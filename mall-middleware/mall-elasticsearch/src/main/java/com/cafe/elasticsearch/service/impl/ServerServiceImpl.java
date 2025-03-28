package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/27 11:19
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse info() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }
}
