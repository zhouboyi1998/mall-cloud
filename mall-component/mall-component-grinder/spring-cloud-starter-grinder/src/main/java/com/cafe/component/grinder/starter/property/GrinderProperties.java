package com.cafe.component.grinder.starter.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.property
 * @Author: zhouboyi
 * @Date: 2025/6/20 17:35
 * @Description: Grinder 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "grinder")
public class GrinderProperties {

    // -------------------- FIELD --------------------

    /**
     * Servlet 匹配的 URL 路径
     */
    private String[] servletPatterns = new String[]{"/*"};

    /**
     * Grinder 过滤器链部署方式
     */
    private FilterChainDeploymentType filterChainDeployment = FilterChainDeploymentType.SERVLET;

    /**
     * Grinder 负载均衡方式
     */
    private LoadBalanceType loadBalance = LoadBalanceType.RANDOM;

    /**
     * HTTP 客户端类型
     */
    private HttpClientType httpClient = HttpClientType.APACHE_HTTP_CLIENT;

    // -------------------- ENUM --------------------

    /**
     * Grinder 过滤器链部署方式枚举
     */
    @Getter
    @AllArgsConstructor
    public enum FilterChainDeploymentType {

        /**
         * 使用独立的 Servlet 容器部署 Grinder 过滤器链
         */
        SERVLET,

        /**
         * 将 Grinder 过滤器链部署到已有的 Servlet 容器中
         */
        SERVLET_FILTER
    }

    /**
     * Grinder 负载均衡方式枚举
     */
    @Getter
    @AllArgsConstructor
    public enum LoadBalanceType {

        /**
         * Random 随机选择
         */
        RANDOM
    }

    /**
     * HTTP 客户端类型枚举
     */
    @Getter
    @AllArgsConstructor
    public enum HttpClientType {

        /**
         * Apache HttpClient
         */
        APACHE_HTTP_CLIENT,

        /**
         * OkHttp
         */
        OK_HTTP
    }
}
