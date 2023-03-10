package com.cafe.security.model;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.model
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:48
 * @Description: 令牌详细信息
 */
public class TokenDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 访问令牌头前缀
     */
    private String tokenPrefix;

    /**
     * 有效时间 (秒)
     */
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
