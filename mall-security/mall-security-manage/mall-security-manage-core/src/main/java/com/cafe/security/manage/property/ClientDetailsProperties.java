package com.cafe.security.manage.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.property
 * @Author: zhouboyi
 * @Date: 2022/5/15 18:53
 * @Description: 获取 application 中定义的客户端详细信息配置
 */
@Component
@ConfigurationProperties(prefix = "client.details")
public class ClientDetailsProperties {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 授权模式
     */
    private String[] authorizedGrantTypes;

    /**
     * 授权范围
     */
    private String[] scopes;

    /**
     * 访问令牌过期时间
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌过期时间
     */
    private Integer refreshTokenValiditySeconds;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String[] authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }
}
