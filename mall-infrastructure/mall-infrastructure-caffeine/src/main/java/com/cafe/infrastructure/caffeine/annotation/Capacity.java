package com.cafe.infrastructure.caffeine.annotation;

import com.cafe.infrastructure.caffeine.support.MaximumPolicy;
import com.cafe.infrastructure.caffeine.support.SimpleWeigher;
import com.github.benmanes.caffeine.cache.Weigher;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.annotation
 * @Author: zhouboyi
 * @Date: 2025/9/26 14:46
 * @Description: Caffeine 缓存容量注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {})
public @interface Capacity {

    int initialCapacity() default -1;

    MaximumPolicy maximumPolicy() default MaximumPolicy.MAXiMUM_SIZE;

    long maximumSize() default -1L;

    long maximumWeight() default -1L;

    Class<? extends Weigher<String, Object>> weigher() default SimpleWeigher.class;
}
