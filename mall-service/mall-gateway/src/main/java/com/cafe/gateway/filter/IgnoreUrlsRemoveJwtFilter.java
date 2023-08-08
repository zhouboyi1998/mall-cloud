package com.cafe.gateway.filter;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.gateway.property.SecureProperties;
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
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2022/5/11 11:07
 * @Description: 白名单 URL 过滤器
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private final SecureProperties secureProperties;

    @Autowired
    public IgnoreUrlsRemoveJwtFilter(SecureProperties secureProperties) {
        this.secureProperties = secureProperties;
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
        // 获取 Request
        ServerHttpRequest request = exchange.getRequest();
        // 获取 URI
        URI uri = request.getURI();
        // 新建 URI 路径匹配器
        PathMatcher pathMatcher = new AntPathMatcher();
        // 获取所有白名单 URL
        List<String> ignoreUrls = secureProperties.getIgnoreUrls();
        // 遍历所有白名单 URL
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                // 移除 Request Header 中的访问令牌
                request.mutate().header(RequestConstant.Header.AUTHORIZATION, StringConstant.EMPTY).build();
                // 使用改变后的 Request 重新生成 ServerWebExchange
                exchange = exchange.mutate().request(request).build();
                break;
            }
        }
        return chain.filter(exchange);
    }
}
