package com.cafe.common.constant.security;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.security
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:14
 * @Description: 授权相关常量
 */
public class AuthorizationConstant {

    /**
     * 权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 权限声明名称
     */
    public static final String AUTHORITIES_CLAIM_NAME = "authorities";

    /**
     * 所有请求
     */
    public static final String ALL_REQUEST = "/**";

    /**
     * 授权模式
     */
    public static class GrantType {

        /**
         * 图片验证码
         */
        public static final String CAPTCHA = "captcha";

        /**
         * 手机号
         */
        public static final String MOBILE = "mobile";

        /**
         * 邮箱
         */
        public static final String EMAIL = "email";
    }

    /**
     * 令牌类型
     */
    public static class TokenType {

        /**
         * OAuth2 Token
         */
        public static final String BEARER = "bearer";
    }
}
