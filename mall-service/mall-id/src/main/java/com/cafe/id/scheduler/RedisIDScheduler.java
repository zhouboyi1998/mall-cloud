package com.cafe.id.scheduler;

import com.cafe.id.worker.RedisIDWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.scheduler
 * @Author: zhouboyi
 * @Date: 2025/7/9 10:05
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisIDScheduler {

    private final RedisIDWorker redisIDWorker;

    @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeExpiredKeys() {
        Long count = redisIDWorker.removeExpiredKeys();
        log.info("remove expired redis distributed id keys, remove count: [{}]", count);
    }
}
