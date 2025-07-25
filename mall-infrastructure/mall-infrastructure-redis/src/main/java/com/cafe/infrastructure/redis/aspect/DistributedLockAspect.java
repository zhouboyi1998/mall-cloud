package com.cafe.infrastructure.redis.aspect;

import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.infrastructure.redis.annotation.DistributedLock;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.redis.aspect
 * @Author: zhouboyi
 * @Date: 2025/7/25 10:09
 * @Description: 分布式锁切面类
 */
@Slf4j
@RequiredArgsConstructor
@Order(value = Integer.MIN_VALUE)
@Aspect
@Component
public class DistributedLockAspect {

    /**
     * 释放分布式锁的 Lua 脚本
     */
    private static final DefaultRedisScript<Long> REDIS_SCRIPT = new DefaultRedisScript<>("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Long.class);

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 雪花ID生成器
     */
    private final Snowflake snowflake = new Snowflake(1, 0);

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.infrastructure.redis.annotation.DistributedLock)")
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
        // 获取注解
        DistributedLock distributedLock = AnnotationUtils.getAnnotation(method, DistributedLock.class);
        Assert.notNull(distributedLock, "Unable to get @DistributedLock annotation!");
        // 获取注解属性
        String lockKey = distributedLock.lockKey();
        long expireTime = distributedLock.expireTime();

        // 生成锁键 (优先使用注解配置的锁键, 默认使用目标方法的全限定名)
        String finalLockKey = StringUtils.hasText(lockKey) ? lockKey : AOPUtil.resolveTargetMethodFullQualifiedName(proceedingJoinPoint);
        // 组装分布式锁键
        final String distributedLockKey = RedisConstant.DISTRIBUTED_LOCK_PREFIX + finalLockKey;
        // 生成分布式锁值, 用于判断锁的持有者
        final Long distributedLockValue = snowflake.nextId();

        try {
            // 使用 Redis 的 SETNX 命令实现分布式锁
            Boolean absent = expireTime < 0 ?
                Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(distributedLockKey, distributedLockValue)).orElse(Boolean.FALSE) :
                Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(distributedLockKey, distributedLockValue, expireTime, distributedLock.expireUnit())).orElse(Boolean.FALSE);
            if (absent) {
                // 如果分布式锁键不存在, 放行本次请求
                log.info("Distributed lock acquired successfully! distributed lock key -> {}, value -> {}", distributedLockKey, distributedLockValue);
                return proceedingJoinPoint.proceed();
            } else {
                // 如果分布式锁键已存在, 跳过本次请求
                log.info("Distributed lock acquisition failed! distributed lock key -> {}, value -> {}", distributedLockKey, distributedLockValue);
                return null;
            }
        } finally {
            // 使用 Lua 脚本释放锁, 确保只有锁的持有者可以释放锁
            if (Optional.ofNullable(redisTemplate.execute(REDIS_SCRIPT, Collections.singletonList(distributedLockKey), distributedLockValue)).map(v -> v > 0L).orElse(Boolean.FALSE)) {
                log.info("Distributed lock release! distributed lock key -> {}, value -> {}", distributedLockKey, distributedLockValue);
            }
        }
    }
}
