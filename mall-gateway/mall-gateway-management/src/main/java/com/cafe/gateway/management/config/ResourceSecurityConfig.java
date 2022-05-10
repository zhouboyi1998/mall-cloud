package com.cafe.gateway.management.config;

import cn.hutool.core.util.ArrayUtil;
import com.cafe.gateway.management.authorization.AuthorizationManager;
import com.cafe.gateway.management.filter.IgnoreUrlsRemoveJwtFilter;
import com.cafe.gateway.management.handler.RestAccessDeniedHandler;
import com.cafe.gateway.management.handler.RestAuthenticationEntryPoint;
import com.cafe.security.management.constant.AuthEnum;
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
 * @Package: com.cafe.gateway.management.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:57
 * @Description: Web 安全配置
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceSecurityConfig {

    private IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    private IgnoreUrlsConfig ignoreUrlsConfig;

    private AuthorizationManager authorizationManager;

    private RestAccessDeniedHandler restAccessDeniedHandler;

    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public ResourceSecurityConfig(
        IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter,
        IgnoreUrlsConfig ignoreUrlsConfig,
        AuthorizationManager authorizationManager,
        RestAccessDeniedHandler restAccessDeniedHandler,
        RestAuthenticationEntryPoint restAuthenticationEntryPoint

    ) {
        this.ignoreUrlsRemoveJwtFilter = ignoreUrlsRemoveJwtFilter;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
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

        // 加入过滤器, 移除白名单 URL 的 JWT 请求头
        http
            .addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        http
            .authorizeExchange()
            // 白名单配置
            .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll()
            // 鉴权管理器配置
            .anyExchange().access(authorizationManager)
            .and()
            .exceptionHandling()
            // 处理未授权
            .accessDeniedHandler(restAccessDeniedHandler)
            // 处理未认证
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
            .csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthEnum.AUTHORITY_PREFIX.getValue());
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthEnum.AUTHORITY_CLAIM_NAME.getValue());
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
