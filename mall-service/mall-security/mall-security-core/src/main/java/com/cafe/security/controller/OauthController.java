package com.cafe.security.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.security.model.TokenDetails;
import com.cafe.security.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @Description: 登录认证接口
 */
@Api(value = "登录认证接口")
@RestController
@RequestMapping(value = "/oauth")
public class OauthController {

    private final OauthService oauthService;

    @Autowired
    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    /**
     * Oauth2 登录认证
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @LogPrint(value = "Oauth2 登录认证")
    @ApiOperation(value = "Oauth2 登录认证")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "授权模式", name = "grant_type", dataType = "String", paramType = "form", required = true, allowableValues = "password, refresh_token, captcha"),
        @ApiImplicitParam(value = "客户端id", name = "client_id", dataType = "String", paramType = "form", required = true),
        @ApiImplicitParam(value = "客户端密钥", name = "client_secret", dataType = "String", paramType = "form", required = true),
        @ApiImplicitParam(value = "用户名", name = "username", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "密码", name = "password", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "刷新令牌", name = "refresh_token", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "验证码key", name = "key", dataType = "Long", paramType = "form"),
        @ApiImplicitParam(value = "验证码code", name = "code", dataType = "String", paramType = "form"),
        @ApiImplicitParam(value = "手机号", name = "mobile", dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/token")
    public ResponseEntity<TokenDetails> token(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        TokenDetails tokenDetails = oauthService.token(principal, parameters);
        return ResponseEntity.ok(tokenDetails);
    }
}
