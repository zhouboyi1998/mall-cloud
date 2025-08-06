package com.cafe.infrastructure.caffeine.annotation;

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
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/7/31 11:11
 * @Description: Caffeine 缓存注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface CaffeineCache {

    @AliasFor(value = "cacheName")
    String value() default StringConstant.EMPTY;

    @AliasFor(value = "value")
    String cacheName() default StringConstant.EMPTY;

    String cacheKey() default StringConstant.EMPTY;

    long expireTime() default -1L;

    TimeUnit expireUnit() default TimeUnit.SECONDS;

    String condition() default StringConstant.EMPTY;
}
