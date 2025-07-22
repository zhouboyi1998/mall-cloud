package com.cafe.infrastructure.redis.annotation;

import com.cafe.common.constant.pool.StringConstant;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.redis.annotation
 * @Author: zhouboyi
 * @Date: 2023/7/4 17:20
 * @Description: 异常应急返回结果缓存注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface FallbackCache {

    @AliasFor(value = "name")
    String value() default StringConstant.EMPTY;

    @AliasFor(value = "value")
    String name() default StringConstant.EMPTY;

    String key() default StringConstant.EMPTY;

    long timeout() default -1L;

    TimeUnit unit() default TimeUnit.SECONDS;

    String condition() default StringConstant.EMPTY;
}
