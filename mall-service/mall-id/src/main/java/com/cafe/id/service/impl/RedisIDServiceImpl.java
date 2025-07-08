package com.cafe.id.service.impl;

import com.cafe.id.property.IDProperties;
import com.cafe.id.service.IDService;
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
@Service(value = IDProperties.GeneratorServiceName.REDIS)
public class RedisIDServiceImpl implements IDService {

    private final RedisIDWorker redisIDWorker;

    @Override
    public Long nextId() {
        return redisIDWorker.nextId();
    }
}
