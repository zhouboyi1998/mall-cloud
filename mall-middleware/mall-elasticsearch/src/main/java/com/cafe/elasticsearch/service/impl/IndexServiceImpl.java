package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/27 2:28
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public Boolean exists(String index) {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(index);
            return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CreateIndexResponse create(String index) {
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
            return restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AcknowledgedResponse delete(String index) {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
            return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }
}
