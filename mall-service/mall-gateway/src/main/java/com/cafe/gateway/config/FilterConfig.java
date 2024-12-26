package com.cafe.gateway.config;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.security.AuthorizationConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/4/30 20:59
 * @Description: 网关过滤器配置类
 */
@Configuration
public class FilterConfig {

    /**
     * 跨域过滤器
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        // 跨域配置
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许跨域的来源
        configuration.addAllowedOrigin(StringConstant.ASTERISK);
        // 允许跨域的头信息
        configuration.addAllowedHeader(StringConstant.ASTERISK);
        // 允许跨域的请求方式
        configuration.addAllowedMethod(StringConstant.ASTERISK);
        // 是否允许携带 Cookie
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 请求拦截配置: 拦截所有请求, 对拦截的请求进行跨域处理
        source.registerCorsConfiguration(AuthorizationConstant.ALL_REQUEST, configuration);

        return new CorsWebFilter(source);
    }
}
