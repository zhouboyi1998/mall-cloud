package com.cafe.gateway.management.filter;

import cn.hutool.core.util.StrUtil;
import com.cafe.security.management.constant.AuthEnum;
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
 * @Package: com.cafe.gateway.management.filter
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:01
 * @Description: 解析用户信息的全局过滤器
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthEnum.JWT_TOKEN_HEADER.getValue());
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            // 从 Token 中解析用户信息并设置到 Header 中去
            String realToken = token.replace(AuthEnum.JWT_TOKEN_PREFIX.getValue(), "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            LOGGER.info("AuthGlobalFilter.filter() user:{}", userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userStr).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
