package com.cafe.common.core.util;

import com.cafe.common.constant.pool.NumberConstant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.util
 * @Author: zhouboyi
 * @Date: 2022/10/28 9:59
 * @Description: 雪花算法 (生成分布式ID)
 */
public class Snowflake {

    // 共占用 64bit 空间, 组成: 符号位 1bit - 时间戳 41bit - 数据中心ID 5bit - 工作机器ID 5bit - 序列号 12bit

    // ----------------- TIMESTAMP -----------------

    /**
     * 开始时间戳 2022-10-28 00:00:00 (确定后不允许更改)
     */
    private static final long EPOCH = 1666886400L;

    // -------------------- BIT --------------------

    /**
     * 序列号占用位数 (每毫秒可以生成 2^12 个ID)
     */
    private static final long SEQUENCE_BIT = 12L;

    /**
     * 工作机器ID占用位数 (每个数据中心可以部署 2^5 个工作机器)
     */
    private static final long WORKER_ID_BIT = 5L;

    /**
     * 数据中心ID占用位数 (一共 2^5 个数据中心)
     */
    private static final long DATACENTER_ID_BIT = 5L;

    // ------------- --- MASK / MAX ----------------

    /**
     * 序列号掩码 (2^12 - 1)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BIT);

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
     * 工作机器ID向左偏移 12 位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BIT;

    /**
     * 数据中心ID向左偏移 17 位
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT;

    /**
     * 时间戳向左偏移 22 位
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT + DATACENTER_ID_BIT;

    // ------------------- FIELD -------------------

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 工作机器ID
     */
    private final long workerId;

    /**
     * 数据中心ID
     */
    private final long datacenterId;

    /**
     * 最后一次生成分布式ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 容忍的最大时间回拨幅度 (单位: 毫秒)
     */
    private static final long MAX_TIME_BACK = 5L;

    // ---------------- CONSTRUCTOR ----------------

    /**
     * 有参构造方法 (手动传入工作机器ID和数据中心ID)
     *
     * @param workerId     工作机器ID
     * @param datacenterId 数据中心ID
     */
    public Snowflake(long workerId, long datacenterId) {
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
    }

    // ------------------- METHOD ------------------

    /**
     * 获取下一个分布式ID
     *
     * @return
     */
    public synchronized long nextId() {
        // 获取当前时间戳
        long timestamp = timeGen();

        // 如果当前时间戳小于最后一次生成分布式ID的时间戳, 说明发生了时间回拨
        if (timestamp < lastTimestamp) {
            // 获取时间回拨幅度
            long offset = lastTimestamp - timestamp;
            // 容忍的最大时间回拨幅度
            if (offset <= MAX_TIME_BACK) {
                try {
                    // 等待双倍时间回拨幅度的时间, 再开始生成分布式ID
                    wait(offset << 1);
                    timestamp = timeGen();
                    // 再次发生时钟回拨
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Thead wait %d milliseconds. Clock moved backwards again. Refusing to generate id for %d milliseconds.", MAX_TIME_BACK, lastTimestamp - timestamp));
                    }
                } catch (InterruptedException e) {
                    // 线程等待中断
                    throw new RuntimeException(String.format("Thead wait interrupted. InterruptedException message -> %s", e.getMessage()));
                }
            } else {
                // 超出容忍的最大时间回拨幅度
                throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds.", lastTimestamp - timestamp));
            }
        }

        // 如果当前时间戳等于最后一次生成分布式ID的时间戳 (同一毫秒内), 序列号递增, 生成新的分布式ID
        if (timestamp == lastTimestamp) {
            // 序列号递增
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 达到毫秒内序列号的最大值, 阻塞线程直到下一毫秒
            if (sequence == 0) {
                // 接收下一毫秒的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 如果前时间戳等于最后一次生成分布式ID的时间戳 (时间已经走到下一毫秒), 重置序列号
            sequence = 0L;
        }

        // 更新最后一次生成分布式ID的时间戳
        lastTimestamp = timestamp;

        // 通过移位 + 或运算, 将 4 个部分拼接成 64bit 的雪花分布式ID
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
            | (datacenterId << DATACENTER_ID_SHIFT)
            | (workerId << WORKER_ID_SHIFT)
            | sequence;
    }

    /**
     * 阻塞线程直到下一毫秒
     *
     * @param lastTimestamp 最后一次生成分布式ID的时间戳
     * @return 下一毫秒的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        // 当时间走到下一毫秒, 返回下一毫秒的时间戳
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    private long timeGen() {
        // 获取系统时间
        return System.currentTimeMillis();
    }

    // -------------------- TEST -------------------

    public static void main(String[] args) {
        Snowflake snowflake = new Snowflake(NumberConstant.ONE, NumberConstant.ONE);
        for (int i = NumberConstant.ZERO; i < NumberConstant.TEN_THOUSAND; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
