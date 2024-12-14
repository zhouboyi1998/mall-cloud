package com.cafe.security.service.impl;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.OauthService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class OauthServiceImpl implements OauthService {

    /**
     * 令牌端点 (获取令牌的入口)
     */
    private final TokenEndpoint tokenEndpoint;

    @Override
    public Token token(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        // 使用 TokenEndpoint 生成访问令牌
        OAuth2AccessToken oAuth2AccessToken = Optional.ofNullable(tokenEndpoint.postAccessToken(principal, parameters).getBody())
            .orElseThrow(NullPointerException::new);

        // 将访问令牌信息封装到自定义令牌信息封装类中返回
        Token token = new Token();
        token.setAccessToken(oAuth2AccessToken.getValue());
        token.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        token.setTokenPrefix(RequestConstant.Header.BEARER_PREFIX);
        token.setExpiresIn(oAuth2AccessToken.getExpiresIn());

        return token;
    }
}
