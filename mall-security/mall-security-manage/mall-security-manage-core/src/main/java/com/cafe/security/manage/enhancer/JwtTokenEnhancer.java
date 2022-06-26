package com.cafe.security.manage.enhancer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.enhancer
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:48
 * @Description: 自定义 JWT 令牌增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        User userDetails = (User) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<String, Object>(16);
        // 把用户名设置到 JWT 中
        info.put("username", userDetails.getUsername());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
