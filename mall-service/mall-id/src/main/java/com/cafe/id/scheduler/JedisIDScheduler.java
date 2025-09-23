package com.cafe.id.scheduler;

import com.cafe.infrastructure.jedis.worker.JedisIDWorker;
import com.cafe.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.scheduler
 * @Author: zhouboyi
 * @Date: 2025/9/23 14:29
 * @Description: Jedis 分布式ID生成器相关定时任务
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JedisIDScheduler {

    private final JedisIDWorker jedisIDWorker;

    @DistributedLock(value = 25, expireUnit = TimeUnit.HOURS)
    @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeExpiredKeys() {
        Long count = jedisIDWorker.removeExpiredKeys();
        log.info("remove expired jedis distributed id keys, remove count: [{}]", count);
    }
}
