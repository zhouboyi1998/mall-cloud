package com.cafe.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.exception
 * @Author: zhouboyi
 * @Date: 2023/3/10 14:21
 * @Description: 手机号不存在异常
 */
public class MobileNotFoundException extends AuthenticationException {

    public MobileNotFoundException(String msg) {
        super(msg);
    }

    public MobileNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
