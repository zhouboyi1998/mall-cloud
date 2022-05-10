package com.cafe.security.management.constant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.constant
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:14
 * @Description:
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
     * 后台管理接口路径匹配
     */
    ADMIN_URL_PATTERN("/mall-admin/**"),

    /**
     * Redis 缓存权限规则 Key
     */
    RESOURCE_ROLES_MAP_KEY("auth:resourceRolesMap"),

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
    USER_TOKEN_HEADER("user");

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
