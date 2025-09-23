package com.cafe.infrastructure.jedis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.jedis.config
 * @Author: zhouboyi
 * @Date: 2025/9/22 18:12
 * @Description: Jedis 配置类
 */
@Configuration
public class JedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;

    /**
     * Jedis 连接池
     */
    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(new JedisPoolConfig(), host, port, 30000, password, database);
    }
}
