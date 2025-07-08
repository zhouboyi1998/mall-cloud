package com.cafe.id.worker;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.worker
 * @Author: zhouboyi
 * @Date: 2025/7/8 16:53
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class RedisIDWorker {

    /**
     * 开始时间戳 2025-07-08 00:00:00 (确定后不允许更改)
     */
    private static final long EPOCH = 1751904000L;

    /**
     * 自增ID占用位数
     */
    private static final long INCREMENT_ID_BIT = 22L;

    /**
     * 时间戳向左偏移 22 位
     */
    private static final long TIMESTAMP_SHIFT = INCREMENT_ID_BIT;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 获取下一个分布式ID
     *
     * @param group ID分组
     * @return 分布式ID
     */
    public Long nextId(String group) {
        // 获取 UTC 时间
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneOffset.UTC);
        // 获取当前时间戳
        long timestamp = now.toInstant().toEpochMilli();

        // 组装完整的 Redis Key
        String key = RedisConstant.ID_PREFIX + now.toLocalDate() + StringConstant.COLON + group;
        // 生成自增ID
        Long increment = Optional.ofNullable(stringRedisTemplate.opsForValue().increment(key))
            .orElseThrow(() -> new RuntimeException("Redis failed to generate a distributed increment id."));

        // 拼接完整的分布式ID
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT) | increment;
    }
}
