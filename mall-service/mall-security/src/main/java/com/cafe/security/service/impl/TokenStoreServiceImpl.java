package com.cafe.security.service.impl;

import com.cafe.security.model.converter.TokenConverter;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.TokenStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2025/1/8 16:01
 * @Description: 令牌存储业务实现类
 */
@RequiredArgsConstructor
@Service
public class TokenStoreServiceImpl implements TokenStoreService {

    private final TokenStore tokenStore;

    @Override
    public Token readAccessToken(String accessToken) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        return TokenConverter.INSTANCE.toToken(oAuth2AccessToken);
    }

    @Override
    public void removeAccessToken(String accessToken) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        if (Objects.nonNull(oAuth2AccessToken)) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
    }

    @Override
    public void removeRefreshToken(String refreshToken) {
        OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(refreshToken);
        if (Objects.nonNull(oAuth2RefreshToken)) {
            tokenStore.removeRefreshToken(oAuth2RefreshToken);
        }
    }
}
