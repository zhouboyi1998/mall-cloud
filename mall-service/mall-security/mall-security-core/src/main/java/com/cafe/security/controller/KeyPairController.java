package com.cafe.security.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.security.service.KeyPairService;
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
 * @Description: 密钥对接口
 */
@RestController
@RequestMapping(value = "/keypair")
public class KeyPairController {

    private final KeyPairService keyPairService;

    @Autowired
    public KeyPairController(KeyPairService keyPairService) {
        this.keyPairService = keyPairService;
    }

    @LogPrint(value = "获取 RSA 公钥")
    @ApiOperation(value = "获取 RSA 公钥")
    @GetMapping(value = "/rsa")
    public ResponseEntity<Map<String, Object>> rsa() {
        Map<String, Object> key = keyPairService.rsa();
        return ResponseEntity.ok(key);
    }
}
