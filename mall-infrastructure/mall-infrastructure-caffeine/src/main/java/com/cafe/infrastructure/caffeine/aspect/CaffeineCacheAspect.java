package com.cafe.infrastructure.caffeine.aspect;

import com.cafe.common.json.util.JacksonUtil;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.common.util.expression.SpELUtil;
import com.cafe.infrastructure.caffeine.annotation.CaffeineCache;
import com.cafe.infrastructure.caffeine.manager.CaffeineCacheManager;
import com.cafe.infrastructure.caffeine.support.ExpirePolicy;
import com.cafe.infrastructure.caffeine.support.MaximumPolicy;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Weigher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.caffeine.aspect
 * @Author: zhouboyi
 * @Date: 2025/7/31 11:12
 * @Description: Caffeine 缓存切面类
 */
@Slf4j
@RequiredArgsConstructor
@Order(value = Integer.MIN_VALUE)
@Aspect
@Component
public class CaffeineCacheAspect {

    private final CaffeineCacheManager caffeineCacheManager;

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.infrastructure.caffeine.annotation.CaffeineCache)")
    public void pointcut() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 连接点
     * @return 返回结果
     */
    @SneakyThrows
    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        // 获取目标方法
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        // 获取目标方法的参数列表
        Map<String, Object> argumentMap = AOPUtil.findArgumentMap(proceedingJoinPoint);
        // 获取注解
        CaffeineCache caffeineCache = AnnotationUtils.getAnnotation(method, CaffeineCache.class);
        Assert.notNull(caffeineCache, "Unable to get @CaffeineCache annotation!");

        // 获取注解的 condition 属性 (SpEL 表达式)
        String condition = caffeineCache.condition();
        // 如果表达式为空, 默认不跳过缓存逻辑; 如果表达式不为空, 根据表达式的评估结果决定是否跳过缓存逻辑
        if (StringUtils.hasText(condition)) {
            // 如果 SpEL 表达式的评估结果为 false, 跳过缓存逻辑, 直接执行方法
            if (Objects.equals(SpELUtil.evaluate(condition, argumentMap, Boolean.class), Boolean.FALSE)) {
                return proceedingJoinPoint.proceed();
            }
        }

        // 获取注解的缓存相关属性
        String cacheName = caffeineCache.cacheName();
        String cacheKey = caffeineCache.cacheKey();

        int initialCapacity = caffeineCache.initialCapacity();
        MaximumPolicy maximumPolicy = caffeineCache.maximumPolicy();
        long maximumSize = caffeineCache.maximumSize();
        long maximumWeight = caffeineCache.maximumWeight();
        Weigher<String, Object> weigher = caffeineCache.weigher().newInstance();

        long expireTime = caffeineCache.expireTime();
        TimeUnit expireUnit = caffeineCache.expireUnit();
        ExpirePolicy expirePolicy = caffeineCache.expirePolicy();

        long refreshInterval = caffeineCache.refreshInterval();
        TimeUnit refreshUnit = caffeineCache.refreshUnit();
        CacheLoader<String, Object> cacheLoader = caffeineCache.cacheLoader().newInstance();

        // 生成缓存名称 (如果注解配置的缓存名称为空, 默认使用目标方法的全限定名)
        final String finalCacheName = StringUtils.hasText(cacheName) ? cacheName : AOPUtil.resolveTargetMethodFullQualifiedName(proceedingJoinPoint, false);
        // 生成缓存键 (优先使用注解配置的缓存键获取参数值并生成 MD5, 默认使用整个参数列表生成 MD5)
        final String finalCacheKey = StringUtils.hasText(cacheKey) ?
            DigestUtils.md5DigestAsHex(JacksonUtil.writeValueAsString(argumentMap.get(cacheKey)).getBytes()) :
            DigestUtils.md5DigestAsHex(JacksonUtil.writeValueAsString(argumentMap).getBytes());

        // 获取缓存值
        Object cacheValue = caffeineCacheManager.getIfPresent(finalCacheName, finalCacheKey);
        if (Objects.nonNull(cacheValue)) {
            // 缓存命中, 返回缓存值
            return cacheValue;
        } else {
            // 缓存未命中, 执行方法获取结果
            Object result = proceedingJoinPoint.proceed();
            // 缓存方法的执行结果
            caffeineCacheManager.put(finalCacheName, finalCacheKey, result, initialCapacity, maximumPolicy, maximumSize, maximumWeight, weigher, expireTime, expireUnit, expirePolicy, refreshInterval, refreshUnit, cacheLoader);
            // 返回方法的执行结果
            return result;
        }
    }
}
