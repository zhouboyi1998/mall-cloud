package com.cafe.infrastructure.lettuce.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.RedisCodec;

import java.util.function.Function;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.lettuce.util
 * @Author: zhouboyi
 * @Date: 2025/9/23 15:16
 * @Description: Lettuce 命令执行器
 */
public class LettuceExecutor {

    /**
     * 执行 Lettuce 命令
     *
     * @param redisClient Lettuce 客户端
     * @param redisCodec  Lettuce 编码器
     * @param function    Lettuce 命令
     * @param <K>         Redis 键类型
     * @param <V>         Redis 值类型
     * @param <R>         命令返回值类型
     * @return 命令返回值
     */
    public static <K, V, R> R execute(RedisClient redisClient, RedisCodec<K, V> redisCodec, Function<RedisCommands<K, V>, R> function) {
        try (StatefulRedisConnection<K, V> redisConnection = redisClient.connect(redisCodec)) {
            RedisCommands<K, V> redisCommands = redisConnection.sync();
            return function.apply(redisCommands);
        }
    }

    /**
     * 执行 Lettuce 命令
     *
     * @param redisClient Lettuce 客户端
     * @param function    Lettuce 命令
     * @param <R>         命令返回值类型
     * @return 命令返回值
     */
    public static <R> R execute(RedisClient redisClient, Function<RedisCommands<String, String>, R> function) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            RedisCommands<String, String> redisCommands = redisConnection.sync();
            return function.apply(redisCommands);
        }
    }
}
