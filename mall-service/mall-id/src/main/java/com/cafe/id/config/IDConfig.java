package com.cafe.id.config;

import com.cafe.common.lang.algorithm.id.Snowflake;
import com.cafe.id.property.IDProperties;
import com.cafe.infrastructure.jedis.worker.JedisIDWorker;
import com.cafe.infrastructure.redis.worker.RedisIDWorker;
import com.cafe.infrastructure.redisson.worker.RedissonIDWorker;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;

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

    private final RedissonClient redissonClient;

    private final JedisPool jedisPool;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(idProperties.getSnowflake().getWorkerId(), idProperties.getSnowflake().getDatacenterId());
    }

    @Bean
    public RedisIDWorker redisIDWorker() {
        return new RedisIDWorker(idProperties.getRedis().getWorkerId(), idProperties.getRedis().getDatacenterId(), stringRedisTemplate);
    }

    @Bean
    public RedissonIDWorker redissonIDWorker() {
        return new RedissonIDWorker(idProperties.getRedisson().getWorkerId(), idProperties.getRedisson().getDatacenterId(), redissonClient);
    }

    @Bean
    public JedisIDWorker jedisIDWorker() {
        return new JedisIDWorker(idProperties.getJedis().getWorkerId(), idProperties.getJedis().getDatacenterId(), jedisPool);
    }
}
