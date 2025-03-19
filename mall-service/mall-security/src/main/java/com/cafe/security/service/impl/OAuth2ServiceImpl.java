package com.cafe.security.service.impl;

import com.cafe.security.model.converter.TokenConverter;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
 * @Description: Oauth2 认证业务实现类
 */
@RequiredArgsConstructor
@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    /**
     * 令牌端点 (获取令牌的入口)
     */
    private final TokenEndpoint tokenEndpoint;

    @Override
    public Token token(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        // 使用 TokenEndpoint 生成访问令牌
        ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        OAuth2AccessToken oAuth2AccessToken = Optional.ofNullable(responseEntity)
            .map(ResponseEntity::getBody)
            .orElseThrow(NullPointerException::new);

        // 将访问令牌信息封装到自定义令牌信息封装类中返回
        return TokenConverter.INSTANCE.toToken(oAuth2AccessToken);
    }
}
