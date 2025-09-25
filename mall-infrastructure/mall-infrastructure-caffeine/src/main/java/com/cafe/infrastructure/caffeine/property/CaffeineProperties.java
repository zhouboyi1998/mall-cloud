package com.cafe.infrastructure.caffeine.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.property
 * @Author: zhouboyi
 * @Date: 2025/8/4 17:57
 * @Description: Caffeine 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "caffeine")
public class CaffeineProperties {

    public static final int INITIAL_CAPACITY = 64;

    public static final long MAXIMUM_SIZE = 1024L;

    public static final long MAXIMUM_WEIGHT = 1024L;

    /**
     * 默认初始容量
     */
    private Integer initialCapacity = INITIAL_CAPACITY;

    /**
     * 默认最大容量
     */
    private Long maximumSize = MAXIMUM_SIZE;

    /**
     * 默认最大权重
     */
    private Long maximumWeight = MAXIMUM_WEIGHT;
}
