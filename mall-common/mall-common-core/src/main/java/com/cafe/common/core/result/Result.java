package com.cafe.common.core.result;

import com.cafe.common.enumeration.http.HttpStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.result
 * @Author: zhouboyi
 * @Date: 2023/7/11 15:54
 * @Description: 返回结果封装
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // -------------------- FIELD --------------------

    private Integer code;

    private String message;

    private T data;

    // -------------------- CONSTRUCTOR --------------------

    private Result(Integer code, String message) {
        this(code, message, null);
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // -------------------- GETTER / SETTER --------------------

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    // -------------------- TO STRING --------------------

    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "{", "}")
            .add("code=" + code)
            .add("message='" + message + "'")
            .add("data=" + data)
            .toString();
    }

    // -------------------- METHOD --------------------

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }

    public static ResultBuilder status(HttpStatus status) {
        return new ResultBuilder(status);
    }

    public static ResultBuilder status(HttpStatusEnum status) {
        return new ResultBuilder(status);
    }

    public static ResultBuilder success() {
        return status(HttpStatusEnum.SUCCESS);
    }

    public static <T> Result<T> success(@Nullable T data) {
        return status(HttpStatusEnum.SUCCESS).data(data);
    }

    public static ResultBuilder fail() {
        return status(HttpStatusEnum.FAIL);
    }

    public static <T> Result<T> fail(String message) {
        return builder().code(HttpStatusEnum.FAIL.value()).message(message).build();
    }

    public static <T> Result<T> entity(ResponseEntity<T> entity) {
        return status(entity.getStatusCode()).data(entity.getBody());
    }

    /**
     * 返回结果构造器
     */
    public static class ResultBuilder {

        private Integer code;

        private String message;

        protected ResultBuilder() {

        }

        protected ResultBuilder(HttpStatus status) {
            this(status.value(), status.getReasonPhrase());
        }

        protected ResultBuilder(HttpStatusEnum status) {
            this(status.value(), status.getReasonPhrase());
        }

        private ResultBuilder(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public ResultBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public ResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        public <T> Result<T> data(@Nullable T data) {
            return new Result<>(this.code, this.message, data);
        }

        public <T> Result<T> build() {
            return new Result<>(this.code, this.message);
        }
    }
}
