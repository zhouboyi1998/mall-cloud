package com.cafe.security.facade.impl;

import com.cafe.security.facade.OAuth2Facade;
import com.cafe.security.model.vo.Token;
import com.cafe.security.service.TokenStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.facade.impl
 * @Author: zhouboyi
 * @Date: 2025/1/8 17:33
 * @Description: OAuth2 防腐层实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2FacadeImpl implements OAuth2Facade {

    private final TokenStoreService tokenStoreService;

    @Override
    public void logout(String accessToken) {
        Token token = tokenStoreService.readAccessToken(accessToken);
        if (Objects.nonNull(token)) {
            tokenStoreService.removeAccessToken(token.getAccessToken());
            tokenStoreService.removeRefreshToken(token.getRefreshToken());
        }
    }
}
