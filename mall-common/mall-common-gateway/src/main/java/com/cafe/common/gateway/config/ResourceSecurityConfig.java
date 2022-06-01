package com.cafe.common.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.cafe.common.gateway.authorization.AuthorizationManager;
import com.cafe.common.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import com.cafe.common.gateway.handler.RestAccessDeniedHandler;
import com.cafe.common.gateway.handler.RestAuthenticationEntryPoint;
import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.common.gateway.property.IgnoreUrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:57
 * @Description: Web 安全配置
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceSecurityConfig {

    /**
     * 白名单 URL 过滤器
     */
    private IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    /**
     * 白名单 URL 配置
     */
    private IgnoreUrlsProperties ignoreUrlsProperties;

    /**
     * 授权管理器
     */
    private AuthorizationManager authorizationManager;

    /**
     * 未授权处理类
     */
    private RestAccessDeniedHandler restAccessDeniedHandler;

    /**
     * 未认证处理类
     */
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public ResourceSecurityConfig(
        IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter,
        IgnoreUrlsProperties ignoreUrlsProperties,
        AuthorizationManager authorizationManager,
        RestAccessDeniedHandler restAccessDeniedHandler,
        RestAuthenticationEntryPoint restAuthenticationEntryPoint

    ) {
        this.ignoreUrlsRemoveJwtFilter = ignoreUrlsRemoveJwtFilter;
        this.ignoreUrlsProperties = ignoreUrlsProperties;
        this.authorizationManager = authorizationManager;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter());

        http
            // 添加过滤器: 移除白名单 URL 中的 JWT 请求头
            .addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            // 白名单配置
            .pathMatchers(ArrayUtil.toArray(ignoreUrlsProperties.getUrls(), String.class))
            .permitAll()
            // 鉴权管理器配置
            .anyExchange()
            .access(authorizationManager)
            .and()
            .exceptionHandling()
            // 处理未授权
            .accessDeniedHandler(restAccessDeniedHandler)
            // 处理未认证
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
            .csrf()
            .disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthenticationConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthenticationConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}