package com.cafe.security.manage.service.impl;

import com.cafe.security.manage.service.KeyPairService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/8 10:09
 * @Description:
 */
@Service
public class KeyPairServiceImpl implements KeyPairService {

    private KeyPair keyPair;

    @Autowired
    public KeyPairServiceImpl(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public Map<String, Object> getRsaPublicKey() {
        // 从证书中获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 使用公钥生成 Nimbus JOSE + JWT 提供的 RSA 密钥 (只存储公钥)
        RSAKey key = new RSAKey.Builder(publicKey).build();
        // 将密钥转换为 Map 格式
        Map<String, Object> map = new JWKSet(key).toJSONObject();

        return map;
    }
}
