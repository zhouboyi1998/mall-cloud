package com.cafe.security.manage.controller;

import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.security.manage.token.Oauth2TokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/token")
    public ResponseEntity<Oauth2TokenDetails> postAccessToken(
        Principal principal,
        @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        // 使用 TokenEndpoint 生成访问令牌
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        // 将访问令牌信息封装到自定义令牌信息封装类中返回
        Oauth2TokenDetails oauth2TokenDetails = new Oauth2TokenDetails();
        oauth2TokenDetails.setToken(oAuth2AccessToken.getValue());
        oauth2TokenDetails.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        oauth2TokenDetails.setTokenPrefix(AuthenticationConstant.JWT_TOKEN_PREFIX);
        oauth2TokenDetails.setExpiresIn(oAuth2AccessToken.getExpiresIn());

        return ResponseEntity.ok(oauth2TokenDetails);
    }
}
