package com.cafe.id.scheduler;

import com.cafe.infrastructure.lettuce.worker.LettuceIDWorker;
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
 * @Date: 2025/9/23 16:39
 * @Description: Lettuce 分布式ID生成器相关定时任务
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LettuceIDScheduler {

    private final LettuceIDWorker lettuceIDWorker;

    @DistributedLock(value = 25, expireUnit = TimeUnit.HOURS)
    @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeExpiredKeys() {
        Long count = lettuceIDWorker.removeExpiredKeys();
        log.info("remove expired lettuce distributed id keys, remove count: [{}]", count);
    }
}
