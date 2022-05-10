package com.cafe.security.management.controller;

import com.cafe.security.management.token.Oauth2TokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.controller
 * @Author: zhouboyi
 * @Date: 2022/5/10 21:34
 * @Description: 登录认证接口
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    private TokenEndpoint tokenEndpoint;

    @Autowired
    public AuthController(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    /**
     * Oauth2 登录认证
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<Oauth2TokenDetails> postAccessToken(
        Principal principal,
        @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        // 获取 Token 信息
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        // 将 Token 信息封装到自定义封装类中返回
        Oauth2TokenDetails oauth2TokenDetails = new Oauth2TokenDetails();
        oauth2TokenDetails.setToken(oAuth2AccessToken.getValue());
        oauth2TokenDetails.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        oauth2TokenDetails.setExpiresIn(oAuth2AccessToken.getExpiresIn());
        oauth2TokenDetails.setTokenHead("Bearer ");

        return ResponseEntity.ok(oauth2TokenDetails);
    }
}
