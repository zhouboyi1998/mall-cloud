package com.cafe.id.service.impl;

import com.cafe.id.service.IDService;
import com.cafe.id.support.IDGenerator;
import com.cafe.infrastructure.redisson.worker.RedissonIDWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2025/9/18 14:49
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class RedissonIDServiceImpl implements IDService {

    private final RedissonIDWorker redissonIDWorker;

    @Override
    public IDGenerator getKey() {
        return IDGenerator.REDISSON;
    }

    @Override
    public Long nextId() {
        return redissonIDWorker.nextId();
    }
}
