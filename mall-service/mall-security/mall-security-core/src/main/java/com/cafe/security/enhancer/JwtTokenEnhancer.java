package com.cafe.security.enhancer;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.security.model.UserInfo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.enhancer
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:48
 * @Description: 自定义 JWT 令牌增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        // 从 OAuth2Authentication 中获取用户详细信息
        UserInfo userDetails = (UserInfo) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>(16);
        // 为 JWT 令牌增加额外的内容 ...
        info.put(RequestConstant.USER_ID, userDetails.getId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
