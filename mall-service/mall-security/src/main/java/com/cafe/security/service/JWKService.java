package com.cafe.security.service;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description: JWK 业务接口
 */
public interface JWKService {

    /**
     * 获取 JWK 公钥 (JWKSet 格式)
     *
     * @return JWK 公钥 (JWKSet 格式)
     */
    Map<String, Object> jwkSet();

    /**
     * 获取 JWK 公钥 (Base64 格式)
     *
     * @return JWK 公钥 (Base64 格式)
     */
    String base64();
}
