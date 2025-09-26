package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.common.constant.pool.StringConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/9/26 14:45
 * @Description: Caffeine 缓存信息注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {})
public @interface Info {

    String cacheName() default StringConstant.EMPTY;

    String cacheKey() default StringConstant.EMPTY;

    String condition() default StringConstant.EMPTY;
}
