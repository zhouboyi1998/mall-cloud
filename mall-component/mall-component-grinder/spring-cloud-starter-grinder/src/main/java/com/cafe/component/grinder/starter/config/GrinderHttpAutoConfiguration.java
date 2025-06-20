package com.cafe.component.grinder.starter.config;

import com.cafe.component.grinder.starter.support.http.HttpInvoker;
import com.cafe.component.grinder.starter.support.http.apache.ApacheHttpClientInvoker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.config
 * @Author: zhouboyi
 * @Date: 2025/7/7 11:44
 * @Description: Grinder 网关 HTTP 调用自动装配配置类
 */
@Configuration
@ConditionalOnClass(value = HttpInvoker.class)
public class GrinderHttpAutoConfiguration {

    /**
     * Apache HttpClient 调用器
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "grinder.http-client", havingValue = "APACHE_HTTP_CLIENT", matchIfMissing = true)
    public HttpInvoker apacheHttpClientInvoker() {
        return new ApacheHttpClientInvoker();
    }
}
