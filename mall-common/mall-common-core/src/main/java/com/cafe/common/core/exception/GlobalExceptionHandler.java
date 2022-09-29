package com.cafe.common.core.exception;

import com.cafe.common.enumeration.HttpStatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.exception
 * @Author: zhouboyi
 * @Date: 2022/9/29 15:27
 * @Description: 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handler(Exception e) {
        LOGGER.error("GlobalExceptionHandler catch exception: {}", e.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HttpStatusCodeEnum.MALL_INTERNAL_SERVER_ERROR.getMessage());
    }
}
