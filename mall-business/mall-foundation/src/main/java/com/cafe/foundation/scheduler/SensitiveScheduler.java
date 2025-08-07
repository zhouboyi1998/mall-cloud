package com.cafe.foundation.scheduler;

import com.cafe.foundation.facade.SensitiveFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.scheduler
 * @Author: zhouboyi
 * @Date: 2025/8/7 23:26
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SensitiveScheduler {

    private final SensitiveFacade sensitiveFacade;

    @PostConstruct
    @Scheduled(cron = "0 0 2 * * ?")
    public void reinitializeSensitiveTrie() {
        Instant startTime = Instant.now();
        sensitiveFacade.initSensitiveTrie();
        Instant endTime = Instant.now();
        long duration = Duration.between(startTime, endTime).toMillis();
        log.info("reinitialize sensitive word trie, duration: {} ms", duration);
    }
}
