package com.cafe.security.service.impl;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.security.model.TokenDetails;
import com.cafe.security.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/8 9:48
 * @Description:
 */
@Service
public class OauthServiceImpl implements OauthService {

    /**
     * 令牌端点 (获取令牌的入口)
     */
    private final TokenEndpoint tokenEndpoint;

    @Autowired
    public OauthServiceImpl(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    @Override
    public TokenDetails token(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        // 使用 TokenEndpoint 生成访问令牌
        OAuth2AccessToken oAuth2AccessToken = Optional.ofNullable(tokenEndpoint.postAccessToken(principal, parameters).getBody())
            .orElseThrow(NullPointerException::new);

        // 将访问令牌信息封装到自定义令牌信息封装类中返回
        TokenDetails tokenDetails = new TokenDetails();
        tokenDetails.setAccessToken(oAuth2AccessToken.getValue());
        tokenDetails.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        tokenDetails.setTokenPrefix(RequestConstant.Header.BEARER_PREFIX);
        tokenDetails.setExpiresIn(oAuth2AccessToken.getExpiresIn());

        return tokenDetails;
    }
}
