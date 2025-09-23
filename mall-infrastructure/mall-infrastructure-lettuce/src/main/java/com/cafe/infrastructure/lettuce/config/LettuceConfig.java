package com.cafe.infrastructure.lettuce.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.lettuce.config
 * @Author: zhouboyi
 * @Date: 2025/9/23 15:11
 * @Description: Lettuce 配置类
 */
@Configuration
public class LettuceConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    public RedisClient redisClient() {
        RedisURI redisURI = RedisURI.builder()
            .withHost(host)
            .withPort(port)
            .withPassword(password)
            .withDatabase(database)
            .build();
        return RedisClient.create(redisURI);
    }
}
