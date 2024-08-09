package com.cafe.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.exception
 * @Author: zhouboyi
 * @Date: 2024/8/9 9:57
 * @Description: 邮箱不存在异常
 */
public class EmailNotFoundException extends AuthenticationException {

    public EmailNotFoundException(String msg) {
        super(msg);
    }

    public EmailNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
