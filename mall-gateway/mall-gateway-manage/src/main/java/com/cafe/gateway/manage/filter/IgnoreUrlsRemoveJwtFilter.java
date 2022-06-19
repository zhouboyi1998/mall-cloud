package com.cafe.gateway.manage.filter;

import com.cafe.gateway.manage.property.IgnoreUrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.manage.config
 * @Author: zhouboyi
 * @Date: 2022/5/11 11:07
 * @Description: 白名单 URL 过滤器
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private IgnoreUrlsProperties ignoreUrlsProperties;

    @Autowired
    public IgnoreUrlsRemoveJwtFilter(IgnoreUrlsProperties ignoreUrlsProperties) {
        this.ignoreUrlsProperties = ignoreUrlsProperties;
    }

    /**
     * 移除白名单 URL 的 JWT 请求头
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 移除白名单 URL 的 JWT 请求头
        List<String> ignoreUrls = ignoreUrlsProperties.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header("Authorization", "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
