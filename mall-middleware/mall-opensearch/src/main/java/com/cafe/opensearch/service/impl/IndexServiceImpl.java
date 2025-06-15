package com.cafe.opensearch.service.impl;

import com.cafe.opensearch.service.IndexService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.service.impl
 * @Author: zhouboyi
 * @Date: 2025/6/16 0:53
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class IndexServiceImpl implements IndexService {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Override
    public Boolean exists(String index) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public CreateIndexResponse create(String index) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        return restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public AcknowledgedResponse delete(String index) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }
}
