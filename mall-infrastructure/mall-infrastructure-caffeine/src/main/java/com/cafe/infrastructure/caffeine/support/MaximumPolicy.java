package com.cafe.infrastructure.caffeine.support;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.support
 * @Author: zhouboyi
 * @Date: 2025/9/26 09:37
 * @Description: 缓存最大限度策略枚举
 */
public enum MaximumPolicy {

    /**
     * 限制缓存总容量
     */
    MAXiMUM_SIZE,

    /**
     * 限制缓存总权重
     */
    MAXIMUM_WEIGHT,
}
