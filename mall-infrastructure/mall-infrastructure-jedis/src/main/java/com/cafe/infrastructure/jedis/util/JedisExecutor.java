package com.cafe.infrastructure.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Function;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.jedis.util
 * @Author: zhouboyi
 * @Date: 2025/9/23 11:02
 * @Description: Jedis 命令执行器
 */
public class JedisExecutor {

    /**
     * 执行 Jedis 命令
     *
     * @param jedisPool Jedis 连接池
     * @param function  Jedis 命令
     * @param <R>       命令返回值类型
     * @return 命令返回值
     */
    public static <R> R execute(JedisPool jedisPool, Function<Jedis, R> function) {
        try (Jedis jedis = jedisPool.getResource()) {
            return function.apply(jedis);
        }
    }
}
