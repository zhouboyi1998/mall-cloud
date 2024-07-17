package com.cafe.common.log.aspect;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.log.model.ApiLog;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.common.util.json.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.aspect
 * @Author: zhouboyi
 * @Date: 2024/7/17 10:59
 * @Description: 接口日志消息打印切面
 */
@Profile(value = {AppConstant.DEV, AppConstant.TEST, AppConstant.PROD, AppConstant.DOCKER})
@Order(value = IntegerConstant.TWO)
@Aspect
@Component
public class ApiLogMessageAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLogMessageAspect.class);

    /**
     * 接口日志
     */
    private final ApiLog apiLog = new ApiLog();

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.common.log.annotation.ApiLogPrint)")
    public void pointcut() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint 连接点
     */
    @Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取 HttpServletRequest 对象
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .map(attributes -> (ServletRequestAttributes) attributes)
            .map(ServletRequestAttributes::getRequest)
            .orElseThrow(NullPointerException::new);

        // 添加请求相关信息到接口日志中
        apiLog.setDescription(ApiLogConsoleAspect.description(joinPoint))
            .setSource(request.getRemoteAddr())
            .setUrl(request.getRequestURL().toString())
            .setType(request.getMethod())
            .setClazz(joinPoint.getSignature().getDeclaringTypeName())
            .setMethod(joinPoint.getSignature().getName())
            .setArgument(AOPUtil.argument(joinPoint));
    }

    /**
     * 响应通知
     *
     * @param joinPoint 连接点
     * @param result    响应结果
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 添加响应结果到接口日志中
        apiLog.setResult(result);
    }

    /**
     * 异常通知
     *
     * @param joinPoint 连接点
     * @param throwable 异常信息
     */
    @AfterThrowing(value = "pointcut()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        // 添加异常信息到接口日志中
        apiLog.setThrowable(throwable);
    }

    /**
     * 后置通知
     *
     * @param joinPoint 连接点
     */
    @After(value = "pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        // 打印接口日志
        LOGGER.info("{}", JacksonUtil.writeValueAsString(apiLog));
    }
}
