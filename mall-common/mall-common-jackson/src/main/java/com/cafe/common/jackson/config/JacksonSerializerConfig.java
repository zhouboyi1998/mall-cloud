package com.cafe.common.jackson.config;

import com.cafe.common.enumeration.date.DateTimePatternEnum;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
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
 * @Description: Jackson 序列化配置类
 */
@Configuration
public class JacksonSerializerConfig {

    /**
     * Jackson ObjectMapper 构造器自定义配置器
     *
     * @return
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
            // 序列化器配置
            .serializerByType(Date.class, new DateSerializer(false, new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())))
            .serializerByType(Calendar.class, new CalendarSerializer(false, new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())))
            .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())))
            .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE.getPattern())))
            .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_TIME.getPattern())))
            // 反序列化器配置
            .deserializerByType(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern()), DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern()))
            .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())))
            .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE.getPattern())))
            .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_TIME.getPattern())));
    }
}
