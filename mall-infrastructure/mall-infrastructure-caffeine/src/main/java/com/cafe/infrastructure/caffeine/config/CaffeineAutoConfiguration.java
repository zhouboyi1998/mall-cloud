package com.cafe.infrastructure.caffeine.config;

import com.cafe.infrastructure.caffeine.manager.CaffeineCacheManager;
import com.cafe.infrastructure.caffeine.property.CaffeineProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.config
 * @Author: zhouboyi
 * @Date: 2025/8/4 17:56
 * @Description: Caffeine 自动装配配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = CaffeineProperties.class)
@ConditionalOnClass(value = CaffeineCacheManager.class)
public class CaffeineAutoConfiguration {

    private final CaffeineProperties caffeineProperties;

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        return new CaffeineCacheManager(caffeineProperties);
    }
}
