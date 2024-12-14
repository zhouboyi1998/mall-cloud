package com.cafe.gateway.util;

import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.util
 * @Author: zhouboyi
 * @Date: 2024/12/10 16:02
 * @Description: JWT 工具类
 */
public class JWTUtil {

    /**
     * 从访问令牌中获取 JWT 载荷
     *
     * @param accessToken 访问令牌
     * @return JWT 载荷
     */
    @SneakyThrows
    public static String getPayload(String accessToken) {
        return JWSObject.parse(accessToken).getPayload().toString();
    }
}
