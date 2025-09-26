package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.infrastructure.caffeine.support.InvalidCacheLoader;
import com.github.benmanes.caffeine.cache.CacheLoader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/9/26 14:47
 * @Description: Caffeine 缓存刷新注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {})
public @interface Refresh {

    long refreshInterval() default -1L;

    TimeUnit refreshUnit() default TimeUnit.SECONDS;

    Class<? extends CacheLoader<String, Object>> cacheLoader() default InvalidCacheLoader.class;
}
