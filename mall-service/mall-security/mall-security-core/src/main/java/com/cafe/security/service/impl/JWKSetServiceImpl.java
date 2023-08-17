package com.cafe.security.service.impl;

import com.cafe.security.service.JWKSetService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description:
 */
@Service
public class JWKSetServiceImpl implements JWKSetService {

    /**
     * 密钥对
     */
    private final KeyPair keyPair;

    @Autowired
    public JWKSetServiceImpl(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public Map<String, Object> rsa() {
        // 从证书中获取 RSA 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 使用 RSA 公钥生成 RSA JWK
        RSAKey key = new RSAKey.Builder(publicKey).build();
        // 生成 JWKSet 并返回
        return new JWKSet(key).toJSONObject();
    }
}
