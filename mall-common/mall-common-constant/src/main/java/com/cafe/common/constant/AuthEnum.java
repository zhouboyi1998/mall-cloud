package com.cafe.common.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:14
 * @Description: 认证信息相关常量
 */
public enum AuthEnum {

    /**
     * JWT 存储权限前缀
     */
    AUTHORITY_PREFIX("ROLE_"),

    /**
     * JWT 存储权限属性
     */
    AUTHORITY_CLAIM_NAME("authorities"),

    /**
     * 后台管理 client_id
     */
    MANAGEMENT_CLIENT_ID("management-app"),

    /**
     * 前台商城 client_id
     */
    PORTAL_CLIENT_ID("portal-app"),

    /**
     * 认证信息 Http 请求头
     */
    JWT_TOKEN_HEADER("Authorization"),

    /**
     * JWT 令牌前缀
     */
    JWT_TOKEN_PREFIX("Bearer "),

    /**
     * 用户信息 Http 请求头
     */
    USER_TOKEN_HEADER("user"),

    /**
     * 后台管理接口路径匹配
     */
    MANAGEMENT_URL_PATTERN("/api/**"),

    /**
     * 前台商城接口路径匹配
     */
    PORTAL_URL_PATTERN("/api/**");

    private String value;

    private AuthEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
