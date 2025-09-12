package com.cafe.id.support;

import org.springframework.util.StringUtils;

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
     * Redis分布式ID生成器
     */
    REDIS,

    ;

    /**
     * 根据枚举名称查找枚举
     *
     * @param name 枚举名称
     * @return 枚举
     */
    public static IDGenerator findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        name = name.toUpperCase();
        for (IDGenerator idGenerator : values()) {
            if (Objects.equals(idGenerator.name(), name)) {
                return idGenerator;
            }
        }
        return null;
    }
}
