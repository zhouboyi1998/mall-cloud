package com.cafe.security.controller;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.TokenStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.controller
 * @Author: zhouboyi
 * @Date: 2025/1/11 1:23
 * @Description: 令牌存储接口
 */
@Api(value = "令牌存储接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/token-store")
public class TokenStoreController {

    private final TokenStoreService tokenStoreService;

    @ApiLogPrint(value = "读取访问令牌")
    @ApiOperation(value = "读取访问令牌")
    @ApiImplicitParam(value = "访问令牌", name = RequestConstant.Header.ACCESS_TOKEN, dataType = "String", paramType = "header", required = true)
    @GetMapping(value = "/access-token/read")
    public ResponseEntity<Token> readAccessToken(@RequestHeader(value = RequestConstant.Header.ACCESS_TOKEN) String accessToken) {
        Token token = tokenStoreService.readAccessToken(accessToken);
        return ResponseEntity.ok(token);
    }
}
