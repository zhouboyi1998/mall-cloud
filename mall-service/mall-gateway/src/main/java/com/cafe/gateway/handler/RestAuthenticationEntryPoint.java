package com.cafe.gateway.handler;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.HttpHeaderConstant;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.handle
 * @Author: zhouboyi
 * @Date: 2022/5/11 10:39
 * @Description: 自定义用户未认证处理类 (未登录 / Token 过期)
 */
@Component
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        // 设置 HTTP 状态码
        response.setStatusCode(HttpStatus.OK);
        // 设置 HTTP 请求头
        // 请求内容类型: application/json
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        // 允许跨域访问: 所有路径
        response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, MediaType.ALL.getType());
        // 缓存控制: 无缓存
        response.getHeaders().set(HttpHeaders.CACHE_CONTROL, HttpHeaderConstant.NO_CACHE);
        String body = JSONUtil.toJsonStr(ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage()));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
