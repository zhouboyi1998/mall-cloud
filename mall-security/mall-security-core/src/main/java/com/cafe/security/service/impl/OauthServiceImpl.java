package com.cafe.security.service.impl;

import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.security.service.OauthService;
import com.cafe.security.token.Oauth2TokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/8 9:48
 * @Description:
 */
@Service
public class OauthServiceImpl implements OauthService {

    private final TokenEndpoint tokenEndpoint;

    @Autowired
    public OauthServiceImpl(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    @Override
    public Oauth2TokenDetails postAccessToken(
        Principal principal, Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        // 使用 TokenEndpoint 生成访问令牌
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        // 将访问令牌信息封装到自定义令牌信息封装类中返回
        Oauth2TokenDetails oauth2TokenDetails = new Oauth2TokenDetails();
        oauth2TokenDetails.setAccessToken(oAuth2AccessToken.getValue());
        oauth2TokenDetails.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        oauth2TokenDetails.setTokenPrefix(AuthenticationConstant.JWT_TOKEN_PREFIX);
        oauth2TokenDetails.setExpiresIn(oAuth2AccessToken.getExpiresIn());

        return oauth2TokenDetails;
    }
}
