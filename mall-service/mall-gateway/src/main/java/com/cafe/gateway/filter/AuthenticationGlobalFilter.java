package com.cafe.gateway.filter;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.filter
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:01
 * @Description: 认证过滤器
 */
@Slf4j
@Component
public class AuthenticationGlobalFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取 Request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 Request Header 中的 Token
        String token = request.getHeaders().getFirst(RequestConstant.Header.AUTHORIZATION);
        // 如果 Token 为空, 直接返回
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }
        // 移除 Token 中的令牌头, 获取 Access Token
        String accessToken = token.replace(RequestConstant.Header.BEARER_PREFIX, StringConstant.EMPTY);
        // 解析 Access Token, 获取 JWT 令牌
        JWSObject jwsObject = JWSObject.parse(accessToken);
        // 获取用户详细信息 (JWT 载荷)
        String userDetails = jwsObject.getPayload().toString();
        // 打印日志
        log.info("AuthenticationGlobalFilter.filter(): user details -> {}", userDetails);
        // 将用户详细信息添加到 Request 请求头中
        request.mutate().header(RequestConstant.Header.USER_DETAILS, userDetails).build();
        // 使用改变后的 Request 重新生成 ServerWebExchange
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return IntegerConstant.ONE_HUNDRED;
    }
}
