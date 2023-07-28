package com.cafe.common.core.exception;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.core.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.exception
 * @Author: zhouboyi
 * @Date: 2022/9/29 15:27
 * @Description: 全局异常处理器
 */
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = IntegerConstant.ONE_HUNDRED)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handle(Exception e) {
        String causeName = Optional.ofNullable(e.getCause())
            .map(cause -> cause.getClass().getCanonicalName())
            .orElse(StringConstant.NULL);
        LOGGER.error("GlobalExceptionHandler.handle(): exception -> {}, cause -> {}, message -> {}", e.getClass().getCanonicalName(), causeName, e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}
