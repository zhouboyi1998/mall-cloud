package com.cafe.security.service.impl;

import com.cafe.security.service.JWKService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description: JWK 业务实现类
 */
@RequiredArgsConstructor
@Service
public class JWKServiceImpl implements JWKService {

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    @Override
    public Map<String, Object> jwkSet() {
        // 从密钥对中获取公钥, 转换成 RSA 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 使用 RSA 公钥生成 JWK
        RSAKey key = new RSAKey.Builder(publicKey).build();
        // 封装成 JWKSet 格式返回
        return new JWKSet(key).toJSONObject();
    }

    @Override
    public String base64() {
        // 从密钥对中获取公钥, 封装成 Base64 编码返回
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }
}
