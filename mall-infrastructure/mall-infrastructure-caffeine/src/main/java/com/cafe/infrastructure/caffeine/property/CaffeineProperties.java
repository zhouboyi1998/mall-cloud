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
     * 默认最大缓存数量
     */
    private Integer defaultMaximumSize = 1000;
}
