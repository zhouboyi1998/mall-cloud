package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.infrastructure.caffeine.support.ExpirePolicy;
import com.cafe.infrastructure.caffeine.support.InvalidCacheLoader;
import com.cafe.infrastructure.caffeine.support.MaximumPolicy;
import com.cafe.infrastructure.caffeine.support.SimpleWeigher;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Weigher;
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

    int initialCapacity() default -1;

    MaximumPolicy maximumPolicy() default MaximumPolicy.MAXiMUM_SIZE;

    long maximumSize() default -1L;

    long maximumWeight() default -1L;

    Class<? extends Weigher<String, Object>> weigher() default SimpleWeigher.class;

    long expireTime();

    TimeUnit expireUnit() default TimeUnit.SECONDS;

    ExpirePolicy expirePolicy() default ExpirePolicy.EXPIRE_AFTER_WRITE;

    long refreshInterval() default -1L;

    TimeUnit refreshUnit() default TimeUnit.SECONDS;

    Class<? extends CacheLoader<String, Object>> cacheLoader() default InvalidCacheLoader.class;

    String condition() default StringConstant.EMPTY;
}
