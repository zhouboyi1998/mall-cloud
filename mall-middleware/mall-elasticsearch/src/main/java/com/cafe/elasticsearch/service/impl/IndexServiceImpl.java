package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.service.IndexService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/27 2:28
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public IndexServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public Boolean exists(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public CreateIndexResponse create(String index) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        return restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public AcknowledgedResponse delete(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
