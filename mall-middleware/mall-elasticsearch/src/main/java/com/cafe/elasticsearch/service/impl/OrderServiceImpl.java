package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.service.OrderService;
import com.cafe.order.vo.OrderVO;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public OrderServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public GetResponse one(String id) throws IOException {
        // 组装查询请求
        GetRequest getRequest = new GetRequest(ElasticSearchConstant.OrderVO.INDEX, id);
        // 查询数据
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @Override
    public IndexResponse insert(OrderVO orderVO) throws IOException {
        // 组装插入请求
        IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.OrderVO.INDEX)
            .timeout(TimeValue.timeValueSeconds(10))
            .id(orderVO.getId().toString())
            .source(JacksonUtil.writeValueAsString(orderVO), XContentType.JSON);
        // 插入数据
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public UpdateResponse update(OrderVO orderVO) throws IOException {
        // 组装更新请求
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.OrderVO.INDEX, orderVO.getId().toString())
            .timeout(TimeValue.timeValueSeconds(10))
            .doc(JacksonUtil.writeValueAsString(orderVO), XContentType.JSON);
        // 更新数据
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public DeleteResponse delete(String id) throws IOException {
        // 组装删除请求
        DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.OrderVO.INDEX, id)
            .timeout(TimeValue.timeValueSeconds(10));
        // 删除数据
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse insertBatch(List<OrderVO> orderVOList) throws IOException {
        // 组装批量插入请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (OrderVO orderVO : orderVOList) {
            IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.OrderVO.INDEX)
                // ElasticSearch 索引库的主键不使用自动生成的 ID, 使用数据库中存储的业务 ID
                .id(orderVO.getId().toString())
                .source(JacksonUtil.writeValueAsString(orderVO), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        // 批量插入数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse updateBatch(List<OrderVO> orderVOList) throws IOException {
        // 组装批量更新请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (OrderVO orderVO : orderVOList) {
            UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.OrderVO.INDEX, orderVO.getId().toString())
                .doc(JacksonUtil.writeValueAsString(orderVO), XContentType.JSON);
            bulkRequest.add(updateRequest);
        }
        // 批量更新数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse deleteBatch(List<String> ids) throws IOException {
        // 组装批量删除请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (String id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.OrderVO.INDEX, id);
            bulkRequest.add(deleteRequest);
        }
        // 批量删除数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}
