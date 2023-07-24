package com.cafe.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.gateway.authentication.OauthServerAuthenticationEntryPoint;
import com.cafe.gateway.authorization.AuthorizationManager;
import com.cafe.gateway.authorization.OauthServerAccessDeniedHandler;
import com.cafe.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import com.cafe.gateway.property.SecureProperties;
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
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:57
 * @Description: WebFlux 安全配置
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    /**
     * 全局跨域配置
     */
    private final GlobalCorsConfig globalCorsConfig;

    /**
     * 白名单 URL 过滤器
     */
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    /**
     * 安全配置
     */
    private final SecureProperties secureProperties;

    /**
     * 授权管理器
     */
    private final AuthorizationManager authorizationManager;

    /**
     * 未认证处理器
     */
    private final OauthServerAuthenticationEntryPoint oauthServerAuthenticationEntryPoint;

    /**
     * 未授权处理器
     */
    private final OauthServerAccessDeniedHandler oauthServerAccessDeniedHandler;

    @Autowired
    public WebFluxSecurityConfig(
        GlobalCorsConfig globalCorsConfig,
        IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter,
        SecureProperties secureProperties,
        AuthorizationManager authorizationManager,
        OauthServerAuthenticationEntryPoint oauthServerAuthenticationEntryPoint,
        OauthServerAccessDeniedHandler oauthServerAccessDeniedHandler
    ) {
        this.globalCorsConfig = globalCorsConfig;
        this.ignoreUrlsRemoveJwtFilter = ignoreUrlsRemoveJwtFilter;
        this.secureProperties = secureProperties;
        this.authorizationManager = authorizationManager;
        this.oauthServerAuthenticationEntryPoint = oauthServerAuthenticationEntryPoint;
        this.oauthServerAccessDeniedHandler = oauthServerAccessDeniedHandler;
    }

    @Bean
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf()
            .disable()
            .cors()
            .and()
            // 添加过滤器: 添加全局跨域配置
            .addFilterBefore(globalCorsConfig.corsWebFilter(), SecurityWebFiltersOrder.CORS)
            // 添加过滤器: 移除白名单 URL 中的 JWT 请求头
            .addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            // 白名单 URL 配置
            .pathMatchers(ArrayUtil.toArray(secureProperties.getIgnoreUrls(), String.class))
            .permitAll()
            // 鉴权管理器配置
            .anyExchange()
            .access(authorizationManager)
            .and()
            .exceptionHandling()
            // 未认证处理器配置
            .authenticationEntryPoint(oauthServerAuthenticationEntryPoint)
            // 未授权处理器配置
            .accessDeniedHandler(oauthServerAccessDeniedHandler)
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthorizationConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthorizationConstant.AUTHORITIES_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
