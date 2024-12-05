package com.cafe.security.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.service.JWKService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: JWK 接口
 */
@Api(value = "JWK 接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/jwk")
public class JWKController {

    private final JWKService jwkService;

    @ApiLogPrint(value = "获取 JWK 公钥 (JWKSet 格式)")
    @ApiOperation(value = "获取 JWK 公钥 (JWKSet 格式)")
    @GetMapping(value = "/jwk-set")
    public ResponseEntity<Map<String, Object>> jwkSet() {
        Map<String, Object> jwkSet = jwkService.jwkSet();
        return ResponseEntity.ok(jwkSet);
    }

    @ApiLogPrint(value = "获取 JWK 公钥 (Base64 格式)")
    @ApiOperation(value = "获取 JWK 公钥 (Base64 格式)")
    @GetMapping(value = "/base64")
    public ResponseEntity<String> base64() {
        String base64 = jwkService.base64();
        return ResponseEntity.ok(base64);
    }
}
