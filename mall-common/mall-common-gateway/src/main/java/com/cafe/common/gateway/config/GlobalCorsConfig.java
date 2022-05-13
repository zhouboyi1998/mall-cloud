package com.cafe.common.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/4/30 20:59
 * @Description: 网关全局跨域配置
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域的来源
        config.addAllowedOrigin("*");
        // 允许跨域的头信息
        config.addAllowedHeader("*");
        // 允许跨域的请求方式
        config.addAllowedMethod("*");
        // 是否允许携带 Cookie
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /**
         * 请求拦截配置
         * "/**" 表示拦截所有请求
         * 并使用上面 CorsConfiguration 中的配置, 对拦截的请求进行跨域处理
         */
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
