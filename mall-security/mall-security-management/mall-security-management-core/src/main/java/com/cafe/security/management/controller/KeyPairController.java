package com.cafe.security.management.controller;

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
 * @Package: com.cafe.security.management.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: 密钥对接口
 */
@RestController
@RequestMapping(value = "/keyPair")
public class KeyPairController {

    private KeyPair keyPair;

    @Autowired
    public KeyPairController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @ApiOperation(value = "获取 RSA 私钥")
    @GetMapping("/rsa/publicKey")
    public ResponseEntity<Map<String, Object>> getRsaPublicKey() {
        // 从 RSA 密钥对中获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 使用公钥生成私钥
        RSAKey key = new RSAKey.Builder(publicKey).build();
        // 将私钥信息转换为 Map 格式
        Map<String, Object> map = new JWKSet(key).toJSONObject();
        return ResponseEntity.ok(map);
    }
}
