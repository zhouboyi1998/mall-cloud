package com.cafe.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.json
 * @Author: zhouboyi
 * @Date: 2023/8/29 17:36
 * @Description: Jackson 序列化工具类
 */
@Component
public class JacksonUtil {

    private static ObjectMapper OBJECT_MAPPER;

    @Autowired
    public JacksonUtil(ObjectMapper objectMapper) {
        JacksonUtil.OBJECT_MAPPER = objectMapper;
    }

    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize the value as String!");
        }
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return Objects.nonNull(content) ? OBJECT_MAPPER.readValue(content, valueType) : valueType.newInstance();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to deserialize the content as Object!");
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to get instance via reflection!");
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to deserialize the content as Object!");
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    public static <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
        return OBJECT_MAPPER.convertValue(fromValue, toValueTypeRef);
    }

    public static ObjectNode createObjectNode(String... keysAndValues) {
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
        for (int i = 0; i < keysAndValues.length - 1; i += 2) {
            objectNode.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        return objectNode;
    }
}
