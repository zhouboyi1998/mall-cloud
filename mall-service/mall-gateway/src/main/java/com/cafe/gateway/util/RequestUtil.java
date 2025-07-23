package com.cafe.gateway.util;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.constant.security.AuthorizationConstant;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.util
 * @Author: zhouboyi
 * @Date: 2025/1/11 2:18
 * @Description: 请求工具类
 */
public class RequestUtil {

    /**
     * 从请求头中获取参数
     *
     * @param httpHeaders 请求头
     * @return 请求头参数
     */
    public static String getHeader(HttpHeaders httpHeaders, String parameter) {
        return Optional.ofNullable(httpHeaders)
            .map(headers -> headers.getFirst(parameter))
            .orElse(null);
    }

    /**
     * 从请求头中获取授权令牌, 再从授权令牌中解析获取访问令牌
     *
     * @param httpHeaders 请求头
     * @return 访问令牌
     */
    public static String getAccessTokenByAuthorization(HttpHeaders httpHeaders) {
        return Optional.ofNullable(httpHeaders)
            .map(headers -> headers.getFirst(RequestConstant.Header.AUTHORIZATION))
            .map(authorization -> authorization.replaceFirst(AuthorizationConstant.TokenType.BEARER, StringConstant.EMPTY))
            .map(String::trim)
            .orElse(null);
    }
}
