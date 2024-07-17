package com.cafe.security.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.service.JWKSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description: JWKSet 接口
 */
@Api(value = "JWKSet 接口")
@RestController
@RequestMapping(value = "/jwk-set")
public class JWKSetController {

    private final JWKSetService jwkSetService;

    @Autowired
    public JWKSetController(JWKSetService jwkSetService) {
        this.jwkSetService = jwkSetService;
    }

    @ApiLogPrint(value = "获取 RSA JWKSet")
    @ApiOperation(value = "获取 RSA JWKSet")
    @GetMapping(value = "/rsa")
    public ResponseEntity<Map<String, Object>> rsa() {
        Map<String, Object> jwkSet = jwkSetService.rsa();
        return ResponseEntity.ok(jwkSet);
    }
}
