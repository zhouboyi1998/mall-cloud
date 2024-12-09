package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.util.json.JacksonUtil;
import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class OrderIndexServiceImpl implements OrderIndexService {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Override
    public GetResponse one(String id) {
        // 组装查询请求
        GetRequest getRequest = new GetRequest(ElasticSearchConstant.Order.INDEX, id);
        // 查询数据
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public IndexResponse insert(OrderIndex orderIndex) {
        // 组装插入请求
        IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Order.INDEX)
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .id(orderIndex.getId().toString())
            .source(JacksonUtil.writeValueAsString(orderIndex), XContentType.JSON);
        // 插入数据
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public UpdateResponse update(OrderIndex orderIndex) {
        // 组装更新请求
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Order.INDEX, orderIndex.getId().toString())
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .doc(JacksonUtil.writeValueAsString(orderIndex), XContentType.JSON);
        // 更新数据
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public DeleteResponse delete(String id) {
        // 组装删除请求
        DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Order.INDEX, id)
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN));
        // 删除数据
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse insertBatch(List<OrderIndex> orderIndexList) {
        // 组装批量插入请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (OrderIndex orderIndex : orderIndexList) {
            IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Order.INDEX)
                // 设置索引ID字段为订单ID字段, 不额外生成
                .id(orderIndex.getId().toString())
                .source(JacksonUtil.writeValueAsString(orderIndex), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        // 批量插入数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse updateBatch(List<OrderIndex> orderIndexList) {
        // 组装批量更新请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (OrderIndex orderIndex : orderIndexList) {
            UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Order.INDEX, orderIndex.getId().toString())
                .doc(JacksonUtil.writeValueAsString(orderIndex), XContentType.JSON);
            bulkRequest.add(updateRequest);
        }
        // 批量更新数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse deleteBatch(List<String> ids) {
        // 组装批量删除请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (String id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Order.INDEX, id);
            bulkRequest.add(deleteRequest);
        }
        // 批量删除数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}
