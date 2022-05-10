package com.cafe.gateway.management.config;

import cn.hutool.core.util.ArrayUtil;
import com.cafe.gateway.management.authorization.AuthorizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerWebExchangeDelegatingServerAccessDeniedHandler;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.management.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:57
 * @Description: Web 安全配置
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceSecurityConfig {

    private IgnoreUrlsConfig ignoreUrlsConfig;

    private AuthorizationManager authorizationManager;

    private ServerWebExchangeDelegatingServerAccessDeniedHandler serverAccessDeniedHandler;

    private HttpBasicServerAuthenticationEntryPoint serverAuthenticationEntryPoint;

    @Autowired
    public ResourceSecurityConfig(
        IgnoreUrlsConfig ignoreUrlsConfig,
        AuthorizationManager authorizationManager,
        ServerWebExchangeDelegatingServerAccessDeniedHandler serverAccessDeniedHandler,
        HttpBasicServerAuthenticationEntryPoint serverAuthenticationEntryPoint
    ) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        this.authorizationManager = authorizationManager;
        this.serverAccessDeniedHandler = serverAccessDeniedHandler;
        this.serverAuthenticationEntryPoint = serverAuthenticationEntryPoint;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            // 白名单配置
            .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getIgnoreUrls(), String.class)).permitAll()
            // 鉴权管理器配置
            .anyExchange().access(authorizationManager)
            .and()
            .exceptionHandling()
            // 处理未授权
            .accessDeniedHandler(serverAccessDeniedHandler)
            // 处理未认证
            .authenticationEntryPoint(serverAuthenticationEntryPoint)
            .and()
            .csrf().disable();
        return http.build();
    }
}
