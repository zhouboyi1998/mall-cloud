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

    /**
     * 缓存容量配置
     */
    private Capacity capacity = new Capacity();

    @Getter
    @Setter
    public static class Capacity {

        /**
         * 默认初始容量
         */
        private Integer initialCapacity = 64;

        /**
         * 默认最大容量
         */
        private Long maximumSize = 1024L;

        /**
         * 默认最大权重
         */
        private Long maximumWeight = 1024L;
    }
}
