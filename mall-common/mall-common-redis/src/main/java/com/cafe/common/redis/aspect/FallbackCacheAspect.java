package com.cafe.common.redis.aspect;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.redis.annotation.FallbackCache;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.common.util.json.JacksonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.redis.aspect
 * @Author: zhouboyi
 * @Date: 2023/7/4 17:25
 * @Description: 异常应急返回结果缓存切面类
 */
@Slf4j
@Order
@Aspect
@Component
public class FallbackCacheAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FallbackCacheAspect(@Qualifier(value = "redisTemplate4JavaValue") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.common.redis.annotation.FallbackCache)")
    public void pointcut() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @SneakyThrows
    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取目标类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取目标方法
        Method method = signature.getMethod();
        // 获取目标方法名
        String methodName = method.getName();
        // 获取注解
        FallbackCache fallbackCache = AnnotationUtils.getAnnotation(method, FallbackCache.class);
        Assert.notNull(fallbackCache, "Unable to get @FallbackCache annotation!");
        // 获取参数名列表
        List<String> keyList = Arrays.stream(signature.getParameterNames()).collect(Collectors.toList());
        // 获取参数值列表
        List<Object> valueList = Arrays.stream(proceedingJoinPoint.getArgs()).collect(Collectors.toList());

        // 获取缓存名称 (如果注解配置的缓存名称为空, 则使用目标类的全限定名 + 目标方法的名称)
        String cacheName = StringUtils.hasText(fallbackCache.name()) ? fallbackCache.name() : className + StringConstant.POINT + methodName;
        // 获取缓存 Key (如果注解配置的缓存 Key 为空, 则使用目标方法的参数列表 JSON 字符串 hashCode)
        Integer cacheKey = StringUtils.hasText(fallbackCache.key())
            ? JacksonUtil.writeValueAsString(valueList.get(keyList.indexOf(fallbackCache.key()))).hashCode()
            : AOPUtil.findArgumentString(proceedingJoinPoint).hashCode();
        // 组装 Redis 缓存的完整 Key (缓存名称 + 缓存 Key)
        StringBuilder key = new StringBuilder()
            .append(RedisConstant.FALLBACK_PREFIX).append(cacheName)
            .append(StringConstant.COLON).append(cacheKey);

        try {
            // 进入连接点
            Object result = proceedingJoinPoint.proceed();
            // 方法执行正常, 将返回值存入 Redis 缓存中
            long timeout = fallbackCache.timeout();
            if (timeout < IntegerConstant.ZERO) {
                redisTemplate.opsForValue().set(key.toString(), result);
            } else {
                redisTemplate.opsForValue().set(key.toString(), result, timeout, fallbackCache.unit());
            }
            return result;
        } catch (Exception e) {
            // 打印异常信息
            log.error("FallbackCacheAspect.doAround(): class -> {}, method -> {}, message -> {}", className, methodName, e.getMessage(), e);
            // 方法执行异常, 返回缓存在 Redis 中的上一次执行结果
            return redisTemplate.opsForValue().get(key.toString());
        }
    }
}
