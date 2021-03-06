package com.cafe.common.enumeration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.enumeration
 * @Author: zhouboyi
 * @Date: 2022/5/9 9:09
 * @Description: 错误信息枚举
 */
public enum ExceptionMessageEnum {

    USERNAME_NOTFOUND(1001, "账号不存在"),
    ACCOUNT_DISABLED(1002, "账号不可用"),
    ACCOUNT_LOCKED(1003, "账号被锁定"),
    ACCOUNT_EXPIRED(1004, "账号已过期"),
    CREDENTIALS_EXPIRED(1005, "证书已过期");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    ExceptionMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
