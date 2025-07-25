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
 * @Date: 2025/7/25 10:09
 * @Description: 分布式锁注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface DistributedLock {

    @AliasFor(value = "expireTime")
    long value() default -1L;

    @AliasFor(value = "value")
    long expireTime() default -1L;

    TimeUnit expireUnit() default TimeUnit.SECONDS;

    String lockKey() default StringConstant.EMPTY;
}
