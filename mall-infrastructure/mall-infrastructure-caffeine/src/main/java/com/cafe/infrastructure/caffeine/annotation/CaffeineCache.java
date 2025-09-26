package com.cafe.infrastructure.caffeine.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

    Info info() default @Info;

    Capacity capacity() default @Capacity;

    Expire expire();

    Refresh refresh() default @Refresh;
}
