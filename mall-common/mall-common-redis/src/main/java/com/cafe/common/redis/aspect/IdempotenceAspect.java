package com.cafe.common.redis.aspect;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.redis.annotation.Idempotence;
import com.cafe.common.util.aop.AOPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.redis.aspect
 * @Author: zhouboyi
 * @Date: 2023/8/4 15:08
 * @Description: 接口幂等性切面类
 */
@Aspect
@Order
@Component
@Profile(value = {AppConstant.DEV, AppConstant.TEST, AppConstant.DOCKER})
public class IdempotenceAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public IdempotenceAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置 @Idempotence 注解为切入点
     */
    @Pointcut(value = "@annotation(com.cafe.common.redis.annotation.Idempotence)")
    public void idempotence() {

    }

    @Before(value = "idempotence()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取目标类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取目标方法
        Method method = signature.getMethod();
        // 获取目标方法名
        String methodName = method.getName();

        // 获取 HTTP 请求
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .map(attributes -> (ServletRequestAttributes) attributes)
            .map(ServletRequestAttributes::getRequest)
            .orElseThrow(NullPointerException::new);
        // 获取访问令牌
        String accessToken = request.getHeader(RequestConstant.Header.AUTHORIZATION)
            .replace(RequestConstant.Header.BEARER_PREFIX, StringConstant.EMPTY);

        // 获取注解
        Idempotence idempotence = AnnotationUtils.getAnnotation(method, Idempotence.class);
        Assert.notNull(idempotence, "Unable to get @Idempotence annotation!");

        // 组装 Redis 缓存的 Key (方法全限定名 + 参数 Hash 值 + 访问令牌)
        StringBuilder key = new StringBuilder()
            .append(RedisConstant.IDEMPOTENCE_PREFIX).append(className)
            .append(StringConstant.POINT).append(methodName)
            .append(StringConstant.COLON).append(AOPUtil.argument(joinPoint).hashCode())
            .append(StringConstant.COLON).append(accessToken);

        // 判断是否重复提交
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key.toString()))) {
            throw new RuntimeException("Do not repeat submit!");
        } else {
            redisTemplate.opsForValue().set(key.toString(), StringConstant.EMPTY, idempotence.intervalTime(), idempotence.unit());
        }
    }
}
