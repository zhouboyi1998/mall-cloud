package com.cafe.search.elasticsearch.service.impl;

import com.cafe.search.elasticsearch.service.ElasticSearchService;
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
 * @Package: com.cafe.search.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public SearchResponse info() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }

    @Override
    public Boolean existsIndex(String name) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(name);
        Boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        return exists;
    }

    @Override
    public CreateIndexResponse createIndex(String name) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(name);
        CreateIndexResponse createIndexResponse
            = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        return createIndexResponse;
    }

    @Override
    public AcknowledgedResponse deleteIndex(String name) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(name);
        AcknowledgedResponse acknowledgedResponse
            = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return acknowledgedResponse;
    }
}
