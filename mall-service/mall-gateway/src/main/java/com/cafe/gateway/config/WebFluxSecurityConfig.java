package com.cafe.gateway.config;

import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.gateway.exception.OauthServerAccessDeniedHandler;
import com.cafe.gateway.exception.OauthServerAuthenticationEntryPoint;
import com.cafe.gateway.filter.WhitelistWebFilter;
import com.cafe.gateway.manager.AuthorizationManager;
import com.cafe.gateway.property.SecurityProperties;
import lombok.RequiredArgsConstructor;
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
import org.springframework.util.StringUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:57
 * @Description: WebFlux 安全配置类
 */
@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    /**
     * 跨域过滤器
     */
    private final CorsWebFilter corsWebFilter;

    /**
     * 白名单过滤器
     */
    private final WhitelistWebFilter whitelistWebFilter;

    /**
     * 网关安全配置
     */
    private final SecurityProperties securityProperties;

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

    @Bean
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf()
            .disable()
            .cors()
            .and()
            // 添加跨域过滤器
            .addFilterBefore(corsWebFilter, SecurityWebFiltersOrder.CORS)
            // 添加白名单过滤器
            .addFilterBefore(whitelistWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            // 白名单配置
            .pathMatchers(StringUtils.toStringArray(securityProperties.getWhitelist()))
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
