package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.service.ElasticSearchService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public SearchResponse info() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @Override
    public Boolean existsIndex(String name) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(name);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public CreateIndexResponse createIndex(String name) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(name);
        return restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public AcknowledgedResponse deleteIndex(String name) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(name);
        return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
