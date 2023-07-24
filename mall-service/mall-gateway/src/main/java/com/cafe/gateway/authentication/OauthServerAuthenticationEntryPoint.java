package com.cafe.gateway.authentication;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.request.RequestConstant;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.authentication
 * @Author: zhouboyi
 * @Date: 2022/5/11 10:39
 * @Description: 未认证处理器
 */
@Component
public class OauthServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return Mono.defer(() -> Mono.just(exchange.getResponse()))
            .flatMap(response -> {
                // ----- 设置 HTTP 状态码 -----
                response.setStatusCode(HttpStatus.OK);
                // ----- 设置 HTTP 响应头 -----
                // 请求内容类型: application/json
                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                // 允许跨域访问: 所有路径
                response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, MediaType.ALL.getType());
                // 缓存控制: 无缓存
                response.getHeaders().set(HttpHeaders.CACHE_CONTROL, RequestConstant.NO_CACHE);
                // ----- 设置 HTTP 响应体 -----
                ResponseEntity<String> body = ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(HttpStatus.FORBIDDEN.getReasonPhrase());
                String result = JSONUtil.toJsonStr(body);
                DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(buffer));
            });
    }
}
