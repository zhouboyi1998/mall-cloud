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
     * 权限前缀
     */
    AUTHORITY_PREFIX("ROLE_"),

    /**
     * 权限名称
     */
    AUTHORITY_CLAIM_NAME("authorities"),

    /**
     * JWT 令牌前缀
     */
    JWT_TOKEN_PREFIX("Bearer "),

    /**
     * 认证信息请求头 (JWT 令牌请求头)
     */
    JWT_TOKEN_HEADER("Authorization"),

    /**
     * 用户信息请求头
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
