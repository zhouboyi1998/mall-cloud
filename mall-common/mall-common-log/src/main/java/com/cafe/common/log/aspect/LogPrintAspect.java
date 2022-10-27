package com.cafe.common.log.aspect;

import cn.hutool.json.JSONUtil;
import com.cafe.common.log.annotation.LogPrint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.aspect
 * @Author: zhouboyi
 * @Date: 2022/9/16 9:30
 * @Description: 接口日志打印切面类
 */
@Aspect
@Component
@Profile({"dev", "test"})
public class LogPrintAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrintAspect.class);

    /**
     * 配置 @LogPrint 注解为切入点
     */
    @Pointcut("@annotation(com.cafe.common.log.annotation.LogPrint)")
    public void logPrint() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 连接点
     * @return
     * @throws Throwable
     */
    @Around("logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 为每一个请求生成 UUID 格式的 Request ID
        String requestId = UUID.randomUUID().toString();
        // 将请求 Request ID 存储到日志上下文 (SLF4J MDC) 中
        MDC.put("requestId", requestId);
        // 开始打印
        LOGGER.info("==================== start ====================");
        // 获取进入连接点之前的时间
        Long startTime = System.currentTimeMillis();
        // 进入连接点 (执行目标方法, 获取目标方法的响应结果)
        Object result = proceedingJoinPoint.proceed();
        // 响应结果
        LOGGER.info("Response result  : {}", JSONUtil.toJsonStr(result));
        // 执行耗时 (进入连接点之后的时间 - 进入连接点之前的时间)
        LOGGER.info("Time Consuming   : {} ms", System.currentTimeMillis() - startTime);
        // 结束打印
        LOGGER.info("===================== end =====================");
        // 返回目标方法的响应结果
        return result;
    }

    /**
     * 前置通知
     *
     * @param joinPoint 连接点
     * @throws Throwable
     */
    @Before("logPrint()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 获取 HTTP 请求相关信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 请求来源 IP
        LOGGER.info("Source IP        : {}", request.getRemoteAddr());
        // 描述信息
        LOGGER.info("Description      : {}", getLogPrintDescription(joinPoint));
        // HTTP 请求 URL
        LOGGER.info("Request URL      : {}", request.getRequestURL().toString());
        // HTTP 请求类型
        LOGGER.info("HTTP Method      : {}", request.getMethod());
        // 控制器类的全路径
        LOGGER.info("Class            : {}", joinPoint.getSignature().getDeclaringTypeName());
        // 执行方法
        LOGGER.info("Method           : {}", joinPoint.getSignature().getName());
        // 请求参数
        LOGGER.info("Request Argument : {}", getRequestParams(joinPoint));
    }

    /**
     * 获取请求入参
     *
     * @param joinPoint 连接点
     * @return
     */
    private String getRequestParams(JoinPoint joinPoint) {
        // 存储请求入参
        StringBuilder params = new StringBuilder();
        // 获取所有入参, 去除 HTTP 相关参数、文件相关参数
        List<Object> args = Arrays
            .stream(joinPoint.getArgs())
            .filter(arg -> !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) &&
                !(arg instanceof MultipartFile) && !(arg instanceof MultipartFile[]))
            .collect(Collectors.toList());
        // 遍历所有入参, 将参数转换为字符串, 使用 & 符号分隔
        for (Object arg : args) {
            // 直接拼接基本数据类型的参数, 使用 JSON 工具将其它类型的参数转换为字符串再拼接
            if (arg instanceof Number || arg instanceof Character || arg instanceof Boolean) {
                params.append(arg).append("&");
            } else {
                params.append(JSONUtil.toJsonStr(arg)).append("&");
            }
        }
        // 删除最后一个 & 符号
        Integer index = params.lastIndexOf("&");
        if (index > -1) {
            params.deleteCharAt(index);
        }
        return params.toString();
    }

    /**
     * 获取 @LogPrint 注解中配置的描述信息
     *
     * @param joinPoint 连接点
     * @return
     * @throws ClassNotFoundException
     */
    private String getLogPrintDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        // 获取目标对象的类名
        String targetClassName = joinPoint.getTarget().getClass().getName();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取目标方法实参
        Object[] arguments = joinPoint.getArgs();
        // 获取目标对象的类
        Class<?> targetClass = Class.forName(targetClassName);
        // 获取目标对象的类的所有方法
        Method[] methods = targetClass.getMethods();
        // 存储 @LogPrint 注解上配置的描述信息
        StringBuilder description = new StringBuilder();
        // 遍历所有方法
        for (Method method : methods) {
            // 如果找到目标方法, 获取方法 @LogPrint 注解上配置的描述信息
            if (method.getName().equals(methodName)) {
                // 判断形参个数和实参个数是否相等 (可能存在重载方法)
                if (method.getParameterCount() == arguments.length) {
                    // 获取 @LogPrint 注解上配置的描述信息
                    // 因为配置属性别名的注解 @AliasFor 是 Spring 提供的
                    // 必须使用 Spring 提供的 AnnotationUtils 获取注解, 才可以解析别名
                    // 如果直接使用 Method 获取注解, 无法解析别名 (配置了一对别名中的一个, 获取另一个时仍然为空或默认值)
                    description.append(AnnotationUtils.getAnnotation(method, LogPrint.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}