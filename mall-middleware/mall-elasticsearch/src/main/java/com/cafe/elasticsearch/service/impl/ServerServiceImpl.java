package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.service.ServerService;
import lombok.SneakyThrows;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
@Service
public class ServerServiceImpl implements ServerService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public ServerServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    @Override
    public SearchResponse info() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
