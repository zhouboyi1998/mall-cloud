package com.cafe.infrastructure.caffeine.support;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.support
 * @Author: zhouboyi
 * @Date: 2025/9/24 17:15
 * @Description: 缓存过期策略枚举
 */
public enum ExpirePolicy {

    /**
     * 缓存写入后指定时间后过期
     */
    EXPIRE_AFTER_WRITE,

    /**
     * 缓存最后一次访问后指定时间后过期
     */
    EXPIRE_AFTER_ACCESS,
}
