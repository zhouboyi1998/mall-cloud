package com.cafe.id.config;

import com.cafe.common.lang.id.Snowflake;
import com.cafe.id.property.IDProperties;
import com.cafe.infrastructure.redis.worker.RedisIDWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.config
 * @Author: zhouboyi
 * @Date: 2022/12/6 10:47
 * @Description: 分布式ID配置类
 */
@RequiredArgsConstructor
@Configuration
public class IDConfig {

    private final IDProperties idProperties;

    private final StringRedisTemplate stringRedisTemplate;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(idProperties.getSnowflake().getWorkerId(), idProperties.getSnowflake().getDatacenterId());
    }

    @Bean
    public RedisIDWorker redisIDWorker() {
        return new RedisIDWorker(idProperties.getRedis().getWorkerId(), idProperties.getRedis().getDatacenterId(), stringRedisTemplate);
    }
}
