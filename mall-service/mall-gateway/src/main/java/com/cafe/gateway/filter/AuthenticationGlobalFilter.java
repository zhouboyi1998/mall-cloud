package com.cafe.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.cafe.common.constant.HttpHeaderConstant;
import com.cafe.common.constant.StringConstant;
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
 * @Package: com.cafe.gateway.filter
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:01
 * @Description: 全局过滤器 (解析 JWT 中的用户认证信息)
 */
@Component
public class AuthenticationGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationGlobalFilter.class);

    /**
     * 解析 JWT 获取其中的用户信息
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取 Request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 Request Header 中的 Token
        String token = request.getHeaders().getFirst(HttpHeaderConstant.AUTHORIZATION_HEADER);
        // 如果 Token 为空, 直接返回
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        // 移除 Token 中的令牌头, 获取 Access Token
        String accessToken = token.replace(HttpHeaderConstant.AUTHORIZATION_HEADER_PREFIX, StringConstant.EMPTY);
        try {
            // 解析 Access Token
            JWSObject jwsObject = JWSObject.parse(accessToken);
            // 从解析后的 Access Token 中获取用户详细信息
            String userDetails = jwsObject.getPayload().toString();
            // 打印日志
            LOGGER.info("AuthenticationGlobalFilter.filter(): userDetails -> {}", userDetails);
            // 将用户信息设置到 Request 请求头中
            request.mutate().header(HttpHeaderConstant.USER_DETAILS_HEADER, userDetails).build();
            // 使用改变后的 Request 重新生成 ServerWebExchange
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            LOGGER.error("AuthenticationGlobalFilter.filter(): could not parse accessToken -> {}, message -> {}", accessToken, e.getMessage());
        }
        return chain.filter(exchange);
    }

    /**
     * 设置过滤器优先级
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
