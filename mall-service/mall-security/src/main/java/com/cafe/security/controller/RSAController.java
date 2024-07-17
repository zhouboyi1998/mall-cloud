package com.cafe.security.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.service.RSAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.controller
 * @Author: zhouboyi
 * @Date: 2023/8/16 16:06
 * @Description: RSA 接口
 */
@Api(value = "RSA 接口")
@RestController
@RequestMapping(value = "/rsa")
public class RSAController {

    private final RSAService rsaService;

    @Autowired
    public RSAController(RSAService rsaService) {
        this.rsaService = rsaService;
    }

    @ApiLogPrint(value = "获取 RSA 公钥")
    @ApiOperation(value = "获取 RSA 公钥")
    @GetMapping(value = "/public-key")
    public ResponseEntity<String> publicKey() {
        String publicKey = rsaService.publicKey();
        return ResponseEntity.ok(publicKey);
    }
}
