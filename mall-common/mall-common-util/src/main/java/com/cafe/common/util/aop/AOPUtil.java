package com.cafe.common.util.aop;

import com.cafe.common.jackson.util.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        // 存储请求参数的集合
        Map<String, Object> argumentMap = new HashMap<>(8);

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
            if (value instanceof HttpServletRequest || value instanceof HttpServletResponse) {
                // 跳过 HttpServlet 请求/响应类型参数
                continue;
            }
            // 存储参数到集合中
            if (value instanceof MultipartFile) {
                // 文件类型的参数, 存储文件名称
                argumentMap.put(keyList.get(i), ((MultipartFile) value).getOriginalFilename());
            } else {
                // 其它类型直接存储
                argumentMap.put(keyList.get(i), value);
            }
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
}
