package com.cafe.meilisearch.service;

import com.meilisearch.sdk.model.Stats;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service
 * @Author: zhouboyi
 * @Date: 2025/6/4 23:52
 * @Description:
 */
public interface ServerService {

    /**
     * 查询服务健康状况信息
     *
     * @return 健康状况信息
     */
    String health();

    /**
     * 判断服务是否健康
     *
     * @return 是否健康
     */
    Boolean isHealthy();

    /**
     * 查询服务统计信息
     *
     * @return 统计信息
     */
    Stats stats();

    /**
     * 查询服务版本号
     *
     * @return 版本号
     */
    String version();
}
