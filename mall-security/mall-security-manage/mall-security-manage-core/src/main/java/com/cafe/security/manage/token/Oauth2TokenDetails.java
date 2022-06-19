package com.cafe.security.manage.token;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.token
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:48
 * @Description: 自定义令牌信息封装
 */
public class Oauth2TokenDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问令牌
     */
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
