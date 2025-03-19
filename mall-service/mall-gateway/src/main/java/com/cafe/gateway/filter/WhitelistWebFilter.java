package com.cafe.gateway.filter;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.gateway.property.SecurityProperties;
import com.cafe.gateway.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/5/11 11:07
 * @Description: 白名单过滤器
 */
@RequiredArgsConstructor
@Component
public class WhitelistWebFilter implements WebFilter {

    private final SecurityProperties securityProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 获取 Request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 Request Headers
        HttpHeaders httpHeaders = request.getHeaders();
        // 获取 Access Token
        String accessToken = Optional.ofNullable(RequestUtil.getHeader(httpHeaders, RequestConstant.Header.ACCESS_TOKEN))
            .orElse(RequestUtil.getAccessTokenByAuthorization(httpHeaders));
        // 新建 URI 路径匹配器
        PathMatcher pathMatcher = new AntPathMatcher();
        // 获取白名单 API
        List<String> whitelist = securityProperties.getWhitelist();
        // 遍历白名单 API
        for (String path : whitelist) {
            if (pathMatcher.match(path, request.getURI().getPath())) {
                // 调整白名单 API 请求
                request.mutate()
                    // 移除白名单 API 请求头中的授权令牌
                    .header(RequestConstant.Header.AUTHORIZATION, StringConstant.EMPTY)
                    // 将访问令牌添加到白名单 API 请求头中
                    .header(RequestConstant.Header.ACCESS_TOKEN, accessToken)
                    .build();
                // 使用改变后的 Request 重新生成 ServerWebExchange
                exchange = exchange.mutate().request(request).build();
                break;
            }
        }
        return chain.filter(exchange);
    }
}
