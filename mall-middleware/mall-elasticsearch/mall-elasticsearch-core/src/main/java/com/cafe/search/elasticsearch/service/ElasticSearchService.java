package com.cafe.search.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;

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

    /**
     * 判断索引是否存在
     *
     * @param name 索引名称
     * @return
     * @throws IOException
     */
    Boolean existsIndex(String name) throws IOException;

    /**
     * 创建索引
     *
     * @param name 索引名称
     * @return
     * @throws IOException
     */
    CreateIndexResponse createIndex(String name) throws IOException;

    /**
     * 删除索引
     *
     * @param name 索引名称
     * @return
     * @throws IOException
     */
    AcknowledgedResponse deleteIndex(String name) throws IOException;
}
