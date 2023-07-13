package com.cafe.common.enumeration.http;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration.http
 * @Author: zhouboyi
 * @Date: 2022/5/9 9:09
 * @Description: 自定义状态码枚举
 */
public enum HttpStatusEnum {

    /**
     * 成功
     */
    SUCCESS(200, "Success"),

    /**
     * 用户不存在
     */
    USERNAME_NOT_FOUND(401, "Username Not Found"),

    /**
     * 账号已禁用
     */
    ACCOUNT_DISABLED(401, "Account Disabled"),

    /**
     * 账号被锁定
     */
    ACCOUNT_LOCKED(401, "Account Locked"),

    /**
     * 账号已过期
     */
    ACCOUNT_EXPIRED(401, "Account Expired"),

    /**
     * 证书已过期
     */
    CREDENTIALS_EXPIRED(401, "Credentials Expired"),

    /**
     * 角色不存在
     */
    ROLE_NOT_FOUND(401, "Role Not Found"),

    /**
     * 失败
     */
    FAIL(500, "Fail");

    /**
     * 状态码
     */
    private final Integer value;

    /**
     * 状态信息
     */
    private final String reasonPhrase;

    HttpStatusEnum(Integer value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public Integer value() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
