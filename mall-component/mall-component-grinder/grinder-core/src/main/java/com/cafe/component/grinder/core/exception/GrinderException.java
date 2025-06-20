package com.cafe.component.grinder.core.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.exception
 * @Author: zhouboyi
 * @Date: 2025/6/20 15:35
 * @Description: Grinder 异常信息
 */
@Getter
@ToString
public class GrinderException extends Exception {

    private static final long serialVersionUID = 1L;

    private final Integer code;

    // -------------------- CONSTRUCTOR --------------------

    public GrinderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GrinderException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
