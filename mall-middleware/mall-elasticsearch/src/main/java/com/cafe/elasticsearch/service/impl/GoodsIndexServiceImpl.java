package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.service.GoodsIndexService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:39
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class GoodsIndexServiceImpl implements GoodsIndexService {

    private final RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Override
    public GetResponse one(String id) {
        // 组装查询请求
        GetRequest getRequest = new GetRequest(ElasticSearchConstant.Goods.INDEX, id);
        // 查询数据
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public IndexResponse insert(GoodsIndex goodsIndex) {
        // 组装插入请求
        IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Goods.INDEX)
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .id(goodsIndex.getId().toString())
            .source(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        // 插入数据
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public UpdateResponse update(GoodsIndex goodsIndex) {
        // 组装更新请求
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Goods.INDEX, goodsIndex.getId().toString())
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN))
            .doc(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        // 更新数据
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public DeleteResponse delete(String id) {
        // 组装删除请求
        DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Goods.INDEX, id)
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TEN));
        // 删除数据
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse insertBatch(List<GoodsIndex> goodsIndexList) {
        // 组装批量插入请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (GoodsIndex goodsIndex : goodsIndexList) {
            IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Goods.INDEX)
                // 设置索引ID字段为商品ID字段, 不额外生成
                .id(goodsIndex.getId().toString())
                .source(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        // 批量插入数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse updateBatch(List<GoodsIndex> goodsIndexList) {
        // 组装批量更新请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        for (GoodsIndex goodsIndex : goodsIndexList) {
            UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Goods.INDEX, goodsIndex.getId().toString())
                .doc(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
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
            DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Goods.INDEX, id);
            bulkRequest.add(deleteRequest);
        }
        // 批量删除数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public SearchResponse list(String keyword) {
        // 组装搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.SIXTY));
        // 如果关键词不为空, 组装关键词匹配
        if (ObjectUtils.isNotEmpty(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, ElasticSearchConstant.Goods.SEARCH_FIELD);
            searchSourceBuilder.query(queryBuilder);
        }
        // 组装搜索请求
        SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.Goods.INDEX)
            .source(searchSourceBuilder);
        // 搜索获取返回值
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public SearchResponse page(Integer current, Integer size, String keyword, String sort, String rule) {
        // 组装搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            // 分页条件
            .from((current - 1) * size).size(size)
            // 排序条件
            .sort(sort, SortOrder.fromString(rule))
            // 超时时间
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TWENTY));
        // 如果关键词不为空, 组装关键词匹配
        if (ObjectUtils.isNotEmpty(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, ElasticSearchConstant.Goods.SEARCH_FIELD);
            searchSourceBuilder.query(queryBuilder);
        }
        // 组装搜索请求
        SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.Goods.INDEX)
            .source(searchSourceBuilder);
        // 搜索获取返回值
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
