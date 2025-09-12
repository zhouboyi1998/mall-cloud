package com.cafe.id.service.impl;

import com.cafe.id.service.IDService;
import com.cafe.id.support.IDGenerator;
import com.cafe.infrastructure.redis.worker.RedisIDWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2025/7/8 16:42
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class RedisIDServiceImpl implements IDService {

    private final RedisIDWorker redisIDWorker;

    @Override
    public IDGenerator getKey() {
        return IDGenerator.REDIS;
    }

    @Override
    public Long nextId() {
        return redisIDWorker.nextId();
    }
}
