package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:09
 * @Description: Redis 缓存相关常量
 */
public enum RedisEnum {

    /**
     * 资源-角色对应关系的 Key
     */
    RESOURCE_ROLE_MAP("AUTH:RESOURCE_ROLE_MAP"),

    /**
     * 令牌存储相关的 Key 前缀
     */
    TOKEN_PREFIX("AUTH:TOKEN_"),

    /**
     * 缓存控制: 无缓存
     */
    NO_CACHE("no-cache");

    private String value;

    private RedisEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
