package com.cafe.component.grinder.starter.config;

import com.cafe.component.grinder.starter.support.instance.ServiceInstanceSelector;
import com.cafe.component.grinder.starter.support.instance.random.RandomServiceInstanceSelector;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.config
 * @Author: zhouboyi
 * @Date: 2025/7/7 11:38
 * @Description: Grinder 网关服务发现自动装配配置类
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(value = ServiceInstanceSelector.class)
public class GrinderDiscoveryAutoConfiguration {

    /**
     * 服务发现客户端
     */
    private final DiscoveryClient discoveryClient;

    /**
     * Random 服务实例选择器
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "grinder.load-balance", havingValue = "RANDOM", matchIfMissing = true)
    public ServiceInstanceSelector randomServiceInstanceSelector() {
        return new RandomServiceInstanceSelector(discoveryClient);
    }
}
