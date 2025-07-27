package com.cafe.foundation.aspect;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.foundation.annotation.SensitiveWordValidation;
import com.cafe.foundation.feign.SensitiveFeign;
import com.cafe.starter.boot.condition.ConditionalOnService;
import com.cafe.starter.boot.model.exception.BusinessException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.aspect
 * @Author: zhouboyi
 * @Date: 2025/7/28 22:39
 * @Description: 方法参数敏感词校验切面类
 */
@Slf4j
@RequiredArgsConstructor
@Order(value = -1000)
@Aspect
@Component
@ConditionalOnService(excludeServices = ServiceConstant.MALL_FOUNDATION)
public class SensitiveWordValidationAspect {

    private final SensitiveFeign sensitiveFeign;

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.foundation.annotation.SensitiveWordValidation)")
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
        SensitiveWordValidation sensitiveWordValidation = AnnotationUtils.getAnnotation(method, SensitiveWordValidation.class);
        Assert.notNull(sensitiveWordValidation, "Unable to get @SensitiveWordValidation annotation!");
        // 获取被校验的文本字段路径
        String textFieldPath = sensitiveWordValidation.textFieldPath();

        // 获取被校验的文本字段值
        Object text = AOPUtil.findArgument(proceedingJoinPoint, textFieldPath);
        if (text instanceof String) {
            // 校验文本中是否包含敏感词
            Boolean hasSensitiveWord = Optional.ofNullable(sensitiveFeign.validateText((String) text))
                .map(ResponseEntity::getBody)
                .orElse(Boolean.FALSE);
            if (hasSensitiveWord) {
                throw new BusinessException(HttpStatusEnum.SENSITIVE_WORD_FOUND);
            }
        }

        // 继续执行目标方法
        return proceedingJoinPoint.proceed();
    }
}
