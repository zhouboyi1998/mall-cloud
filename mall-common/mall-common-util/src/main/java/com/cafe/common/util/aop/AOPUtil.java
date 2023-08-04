package com.cafe.common.util.aop;

import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
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
     * 获取请求参数
     *
     * @param joinPoint 连接点
     * @return
     */
    public static String argument(JoinPoint joinPoint) {
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
            args.append(StringConstant.DOUBLE_QUOTATION).append(keyList.get(i))
                .append(StringConstant.DOUBLE_QUOTATION).append(StringConstant.COLON);
            Object value = valueList.get(i);
            if (value instanceof Number || value instanceof Character || value instanceof Boolean || value instanceof CharSequence) {
                // 基本类型、字符串类型的参数直接拼接
                args.append(StringConstant.DOUBLE_QUOTATION).append(value)
                    .append(StringConstant.DOUBLE_QUOTATION).append(StringConstant.COMMA);
            } else if (value instanceof MultipartFile) {
                // 文件类型的参数, 拼接文件名称
                args.append(StringConstant.DOUBLE_QUOTATION).append(((MultipartFile) value).getOriginalFilename())
                    .append(StringConstant.DOUBLE_QUOTATION).append(StringConstant.COMMA);
            } else {
                // 引用类型的参数转换成 JSON 字符串再拼接
                args.append(JSONUtil.toJsonStr(value)).append(StringConstant.COMMA);
            }
        }
        // 删除最后一个逗号
        Integer index = args.lastIndexOf(StringConstant.COMMA);
        if (index > IntegerConstant.MINUS_ONE) {
            args.deleteCharAt(index);
        }
        // 拼接右花括号, 返回 JSON 格式参数
        return args.append(StringConstant.RIGHT_BRACE).toString();
    }
}
