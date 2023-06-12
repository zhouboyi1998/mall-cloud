package com.cafe.common.config.json;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.config.json
 * @Author: zhouboyi
 * @Date: 2023/6/12 15:02
 * @Description: Jackson 格式化配置类
 */
@JsonComponent
public class JacksonFormatConfig {

    /**
     * 日期类型格式
     */
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    /**
     * Jackson 对象映射构造器
     *
     * @return
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
            .serializers(
                // Date 类型序列化器
                new DateSerializer(false, new SimpleDateFormat(pattern)),
                // LocalDateTime 类型序列化器
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern))
            )
            .deserializers(
                // LocalDateTime 类型反序列化器
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern))
            );
    }
}
