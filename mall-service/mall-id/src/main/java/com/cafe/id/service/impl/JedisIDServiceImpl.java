package com.cafe.id.service.impl;

import com.cafe.id.service.IDService;
import com.cafe.id.support.IDGenerator;
import com.cafe.infrastructure.jedis.worker.JedisIDWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2025/9/23 11:38
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class JedisIDServiceImpl implements IDService {

    private final JedisIDWorker jedisIDWorker;

    @Override
    public IDGenerator getKey() {
        return IDGenerator.JEDIS;
    }

    @Override
    public Long nextId() {
        return jedisIDWorker.nextId();
    }
}
