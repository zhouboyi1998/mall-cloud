package com.cafe.infrastructure.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.redis.config
 * @Author: zhouboyi
 * @Date: 2022/5/10 22:17
 * @Description: Redis 配置类
 */
@Configuration
public class RedisConfig {

    @Primary
    @Bean(value = "redisTemplate4JsonValue")
    public RedisTemplate<String, Object> redisTemplate4JsonValue(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 指定序列化规则 (Value 使用 JSON 序列化)
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(value = "redisTemplate4JavaValue")
    public RedisTemplate<String, Object> redisTemplate4JavaValue(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 指定序列化规则 (Value 使用 Java 序列化)
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.java());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.java());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
