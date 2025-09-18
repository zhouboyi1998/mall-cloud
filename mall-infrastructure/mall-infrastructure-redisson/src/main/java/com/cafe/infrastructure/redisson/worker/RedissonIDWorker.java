package com.cafe.infrastructure.redisson.worker;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedissonConstant;
import org.redisson.api.RedissonClient;
import org.redisson.api.options.KeysScanOptions;

import java.time.LocalDate;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.redisson.worker
 * @Author: zhouboyi
 * @Date: 2025/9/18 10:36
 * @Description: Redisson 分布式ID生成器
 */
public class RedissonIDWorker {

    // 共占用 64bit 空间, 组成: 符号位 1bit - 日期戳 15bit - 数据中心ID 5bit - 工作机器ID 5bit - 分组哈希 8bit - 序列号 30bit

    // ----------------- TIMESTAMP -----------------

    /**
     * 开始日期戳 2025-09-18 (确定后不允许更改)
     */
    private static final long EPOCH = LocalDate.of(2025, 9, 18).toEpochDay();

    // -------------------- BIT --------------------

    /**
     * 序列号占用位数 (每天可以生成 2^30 个ID)
     */
    private static final long SEQUENCE_BIT = 30L;

    /**
     * 分组哈希占用位数
     */
    private static final long GROUP_HASH_BIT = 8L;

    /**
     * 工作机器ID占用位数 (每个数据中心可以部署 2^5 个工作机器)
     */
    private static final long WORKER_ID_BIT = 5L;

    /**
     * 数据中心ID占用位数 (一共 2^5 个数据中心)
     */
    private static final long DATACENTER_ID_BIT = 5L;

    // 日期戳占用位数 (一共 2^15 天, 约 89 年)

    // ------------- --- MASK / MAX ----------------

    /**
     * 序列号掩码 (2^30 - 1)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BIT);

    /**
     * 分组哈希掩码 (2^8 - 1)
     */
    private static final long GROUP_HASH_MASK = ~(-1L << GROUP_HASH_BIT);

    /**
     * 工作机器ID掩码 (2^5 - 1)
     */
    private static final long WORKER_ID_MASK = ~(-1L << WORKER_ID_BIT);

    /**
     * 数据中心ID掩码 (2^5 - 1)
     */
    private static final long DATACENTER_ID_MASK = ~(-1L << DATACENTER_ID_BIT);

    // ------------------- OFFSET ------------------

    /**
     * 分组哈希向左偏移 30 位
     */
    private static final long GROUP_HASH_SHIFT = SEQUENCE_BIT;

    /**
     * 工作机器ID向左偏移 38 位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BIT + GROUP_HASH_BIT;

    /**
     * 数据中心ID向左偏移 43 位
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BIT + GROUP_HASH_BIT + WORKER_ID_BIT;

    /**
     * 日期时间戳向左偏移 48 位
     */
    private static final long DATESTAMP_SHIFT = SEQUENCE_BIT + GROUP_HASH_BIT + WORKER_ID_BIT + DATACENTER_ID_BIT;

    // ------------------- FIELD -------------------

    /**
     * 工作机器ID
     */
    private final long workerId;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    /**
     * Redisson 客户端
     */
    private final RedissonClient redissonClient;

    // ---------------- CONSTRUCTOR ----------------

    public RedissonIDWorker(long workerId, long datacenterId, RedissonClient redissonClient) {
        // 工作机器ID超出限制
        if (workerId > WORKER_ID_MASK || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker id can't be greater than %d or less than 0.", WORKER_ID_MASK));
        }
        // 数据中心ID超出限制
        if (datacenterId > DATACENTER_ID_MASK || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter id can't be greater than %d or less than 0.", DATACENTER_ID_MASK));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.redissonClient = redissonClient;
    }

    /**
     * 获取下一个分布式ID
     *
     * @param group ID分组
     * @return 分布式ID
     */
    public long nextId(String group) {
        // 获取当前日期戳
        long datestamp = LocalDate.now().toEpochDay();

        // 组装完整的 Redis Key
        String key = RedissonConstant.ID_PREFIX + datestamp + StringConstant.COLON + group;
        // 序列号递增
        long sequence = redissonClient.getAtomicLong(key).incrementAndGet() & SEQUENCE_MASK;

        // 通过移位 + 或运算, 将 5 个部分拼接成 64bit 的 Redis 分布式ID
        return ((datestamp - EPOCH) << DATESTAMP_SHIFT)
            | (datacenterId << DATACENTER_ID_SHIFT)
            | (workerId << WORKER_ID_SHIFT)
            | ((group.hashCode() & GROUP_HASH_MASK) << GROUP_HASH_SHIFT)
            | sequence;
    }

    /**
     * 获取下一个分布式ID
     *
     * @return 分布式ID
     */
    public long nextId() {
        return nextId(RedissonConstant.DEFAULT_ID_GROUP);
    }

    /**
     * 移除过期的 Redis 分布式ID Key
     *
     * @return 移除的 Key 数量
     */
    public long removeExpiredKeys() {
        // 获取当前日期戳
        long datestamp = LocalDate.now().toEpochDay();

        // 组装 Redis Key 匹配规则
        String pattern = RedissonConstant.ID_PREFIX + StringConstant.ASTERISK + StringConstant.COLON + StringConstant.ASTERISK;
        // 获取所有匹配的 Redis Key
        String[] keyArray = redissonClient.getKeys().getKeysStream(KeysScanOptions.defaults().pattern(pattern))
            // 从所有匹配的 Redis Key 中过滤出 7 天之前的
            .filter(key -> Long.parseLong(key.substring(RedissonConstant.ID_PREFIX.length()).split(StringConstant.COLON)[0]) + 7 <= datestamp)
            .toArray(String[]::new);

        // 批量删除 Redis Key
        return redissonClient.getKeys().delete(keyArray);
    }
}
