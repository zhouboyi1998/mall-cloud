package com.cafe.security.manage.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.security.manage.service.OauthService;
import com.cafe.security.manage.token.Oauth2TokenDetails;
import io.swagger.annotations.ApiImplicitParam;
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
 * @Package: com.cafe.security.manage.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: 登录认证接口
 */
@RestController
@RequestMapping(value = "/oauth")
public class OauthController {

    private OauthService oauthService;

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
    @ApiImplicitParam(name = "parameters", value = "登录认证参数", required = true, paramType = "query", dataType = "Map<String, String>")
    @PostMapping(value = "/token")
    public ResponseEntity<Oauth2TokenDetails> postAccessToken(
        Principal principal,
        @RequestParam(value = "parameters") Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        Oauth2TokenDetails oauth2TokenDetails = oauthService.postAccessToken(principal, parameters);
        return ResponseEntity.ok(oauth2TokenDetails);
    }
}
