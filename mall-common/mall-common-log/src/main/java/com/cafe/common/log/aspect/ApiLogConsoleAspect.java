package com.cafe.common.log.aspect;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.util.annotation.AnnotationUtil;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.common.util.builder.ToStringStyleHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.aspect
 * @Author: zhouboyi
 * @Date: 2022/9/16 9:30
 * @Description: 接口日志控制台打印切面
 */
@Profile(value = {AppConstant.DEV, AppConstant.TEST})
@Slf4j
@Order(value = 1)
@Aspect
@Component
public class ApiLogConsoleAspect {

    /**
     * 雪花ID生成器
     */
    private final Snowflake snowflake = new Snowflake(0, 0);

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.common.log.annotation.ApiLogPrint)")
    public void pointcut() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 连接点
     * @return 响应结果
     */
    @SneakyThrows
    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        // 生成请求ID
        Long requestId = snowflake.nextId();
        // 将请求ID存储到 Slf4J 日志上下文 (MDC) 中
        MDC.put(FieldConstant.REQUEST_ID, String.valueOf(requestId));

        // 获取进入连接点之前的时间
        long startTime = System.nanoTime();
        // 进入连接点 (执行目标方法, 获取目标方法的响应结果)
        Object result = proceedingJoinPoint.proceed();
        // 计算执行耗时
        Long duration = System.nanoTime() - startTime;

        // 打印执行耗时
        log.info("@Around -> Duration: {} ns", duration);
        // 返回目标方法的响应结果
        return result;
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

        // 获取方法对象
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 打印描述信息
        log.info("@Before -> Description: {}", AnnotationUtil.findAnnotationField(method, ApiLogPrint.class, ApiLogPrint::description));
        // 打印来源IP
        log.info("@Before -> Source: {}", request.getRemoteAddr());
        // 打印请求URL
        log.info("@Before -> URL: {}", request.getRequestURL().toString());
        // 打印请求类型
        log.info("@Before -> Type: {}", request.getMethod());
        // 打印控制器类
        log.info("@Before -> Class: {}", joinPoint.getSignature().getDeclaringTypeName());
        // 打印执行方法
        log.info("@Before -> Method: {}", joinPoint.getSignature().getName());
        // 打印请求参数
        log.info("@Before -> Argument: {}", AOPUtil.findArgumentString(joinPoint));
    }

    /**
     * 响应通知
     *
     * @param joinPoint 连接点
     * @param result    响应结果
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 打印响应结果
        log.info("@AfterReturning -> Result: {}", ToStringBuilder.reflectionToString(result, ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE));
    }

    /**
     * 异常通知
     *
     * @param joinPoint 连接点
     * @param throwable 异常信息
     */
    @AfterThrowing(value = "pointcut()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        // 打印异常信息
        log.warn("@AfterThrowing -> Throwable: {}", ToStringBuilder.reflectionToString(throwable, ToStringStyleHolder.JSON_STYLE_WITHOUT_UNICODE));
    }
}
