package com.cafe.common.redis.aspect;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.redis.annotation.ResultCache;
import com.cafe.common.util.aop.AOPUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
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
 * @Description: 接口返回结果缓存切面类
 */
@Aspect
@Order
@Component
@Profile(value = {AppConstant.DEV, AppConstant.TEST, AppConstant.DOCKER})
public class ResultCacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultCacheAspect.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ResultCacheAspect(@Qualifier(value = "redisTemplate4JavaValue") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置 @ResultCache 注解为切入点
     */
    @Pointcut(value = "@annotation(com.cafe.common.redis.annotation.ResultCache)")
    public void resultCache() {

    }

    @Around(value = "resultCache()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取目标类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取目标方法
        Method method = signature.getMethod();
        // 获取目标方法名
        String methodName = method.getName();
        // 获取注解
        ResultCache resultCache = AnnotationUtils.getAnnotation(method, ResultCache.class);
        Assert.notNull(resultCache, "Unable to get @ResultCache annotation!");
        // 获取参数名列表
        List<String> keyList = Arrays.stream(signature.getParameterNames()).collect(Collectors.toList());
        // 获取参数值列表
        List<Object> valueList = Arrays.stream(proceedingJoinPoint.getArgs()).collect(Collectors.toList());

        // 组装 Redis 缓存的 Key
        StringBuilder key = new StringBuilder()
            .append(RedisConstant.RESULT_PREFIX)
            .append(StringUtils.hasText(resultCache.name()) ? resultCache.name() : className + StringConstant.POINT + methodName)
            .append(StringConstant.COLON)
            .append(StringUtils.hasText(resultCache.key()) ? valueList.get(keyList.indexOf(resultCache.key())) : AOPUtil.argument(proceedingJoinPoint).hashCode());

        try {
            // 进入连接点
            Object result = proceedingJoinPoint.proceed();
            // 方法执行正常, 将返回值存入 Redis 缓存中
            long timeout = resultCache.timeout();
            if (timeout < IntegerConstant.ZERO) {
                redisTemplate.opsForValue().set(key.toString(), result);
            } else {
                redisTemplate.opsForValue().set(key.toString(), result, timeout, resultCache.unit());
            }
            return result;
        } catch (Exception e) {
            // 打印异常信息
            LOGGER.error("ResultCacheAspect.doAround(): class -> {}, method -> {}, message -> {}", className, methodName, e.getMessage(), e);
            // 方法执行异常, 返回缓存在 Redis 中的上一次执行结果
            return redisTemplate.opsForValue().get(key.toString());
        }
    }
}
