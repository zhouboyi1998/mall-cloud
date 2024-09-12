package com.cafe.common.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.json
 * @Author: zhouboyi
 * @Date: 2023/8/29 17:36
 * @Description: Jackson 序列化工具类
 */
@DependsOn(value = "jacksonObjectMapper")
@Component
public class JacksonUtil {

    private static ObjectMapper OBJECT_MAPPER;

    @Autowired
    public JacksonUtil(ObjectMapper objectMapper) {
        JacksonUtil.OBJECT_MAPPER = objectMapper;
    }

    /**
     * 将对象序列化为 JSON 字符串
     *
     * @param value 序列化对象
     * @return
     */
    @SneakyThrows
    public static String writeValueAsString(Object value) {
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    /**
     * 将 JSON 字符串反序列化为对象
     *
     * @param content   JSON 字符串
     * @param valueType 反序列化对象类型的 Class 对象 (简单数据类型直接使用 Class 对象作为参数)
     * @param <T>       反序列化对象类型
     * @return
     */
    @SneakyThrows
    public static <T> T readValue(String content, Class<T> valueType) {
        return Objects.nonNull(content) ? OBJECT_MAPPER.readValue(content, valueType) : valueType.newInstance();
    }

    /**
     * 将 JSON 字符串反序列化为对象
     *
     * @param content      JSON 字符串
     * @param valueTypeRef 反序列化对象类型的 TypeReference 封装对象 (复杂数据类型使用 TypeReference 对象封装后再作为参数)
     * @param <T>          反序列化对象类型
     * @return
     */
    @SneakyThrows
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        return OBJECT_MAPPER.readValue(content, valueTypeRef);
    }

    /**
     * 将来源对象转换为目标对象类型
     *
     * @param fromValue   来源对象
     * @param toValueType 目标对象类型的 Class 对象 (简单数据类型直接使用 Class 对象作为参数)
     * @param <T>         目标对象类型
     * @return
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    /**
     * 将来源对象转换为目标对象类型
     *
     * @param fromValue      来源对象
     * @param toValueTypeRef 目标对象类型的 TypeReference 封装对象 (复杂数据类型使用 TypeReference 对象封装后再作为参数)
     * @param <T>            目标对象类型
     * @return
     */
    public static <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
        return OBJECT_MAPPER.convertValue(fromValue, toValueTypeRef);
    }

    /**
     * 根据键值对创建 ObjectNode
     *
     * @param keysAndValues 键值对
     * @return
     */
    public static ObjectNode createObjectNode(String... keysAndValues) {
        int length = keysAndValues.length;
        // 校验参数个数是否为偶数
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The number of arguments must be even!");
        }
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        for (int i = 0; i < length - 1; i += 2) {
            objectNode.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        return objectNode;
    }
}
