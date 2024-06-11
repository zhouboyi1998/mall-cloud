package com.cafe.common.log.aspect;

import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.lang.id.Snowflake;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.log.model.Log;
import com.cafe.common.util.aop.AOPUtil;
import com.cafe.common.util.json.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationUtils;
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
 * @Description: 接口日志打印切面类
 */
@Profile(value = {AppConstant.DEV, AppConstant.TEST})
@Order
@Aspect
@Component
public class LogPrintAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrintAspect.class);

    /**
     * 日志模型
     */
    private final Log log = new Log();

    private final Snowflake snowflake = new Snowflake(0, 0);

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.cafe.common.log.annotation.LogPrint)")
    public void logPrint() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 连接点
     * @return
     * @throws Throwable
     */
    @Around(value = "logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 为每一个请求生成雪花唯一 ID
        Long requestId = snowflake.nextId();
        // 将请求 ID 存储到日志上下文 (SLF4J MDC) 中
        MDC.put(FieldConstant.REQUEST_ID, String.valueOf(requestId));

        // 获取进入连接点之前的时间
        Long startTime = System.currentTimeMillis();
        // 进入连接点 (执行目标方法, 获取目标方法的响应结果)
        Object result = proceedingJoinPoint.proceed();
        // 计算执行耗时
        Long duration = System.currentTimeMillis() - startTime;

        // 响应结果
        LOGGER.info("Result      : {}", JacksonUtil.writeValueAsString(result));
        // 执行耗时
        LOGGER.info("Duration    : {} ms", duration);

        // 组装响应相关信息到日志模型中
        log.setResult(result).setDuration(duration);
        // 完整的接口日志
        LOGGER.info(JacksonUtil.writeValueAsString(log));

        // 返回目标方法的响应结果
        return result;
    }

    /**
     * 前置通知
     *
     * @param joinPoint 连接点
     */
    @Before(value = "logPrint()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取 HttpServletRequest 对象
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .map(attributes -> (ServletRequestAttributes) attributes)
            .map(ServletRequestAttributes::getRequest)
            .orElseThrow(NullPointerException::new);

        // 获取请求相关信息
        String description = description(joinPoint);
        String source = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String type = request.getMethod();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        String argument = AOPUtil.argument(joinPoint);

        // 描述信息
        LOGGER.info("Description : {}", description);
        // 来源 IP
        LOGGER.info("Source      : {}", source);
        // 请求 URL
        LOGGER.info("URL         : {}", url);
        // 请求类型
        LOGGER.info("Type        : {}", type);
        // 控制器类
        LOGGER.info("Class       : {}", clazz);
        // 执行方法
        LOGGER.info("Method      : {}", method);
        // 请求参数
        LOGGER.info("Argument    : {}", argument);

        // 组装请求相关信息到日志模型中
        log.setDescription(description).setSource(source).setUrl(url).setType(type).setClazz(clazz).setMethod(method).setArgument(argument);
    }

    /**
     * 获取日志打印注解的描述信息
     *
     * @param joinPoint 连接点
     * @return
     */
    private String description(JoinPoint joinPoint) {
        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取目标方法
        Method method = signature.getMethod();
        // 获取日志打印注解 (必须使用 Spring 提供的工具获取注解, 否则无法获取别名配置)
        LogPrint logPrint = AnnotationUtils.getAnnotation(method, LogPrint.class);
        // 返回日志打印注解的描述信息
        return Optional.ofNullable(logPrint).map(LogPrint::description).orElse(StringConstant.EMPTY);
    }
}
