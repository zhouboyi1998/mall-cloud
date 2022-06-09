package com.cafe.common.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.cafe.common.constant.AuthenticationConstant;
import com.nimbusds.jose.JWSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.gateway.filter
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:01
 * @Description: 全局过滤器 (解析 JWT 中的用户认证信息)
 */
@Component
public class AuthenticationGlobalFilter implements GlobalFilter, Ordered {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationGlobalFilter.class);

    /**
     * 解析 JWT 获取其中的用户信息
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthenticationConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            // 从 Token 中解析用户信息并设置到 Header 中
            // 解析令牌
            String realToken = token.replace(AuthenticationConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            // 获取用户信息
            String userStr = jwsObject.getPayload().toString();
            // 打印日志
            LOGGER.info("AuthenticationGlobalFilter.filter() user:{}", userStr);
            // 将用户信息设置到请求头中
            ServerHttpRequest request = exchange
                .getRequest()
                .mutate()
                .header(AuthenticationConstant.USER_TOKEN_HEADER, userStr)
                .build();
            exchange = exchange
                .mutate()
                .request(request)
                .build();
        } catch (ParseException e) {
            LOGGER.error("AuthenticationGlobalFilter.filter() failed to parse token: {}", e.getMessage());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
