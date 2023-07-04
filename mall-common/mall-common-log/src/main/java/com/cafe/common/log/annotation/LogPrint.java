package com.cafe.common.log.annotation;

import com.cafe.common.constant.pool.StringConstant;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.annotation
 * @Author: zhouboyi
 * @Date: 2022/9/16 9:19
 * @Description: 接口日志打印注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface LogPrint {

    @AliasFor(value = "description")
    String value() default StringConstant.EMPTY;

    @AliasFor(value = "value")
    String description() default StringConstant.EMPTY;
}
