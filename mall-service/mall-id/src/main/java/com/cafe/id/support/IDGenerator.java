package com.cafe.id.support;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.support
 * @Author: zhouboyi
 * @Date: 2025/9/12 10:58
 * @Description: 分布式ID生成器枚举
 */
public enum IDGenerator {

    /**
     * 雪花分布式ID生成器
     */
    SNOWFLAKE,

    /**
     * Redis 分布式ID生成器
     */
    REDIS,

    /**
     * Redisson 分布式ID生成器
     */
    REDISSON,

    /**
     * Jedis 分布式ID生成器
     */
    JEDIS,

    /**
     * Lettuce 分布式ID生成器
     */
    LETTUCE,

    ;

    /**
     * 根据枚举名称获取枚举
     *
     * @param name 枚举名称
     * @return 枚举
     */
    public static IDGenerator get(String name) {
        if (Objects.isNull(name)) {
            return null;
        }
        name = name.toUpperCase();
        for (IDGenerator value : values()) {
            if (Objects.equals(name, value.name())) {
                return value;
            }
        }
        return null;
    }
}
