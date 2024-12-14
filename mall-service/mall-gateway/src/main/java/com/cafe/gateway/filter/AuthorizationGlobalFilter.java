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

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.filter
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:01
 * @Description: 授权过滤器
 */
@Slf4j
@Component
public class AuthorizationGlobalFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取 Request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 Access Token
        String accessToken = Optional.of(request)
            .map(ServerHttpRequest::getHeaders)
            .map(httpHeaders -> httpHeaders.getFirst(RequestConstant.Header.AUTHORIZATION))
            .map(authorization -> authorization.replace(RequestConstant.Header.BEARER_PREFIX, StringConstant.EMPTY))
            .orElse(StringConstant.EMPTY);
        // 如果 Access Token 为空, 直接返回
        if (StringUtils.isEmpty(accessToken)) {
            return chain.filter(exchange);
        }
        // 获取 JWT 载荷
        String payload = JWSObject.parse(accessToken).getPayload().toString();
        // 打印日志
        log.info("AuthenticationGlobalFilter.filter(): payload -> {}", payload);
        // 将 JWT 载荷添加到 Request Header 中
        request.mutate().header(RequestConstant.Header.PAYLOAD, payload).build();
        // 使用改变后的 Request 重新生成 ServerWebExchange
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return IntegerConstant.ONE_HUNDRED;
    }
}
