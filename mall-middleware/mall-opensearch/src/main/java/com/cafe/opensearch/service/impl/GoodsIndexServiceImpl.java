package com.cafe.opensearch.service.impl;

import com.cafe.common.constant.opensearch.OpenSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.opensearch.model.index.GoodsIndex;
import com.cafe.opensearch.service.GoodsIndexService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.action.update.UpdateResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.service.impl
 * @Author: zhouboyi
 * @Date: 2025/6/16 21:06
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class GoodsIndexServiceImpl implements GoodsIndexService {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Override
    public GoodsIndex one(Long id) {
        GetRequest getRequest = new GetRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(id));
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return JacksonUtil.convertValue(getResponse.getSourceAsMap(), GoodsIndex.class);
    }

    @SneakyThrows
    @Override
    public IndexResponse save(GoodsIndex goodsIndex) {
        IndexRequest indexRequest = new IndexRequest(OpenSearchConstant.Goods.INDEX)
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .id(String.valueOf(goodsIndex.getId()))
            .source(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse saveBatch(List<GoodsIndex> goodsIndexList) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (GoodsIndex goodsIndex : goodsIndexList) {
            IndexRequest indexRequest = new IndexRequest(OpenSearchConstant.Goods.INDEX)
                .id(String.valueOf(goodsIndex.getId()))
                .source(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public UpdateResponse update(GoodsIndex goodsIndex) {
        UpdateRequest updateRequest = new UpdateRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(goodsIndex.getId()))
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .doc(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse updateBatch(List<GoodsIndex> goodsIndexList) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (GoodsIndex goodsIndex : goodsIndexList) {
            UpdateRequest updateRequest = new UpdateRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(goodsIndex.getId()))
                .doc(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
            bulkRequest.add(updateRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public DeleteResponse delete(Long id) {
        DeleteRequest deleteRequest = new DeleteRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(id))
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN));
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse deleteBatch(List<Long> ids) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (Long id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(id));
            bulkRequest.add(deleteRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }
}
