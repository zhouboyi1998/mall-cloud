package com.cafe.starter.boot.model.exception;

import com.cafe.common.enumeration.http.HttpStatusEnum;
import org.springframework.http.HttpStatus;

import java.util.StringJoiner;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.model.exception
 * @Author: zhouboyi
 * @Date: 2023/10/30 17:41
 * @Description: 业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // -------------------- FIELD --------------------

    private final Integer code;

    private final Object data;

    // -------------------- CONSTRUCTOR --------------------

    public BusinessException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public BusinessException(HttpStatus status, Object data) {
        this(status.value(), status.getReasonPhrase(), data);
    }

    public BusinessException(HttpStatusEnum status, Object data) {
        this(status.value(), status.getReasonPhrase(), data);
    }

    public BusinessException(HttpStatus status) {
        this(status, null);
    }

    public BusinessException(HttpStatusEnum status) {
        this(status, null);
    }

    // -------------------- GETTER --------------------

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    // -------------------- TO STRING --------------------

    @Override
    public String toString() {
        return new StringJoiner(", ", BusinessException.class.getSimpleName() + "{", "}")
            .add("code=" + code)
            .add("message='" + super.getMessage() + "'")
            .add("data=" + data)
            .toString();
    }
}
