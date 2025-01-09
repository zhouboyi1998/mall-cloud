package com.cafe.security.model.converter;

import com.cafe.security.model.vo.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.model.converter
 * @Author: zhouboyi
 * @Date: 2025/1/8 16:42
 * @Description: 令牌模型转换器
 */
@Mapper(componentModel = "spring")
public interface TokenConverter {

    TokenConverter INSTANCE = Mappers.getMapper(TokenConverter.class);

    /**
     * OAuth2 访问令牌 -> 令牌模型
     *
     * @param oAuth2AccessToken OAuth2 访问令牌
     * @return 令牌模型
     */
    @Mappings(value = {
        @Mapping(expression = "java(oAuth2AccessToken.getValue())", target = "accessToken"),
        @Mapping(expression = "java(oAuth2AccessToken.getRefreshToken().getValue())", target = "refreshToken"),
        @Mapping(expression = "java(oAuth2AccessToken.getTokenType())", target = "tokenType"),
        @Mapping(expression = "java(oAuth2AccessToken.getExpiresIn())", target = "expiresIn")
    })
    Token toToken(OAuth2AccessToken oAuth2AccessToken);

    /**
     * OAuth2 刷新令牌 -> 令牌模型
     *
     * @param oAuth2RefreshToken OAuth2 刷新令牌
     * @return 令牌模型
     */
    @Mapping(expression = "java(oAuth2RefreshToken.getValue())", target = "refreshToken")
    Token toToken(OAuth2RefreshToken oAuth2RefreshToken);
}
