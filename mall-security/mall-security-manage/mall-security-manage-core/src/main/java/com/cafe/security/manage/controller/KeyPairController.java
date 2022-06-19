package com.cafe.security.manage.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: 密钥对接口
 */
@RestController
@RequestMapping(value = "/key")
public class KeyPairController {

    private KeyPair keyPair;

    @Autowired
    public KeyPairController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @ApiOperation(value = "获取 RSA 公钥")
    @GetMapping("/rsa/public")
    public ResponseEntity<Map<String, Object>> getRsaPublicKey() {
        // 从证书中获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 使用公钥生成 Nimbus JOSE + JWT 提供的 RSA 密钥 (只存储公钥)
        RSAKey key = new RSAKey.Builder(publicKey).build();
        // 将密钥转换为 Map 格式
        Map<String, Object> map = new JWKSet(key).toJSONObject();
        return ResponseEntity.ok(map);
    }
}
