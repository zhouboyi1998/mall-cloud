package com.cafe.security.facade;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.facade
 * @Author: zhouboyi
 * @Date: 2025/1/8 17:33
 * @Description: OAuth2 防腐层接口
 */
public interface OAuth2Facade {

    /**
     * OAuth2 退出登录
     *
     * @param accessToken 访问令牌
     */
    void logout(String accessToken);
}
