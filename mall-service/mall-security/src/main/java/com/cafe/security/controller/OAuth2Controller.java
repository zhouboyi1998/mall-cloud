package com.cafe.security.controller;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.security.facade.OAuth2Facade;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.OAuth2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: OAuth2 认证接口
 */
@Api(value = "OAuth2 认证接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/oauth")
public class OAuth2Controller {

    private final OAuth2Service oauth2Service;

    private final OAuth2Facade oauth2Facade;

    @ApiLogPrint(value = "OAuth2 登录认证")
    @ApiOperation(value = "OAuth2 登录认证")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "授权模式", name = "grant_type", dataType = "String", paramType = "form", required = true, allowableValues = "password, refresh_token, captcha, mobile, email"),
        @ApiImplicitParam(value = "客户端id", name = "client_id", dataType = "String", paramType = "form", required = true),
        @ApiImplicitParam(value = "客户端密钥", name = "client_secret", dataType = "String", paramType = "form", required = true),
        @ApiImplicitParam(value = "用户名", name = "username", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "密码", name = "password", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "刷新令牌", name = "refresh_token", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "验证码key", name = "key", dataType = "Long", paramType = "form"),
        @ApiImplicitParam(value = "验证码code", name = "code", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "手机号", name = "mobile", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "邮箱", name = "email", dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/token")
    public ResponseEntity<Token> token(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        Token token = oauth2Service.token(principal, parameters);
        return ResponseEntity.ok(token);
    }

    @ApiLogPrint(value = "OAuth2 退出登录")
    @ApiOperation(value = "OAuth2 退出登录")
    @ApiImplicitParam(value = "访问令牌", name = RequestConstant.Header.ACCESS_TOKEN, dataType = "String", paramType = "header", required = true)
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = RequestConstant.Header.ACCESS_TOKEN) String accessToken) {
        oauth2Facade.logout(accessToken);
        return ResponseEntity.ok().build();
    }
}
