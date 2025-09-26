package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.infrastructure.caffeine.support.ExpirePolicy;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/9/26 14:46
 * @Description: Caffeine 缓存过期时间注解
 */
public @interface Expire {

    long expireTime();

    TimeUnit expireUnit() default TimeUnit.SECONDS;

    ExpirePolicy expirePolicy() default ExpirePolicy.EXPIRE_AFTER_WRITE;
}
