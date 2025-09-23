package com.cafe.id.service.impl;

import com.cafe.id.service.IDService;
import com.cafe.id.support.IDGenerator;
import com.cafe.infrastructure.lettuce.worker.LettuceIDWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.id.service.impl
 * @Author: zhouboyi
 * @Date: 2025/9/23 16:21
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class LettuceIDServiceImpl implements IDService {

    private final LettuceIDWorker lettuceIDWorker;

    @Override
    public IDGenerator getKey() {
        return IDGenerator.LETTUCE;
    }

    @Override
    public Long nextId() {
        return lettuceIDWorker.nextId();
    }
}
