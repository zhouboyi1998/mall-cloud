package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:14
 * @Description: 认证信息相关常量
 */
public class AuthenticationConstant {

    /**
     * 权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 权限名称
     */
    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * JWT 令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 认证信息请求头 (JWT 令牌请求头)
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * 用户信息请求头
     */
    public static final String USER_DETAILS_HEADER = "user_details";

    /**
     * 客户端id请求参数
     */
    public static final String CLIENT_ID_PARAMETER = "client_id";

    /**
     * 所有请求
     */
    public static final String ALL_REQUEST = "/**";
}
