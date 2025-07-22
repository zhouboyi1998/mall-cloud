package com.cafe.starter.boot.controlleradvice.exception;

import com.cafe.starter.boot.model.Result;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.controlleradvice.exception
 * @Author: zhouboyi
 * @Date: 2022/9/29 15:27
 * @Description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = 100)
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return 返回结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> handle(BusinessException e) {
        log.error("GlobalExceptionHandler.handle(BusinessException e): code -> {}, message -> {}, data -> {}", e.getCode(), e.getMessage(), e.getData(), e);
        return Result.builder().code(e.getCode()).message(e.getMessage()).data(e.getData());
    }

    /**
     * 通用异常处理
     *
     * @param e 异常
     * @return 返回结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(Exception e) {
        log.error("GlobalExceptionHandler.handle(Exception e): exception -> {}, message -> {}", e.getClass().getCanonicalName(), e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}
