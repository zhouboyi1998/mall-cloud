package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.infrastructure.caffeine.support.ExpirePolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/9/26 14:46
 * @Description: Caffeine 缓存过期时间注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {})
public @interface Expire {

    long expireTime();

    TimeUnit expireUnit() default TimeUnit.SECONDS;

    ExpirePolicy expirePolicy() default ExpirePolicy.EXPIRE_AFTER_WRITE;
}
