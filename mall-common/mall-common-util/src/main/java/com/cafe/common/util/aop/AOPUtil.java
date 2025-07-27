package com.cafe.common.util.aop;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.json.util.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.aop
 * @Author: zhouboyi
 * @Date: 2023/7/5 11:46
 * @Description: AOP 工具类
 */
public class AOPUtil {

    /**
     * 获取请求参数集合
     *
     * @param joinPoint 连接点
     * @return 请求参数集合
     */
    public static Map<String, Object> findArgumentMap(JoinPoint joinPoint) {
        // 使用 TreeMap 存储请求参数, 保证请求参数按参数名排序
        Map<String, Object> argumentMap = new TreeMap<>();

        // 获取目标签名, 转换成方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取参数名列表
        List<String> keyList = Arrays.stream(signature.getParameterNames()).collect(Collectors.toList());
        // 获取参数值列表
        List<Object> valueList = Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList());

        // 遍历组装成 JSON 格式
        for (int i = 0; i < valueList.size(); i++) {
            // 获取参数值
            Object value = valueList.get(i);
            // HttpServletRequest、HttpServletResponse 类型的参数, 跳过
            if (value instanceof HttpServletRequest || value instanceof HttpServletResponse) {
                continue;
            }
            // MultipartFile 类型的参数, 存储文件名称
            if (value instanceof MultipartFile) {
                argumentMap.put(keyList.get(i), ((MultipartFile) value).getOriginalFilename());
                continue;
            }
            // 其它类型的参数, 直接存储参数值
            argumentMap.put(keyList.get(i), value);
        }

        // 返回请求参数集合
        return argumentMap;
    }

    /**
     * 获取请求参数 JSON 字符串
     *
     * @param joinPoint 连接点
     * @return 请求参数 JSON 字符串
     */
    public static String findArgumentString(JoinPoint joinPoint) {
        return JacksonUtil.writeValueAsString(findArgumentMap(joinPoint));
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint    连接点
     * @param argumentPath 请求参数路径
     * @return 请求参数
     */
    public static Object findArgument(JoinPoint joinPoint, String argumentPath) {
        // 分割请求参数路径, 获取各级参数名称
        String[] argumentNames = argumentPath.split(StringConstant.POINT_REGEX);
        if (ObjectUtils.isEmpty(argumentNames)) {
            return null;
        }

        // 获取方法参数集合
        Map<String, Object> argumentMap = findArgumentMap(joinPoint);
        // 获取一级参数
        Object argument = argumentMap.get(argumentNames[0]);
        if (argument == null) {
            return null;
        }

        try {
            // 递归查找参数值
            return recursiveFindArgument(argument, argumentNames, 1);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 递归获取请求参数
     *
     * @param currentArgumentValue 当前参数值
     * @param argumentNames        各级参数名称列表
     * @param nextArgumentIndex    下一级参数索引
     * @return 请求参数
     */
    public static Object recursiveFindArgument(Object currentArgumentValue, String[] argumentNames, int nextArgumentIndex) {
        // 如果已经处理完所有参数路径, 返回当前参数
        if (Objects.isNull(currentArgumentValue) || nextArgumentIndex >= argumentNames.length) {
            return currentArgumentValue;
        }
        // 获取当前参数的类对象
        Class<?> clazz = currentArgumentValue.getClass();

        // 获取下一级参数名
        String nextArgumentName = argumentNames[nextArgumentIndex];
        // 获取下一级参数
        Field argument = ReflectionUtils.findField(clazz, nextArgumentName);
        if (argument == null) {
            return null;
        }
        // 设置下一级参数可访问
        argument.setAccessible(true);
        // 获取下一级参数值
        Object nextArgumentValue = ReflectionUtils.getField(argument, currentArgumentValue);

        if (nextArgumentIndex == argumentNames.length - 1) {
            // 如果是最后一层参数, 返回参数值
            return nextArgumentValue;
        } else {
            // 否则, 递归查找再下一级参数值
            return recursiveFindArgument(nextArgumentValue, argumentNames, nextArgumentIndex + 1);
        }
    }

    /**
     * 解析目标类的全限定名
     *
     * @param joinPoint 连接点
     * @return 目标类的全限定名
     */
    public static String resolveTargetClassFullQualifiedName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    /**
     * 解析目标方法的全限定名
     *
     * @param joinPoint          连接点
     * @param withParameterTypes 是否包含参数类型
     * @return 目标方法的全限定名
     */
    public static String resolveTargetMethodFullQualifiedName(JoinPoint joinPoint, boolean withParameterTypes) {
        // 获取目标方法所在类的全限定名
        String className = resolveTargetClassFullQualifiedName(joinPoint);
        // 获取目标方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取目标方法名
        String methodName = method.getName();
        StringBuilder sb = new StringBuilder().append(className).append(StringConstant.HASH).append(methodName);

        // 判断是否需要包含参数类型
        if (withParameterTypes) {
            // 获取目标方法参数类型列表
            String parameterTypes = Arrays.stream(method.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.joining(StringConstant.COMMA));
            sb.append(StringConstant.LEFT_PARENTHESIS).append(parameterTypes).append(StringConstant.RIGHT_PARENTHESIS);
        }

        return sb.toString();
    }

    /**
     * 解析目标方法的全限定名
     *
     * @param joinPoint 连接点
     * @return 目标方法的全限定名
     */
    public static String resolveTargetMethodFullQualifiedName(JoinPoint joinPoint) {
        return resolveTargetMethodFullQualifiedName(joinPoint, true);
    }
}
