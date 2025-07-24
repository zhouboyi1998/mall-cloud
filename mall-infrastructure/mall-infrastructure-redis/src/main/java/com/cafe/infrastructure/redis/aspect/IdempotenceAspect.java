package com.cafe.infrastructure.redis.aspect;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.infrastructure.redis.annotation.Idempotence;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.redis.aspect
 * @Author: zhouboyi
 * @Date: 2023/8/4 15:08
 * @Description: 幂等切面类
 */
@Slf4j
@RequiredArgsConstructor
@Order(value = Integer.MIN_VALUE)
@Aspect
@Component
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class IdempotenceAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.infrastructure.redis.annotation.Idempotence)")
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
        // 获取访问令牌
        String accessTokenMD5 = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .map(attributes -> (ServletRequestAttributes) attributes)
            .map(ServletRequestAttributes::getRequest)
            .map(request -> request.getHeader(RequestConstant.Header.AUTHORIZATION))
            .map(authorization -> authorization.replaceFirst(AuthorizationConstant.TokenType.BEARER, StringConstant.EMPTY))
            .map(String::trim)
            // 使用 MD5 算法获取低冲突概率的访问令牌 Hash 值
            .map(String::getBytes)
            .map(DigestUtils::md5DigestAsHex)
            .orElse(null);
        // 如果访问令牌为空, 不进行幂等性校验, 直接放行
        if (Objects.isNull(accessTokenMD5)) {
            return proceedingJoinPoint.proceed();
        }

        // 获取目标方法
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        // 获取注解
        Idempotence idempotence = AnnotationUtils.getAnnotation(method, Idempotence.class);
        Assert.notNull(idempotence, "Unable to get @Idempotence annotation!");

        // 获取目标方法的全限定名
        String targetMethodFQN = AOPUtil.resolveTargetMethodFullQualifiedName(proceedingJoinPoint);
        // 获取请求参数 JSON 字符串
        String argumentString = AOPUtil.findArgumentString(proceedingJoinPoint);
        // 使用 MD5 算法获取低冲突概率的请求参数 Hash 值
        String argumentMD5 = DigestUtils.md5DigestAsHex(argumentString.getBytes());
        // 组装幂等键
        final String idempotenceKey = RedisConstant.IDEMPOTENCE_PREFIX + targetMethodFQN +
            StringConstant.COLON + argumentMD5 +
            StringConstant.COLON + accessTokenMD5;

        // 使用 Redis 的 SETNX 命令实现分布式锁
        Boolean absent = Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(idempotenceKey, StringConstant.EMPTY, idempotence.intervalTime(), idempotence.intervalUnit())).orElse(Boolean.FALSE);
        if (absent) {
            // 如果幂等键不存在, 放行本次请求
            return proceedingJoinPoint.proceed();
        } else {
            // 如果幂等键已存在, 判断本次请求为重复提交
            log.warn("IdempotenceAspect.doAround(): Duplicate request! idempotence key -> {}", idempotenceKey);
            throw new RuntimeException("Do not repeat submit!");
        }
    }
}
