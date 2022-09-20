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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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

    private static final Logger logger = LoggerFactory.getLogger(LogPrintAspect.class);

    /**
     * 配置 @LogPrint 注解为切入点
     */
    @Pointcut("@annotation(com.cafe.common.log.annotation.LogPrint)")
    public void logPrint() {

    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取进入连接点之前的时间
        long startTime = System.currentTimeMillis();
        // 开始打印
        logger.info("============================== Start ==============================");
        // 进入连接点 (相当于调用目标方法, 目标方法的返回值就是出参)
        Object result = proceedingJoinPoint.proceed();
        // 响应出参
        logger.info("Response Args  : {}", JSONUtil.toJsonStr(result));
        // 执行耗时 (进入连接点之后的时间 - 进入连接点之前的时间)
        logger.info("Time Consume   : {} ms", System.currentTimeMillis() - startTime);
        // 结束打印
        logger.info("=============================== End ===============================");
        // 要返回目标方法的返回值, 否则目标方法的返回值就被截留在 Around Advice 中了
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
        // 获取请求相关信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 请求来源 IP
        logger.info("Source IP      : {}", request.getRemoteAddr());
        // 描述信息
        logger.info("Description    : {}", getLogPrintDescription(joinPoint));
        // 请求 URL
        logger.info("Request URL    : {}", request.getRequestURL().toString());
        // HTTP 请求类型
        logger.info("HTTP Method    : {}", request.getMethod());
        // 接口类全路径
        logger.info("Class          : {}", joinPoint.getSignature().getDeclaringTypeName());
        // 执行方法
        logger.info("Method         : {}", joinPoint.getSignature().getName());
        // 请求入参
        logger.info("Request Args   : {}", getRequestParams(joinPoint));
    }

    /**
     * 获取请求入参
     *
     * @param joinPoint 连接点
     * @return
     */
    private String getRequestParams(JoinPoint joinPoint) {
        // 存储入参的字符串
        String params = "";
        // 如果有入参, 遍历所有入参
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                // 获取入参
                Object arg = joinPoint.getArgs()[i];
                // 跳过 HTTP Servlet 相关参数和文件相关参数
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest) ||
                    (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                // 将参数转换为字符串并存储
                try {
                    // 如果是 Number 类型, 直接拼接, Hutool JSONUtil.toJsonStr() 方法不能转换 Number 类型
                    if (arg instanceof Number) {
                        params += "{" + arg + "}";
                    } else {
                        params += JSONUtil.toJsonStr(arg);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return params;
    }

    /**
     * 获取 @LogPrint 注解配置的描述信息
     *
     * @param joinPoint 连接点
     * @return
     */
    private String getLogPrintDescription(JoinPoint joinPoint) throws Exception {
        // 获取目标对象的类名
        String targetClassName = joinPoint.getTarget().getClass().getName();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取目标方法实参
        Object[] arguments = joinPoint.getArgs();
        // 获取目标对象的类
        Class targetClass = Class.forName(targetClassName);
        // 获取目标对象的类的所有方法
        Method[] methods = targetClass.getMethods();
        // 存储 @LogPrint 注解上配置的描述信息
        StringBuilder description = new StringBuilder();
        // 遍历所有方法
        for (Method method : methods) {
            // 如果找到目标方法, 获取方法 @LogPrint 注解上配置的描述信息
            if (method.getName().equals(methodName)) {
                // 获取方法所有参数的类型
                Class[] clazzs = method.getParameterTypes();
                // 判断形参个数和实参个数是否相等 (可能有同名的重载方法)
                if (clazzs.length == arguments.length) {
                    // 获取 @LogPrint 注解上配置的描述信息
                    description.append(method.getAnnotation(LogPrint.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
