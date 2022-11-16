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
    public static final String USER_DETAILS_HEADER = "user-details";

    /**
     * 用户名称 Key
     */
    public static final String USERNAME = "username";

    /**
     * 所有请求
     */
    public static final String ALL_REQUEST = "/**";

    /**
     * 菜单路径在请求路径中的位置
     */
    public static final Integer MENU_PATH_INDEX = 2;
}
