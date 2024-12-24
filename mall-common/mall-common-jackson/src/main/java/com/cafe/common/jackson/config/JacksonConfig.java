package com.cafe.common.jackson.config;

import com.cafe.common.constant.date.DateTimeConstant;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.jackson.config
 * @Author: zhouboyi
 * @Date: 2023/6/12 15:02
 * @Description: Jackson 配置类
 */
@Configuration
public class JacksonConfig {

    /**
     * Jackson ObjectMapper 构造器自定义配置器
     *
     * @return
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
            // 序列化器配置
            .serializerByType(Long.TYPE, ToStringSerializer.instance)
            .serializerByType(Long.class, ToStringSerializer.instance)
            .serializerByType(Date.class, new DateSerializer(false, new SimpleDateFormat(DateTimeConstant.DEFAULT_DATE_TIME)))
            .serializerByType(Calendar.class, new CalendarSerializer(false, new SimpleDateFormat(DateTimeConstant.DEFAULT_DATE_TIME)))
            .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE_TIME)))
            .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE)))
            .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_TIME)))
            // 反序列化器配置
            .deserializerByType(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(DateTimeConstant.DEFAULT_DATE_TIME), DateTimeConstant.DEFAULT_DATE_TIME))
            .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE_TIME)))
            .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE)))
            .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_TIME)));
    }
}
