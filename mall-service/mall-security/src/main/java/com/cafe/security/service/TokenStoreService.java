package com.cafe.security.service;

import com.cafe.security.model.vo.Token;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2025/1/8 16:01
 * @Description: 令牌存储业务接口
 */
public interface TokenStoreService {

    /**
     * 读取访问令牌
     *
     * @param accessToken 访问令牌
     * @return 令牌模型
     */
    Token readAccessToken(String accessToken);

    /**
     * 移除访问令牌
     *
     * @param accessToken 访问令牌
     */
    void removeAccessToken(String accessToken);

    /**
     * 移除刷新令牌
     *
     * @param refreshToken 刷新令牌
     */
    void removeRefreshToken(String refreshToken);
}
