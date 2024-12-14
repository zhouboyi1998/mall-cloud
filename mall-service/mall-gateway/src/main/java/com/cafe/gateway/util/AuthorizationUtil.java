package com.cafe.gateway.util;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.util
 * @Author: zhouboyi
 * @Date: 2024/12/10 16:03
 * @Description: 授权工具类
 */
public class AuthorizationUtil {

    /**
     * 从请求中获取访问令牌
     *
     * @param serverHttpRequest 请求
     * @return 访问令牌
     */
    public static String getAccessToken(ServerHttpRequest serverHttpRequest) {
        return Optional.ofNullable(serverHttpRequest)
            // 获取请求头
            .map(ServerHttpRequest::getHeaders)
            // 获取 Authorization
            .map(httpHeaders -> httpHeaders.getFirst(RequestConstant.Header.AUTHORIZATION))
            // 移除 Bearer 前缀, 获取 Access Token
            .map(authorization -> authorization.replace(RequestConstant.Header.BEARER_PREFIX, StringConstant.EMPTY))
            .orElse(null);
    }
}
