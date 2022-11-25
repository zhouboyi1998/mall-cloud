package com.cafe.common.enumeration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration
 * @Author: zhouboyi
 * @Date: 2022/5/9 9:09
 * @Description: 自定义状态码枚举
 */
public enum HttpStatusCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(2000, "Success"),

    /**
     * 用户不存在
     */
    USERNAME_NOT_FOUND(4001, "Username Not Found"),

    /**
     * 账号已禁用
     */
    ACCOUNT_DISABLED(4002, "Account Disabled"),

    /**
     * 账号被锁定
     */
    ACCOUNT_LOCKED(4003, "Account Locked"),

    /**
     * 账号已过期
     */
    ACCOUNT_EXPIRED(4004, "Account Expired"),

    /**
     * 证书已过期
     */
    CREDENTIALS_EXPIRED(4005, "Credentials Expired"),

    /**
     * 角色不存在
     */
    ROLE_NOT_FOUND(4006, "Role Not Found"),

    /**
     * 商城内部服务错误
     */
    MALL_INTERNAL_SERVER_ERROR(5000, "Mall Internal Server Error");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态信息
     */
    private final String message;

    HttpStatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
