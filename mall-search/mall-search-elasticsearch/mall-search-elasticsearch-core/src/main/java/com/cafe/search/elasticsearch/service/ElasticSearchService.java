package com.cafe.search.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
public interface ElasticSearchService {

    /**
     * 查询 ElasticSearch 集群信息
     *
     * @return
     * @throws IOException
     */
    SearchResponse info() throws IOException;
}
