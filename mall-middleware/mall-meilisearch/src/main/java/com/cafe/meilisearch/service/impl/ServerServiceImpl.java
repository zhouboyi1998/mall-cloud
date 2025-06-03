package com.cafe.meilisearch.service.impl;

import com.cafe.meilisearch.service.ServerService;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.model.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service.impl
 * @Author: zhouboyi
 * @Date: 2025/6/4 23:52
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class ServerServiceImpl implements ServerService {

    private final Client client;

    @Override
    public String health() {
        return client.health();
    }

    @Override
    public Boolean isHealthy() {
        return client.isHealthy();
    }

    @Override
    public Stats stats() {
        return client.getStats();
    }

    @Override
    public String version() {
        return client.getVersion();
    }
}
