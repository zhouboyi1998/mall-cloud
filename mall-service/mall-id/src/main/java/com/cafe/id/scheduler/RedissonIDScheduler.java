package com.cafe.id.scheduler;

import com.cafe.infrastructure.redis.annotation.DistributedLock;
import com.cafe.infrastructure.redisson.worker.RedissonIDWorker;
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
 * @Date: 2025/9/18 15:02
 * @Description: Redisson 分布式ID生成器相关定时任务
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedissonIDScheduler {

    private final RedissonIDWorker redissonIDWorker;

    @DistributedLock(value = 25, expireUnit = TimeUnit.HOURS)
    @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeExpiredKeys() {
        long count = redissonIDWorker.removeExpiredKeys();
        log.info("remove expired redisson distributed id keys, remove count: [{}]", count);
    }
}
