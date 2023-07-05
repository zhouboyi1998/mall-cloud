package com.cafe.common.log.aspect;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.app.AppConstant;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.log.annotation.LogPrint;
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
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
@Profile(value = {AppConstant.DEV, AppConstant.TEST})
public class LogPrintAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrintAspect.class);

    /**
     * 配置 @LogPrint 注解为切入点
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
        // 为每一个请求生成 UUID 格式的 Request ID
        String requestId = UUID.randomUUID().toString();
        // 将请求 Request ID 存储到日志上下文 (SLF4J MDC) 中
        MDC.put(FieldConstant.REQUEST_ID, requestId);
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
     */
    @Before(value = "logPrint()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取 HTTP 请求相关信息
        HttpServletRequest request = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .map(attributes -> (ServletRequestAttributes) attributes)
            .map(ServletRequestAttributes::getRequest)
            .orElseThrow(NullPointerException::new);
        // 请求来源 IP
        LOGGER.info("Source IP        : {}", request.getRemoteAddr());
        // 描述信息
        LOGGER.info("Description      : {}", description(joinPoint));
        // HTTP 请求 URL
        LOGGER.info("Request URL      : {}", request.getRequestURL());
        // HTTP 请求类型
        LOGGER.info("HTTP Method      : {}", request.getMethod());
        // 控制器类的全路径
        LOGGER.info("Class            : {}", joinPoint.getSignature().getDeclaringTypeName());
        // 执行方法
        LOGGER.info("Method           : {}", joinPoint.getSignature().getName());
        // 请求参数
        LOGGER.info("Request Argument : {}", argument(joinPoint));
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint 连接点
     * @return
     */
    private String argument(JoinPoint joinPoint) {
        // 存储请求参数
        StringBuilder args = new StringBuilder(StringConstant.LEFT_BRACE);

        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取参数名列表
        List<String> keyList = Arrays.stream(signature.getParameterNames()).collect(Collectors.toList());
        // 获取参数值列表
        List<Object> valueList = Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList());

        // 遍历组装成 JSON 格式
        for (int i = 0; i < valueList.size(); i++) {
            args.append(StringConstant.DOUBLE_QUOTATION_MARK).append(keyList.get(i))
                .append(StringConstant.DOUBLE_QUOTATION_MARK).append(StringConstant.COLON);
            Object value = valueList.get(i);
            if (value instanceof Number || value instanceof Character || value instanceof Boolean || value instanceof CharSequence) {
                // 基本类型、字符串类型的参数直接拼接
                args.append(StringConstant.DOUBLE_QUOTATION_MARK).append(value)
                    .append(StringConstant.DOUBLE_QUOTATION_MARK).append(StringConstant.COMMA);
            } else {
                // 引用类型的参数转换成 JSON 字符串再拼接
                args.append(JSONUtil.toJsonStr(value)).append(StringConstant.COMMA);
            }
        }
        // 删除最后一个逗号, 拼接右花括号
        Integer index = args.lastIndexOf(StringConstant.COMMA);
        if (index > IntegerConstant.MINUS_ONE) {
            args.deleteCharAt(index).append(StringConstant.RIGHT_BRACE);
        }
        return args.toString();
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
