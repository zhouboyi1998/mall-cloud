package com.cafe.infrastructure.redis.annotation;

import com.cafe.common.constant.pool.LongConstant;
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
 * @Date: 2023/8/4 15:06
 * @Description: 接口幂等性注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Idempotence {

    @AliasFor(value = "intervalTime")
    long value() default LongConstant.TWO;

    @AliasFor(value = "value")
    long intervalTime() default LongConstant.TWO;

    TimeUnit unit() default TimeUnit.SECONDS;
}
