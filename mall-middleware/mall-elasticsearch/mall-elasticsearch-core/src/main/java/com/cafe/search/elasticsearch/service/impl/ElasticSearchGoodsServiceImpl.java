package com.cafe.search.elasticsearch.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.search.elasticsearch.model.Goods;
import com.cafe.search.elasticsearch.service.ElasticSearchGoodsService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:39
 * @Description:
 */
@Service
public class ElasticSearchGoodsServiceImpl implements ElasticSearchGoodsService {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchGoodsServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public GetResponse one(String id) throws IOException {
        // 组装查询请求
        GetRequest getRequest = new GetRequest(ElasticSearchConstant.Goods.INDEX, id);
        // 查询数据
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @Override
    public IndexResponse insert(Goods goods) throws IOException {
        // 组装插入请求
        IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Goods.INDEX)
            .timeout(TimeValue.timeValueSeconds(10))
            .id(goods.getId().toString())
            .source(JSONUtil.toJsonStr(goods), XContentType.JSON);
        // 插入数据
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public UpdateResponse update(Goods goods) throws IOException {
        // 组装更新请求
        UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Goods.INDEX, goods.getId().toString())
            .timeout(TimeValue.timeValueSeconds(10))
            .doc(JSONUtil.toJsonStr(goods), XContentType.JSON);
        // 更新数据
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public DeleteResponse delete(String id) throws IOException {
        // 组装删除请求
        DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Goods.INDEX, id)
            .timeout(TimeValue.timeValueSeconds(10));
        // 删除数据
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse insertBatch(List<Goods> goodsList) throws IOException {
        // 组装批量插入请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (Goods goods : goodsList) {
            IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.Goods.INDEX)
                // ElasticSearch 索引库的主键不使用自动生成的 ID, 使用数据库中存储的业务 ID
                .id(goods.getId().toString())
                .source(JSONUtil.toJsonStr(goods), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        // 批量插入数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse updateBatch(List<Goods> goodsList) throws IOException {
        // 组装批量更新请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (Goods goods : goodsList) {
            UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.Goods.INDEX, goods.getId().toString())
                .doc(JSONUtil.toJsonStr(goods), XContentType.JSON);
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
            DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.Goods.INDEX, id);
            bulkRequest.add(deleteRequest);
        }
        // 批量删除数据
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @Override
    public SearchResponse page(Integer current, Integer size, String keyword, String sort, String rule) throws IOException {
        // 组装搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            // 分页条件
            .from((current - 1) * size).size(size)
            // 排序条件
            .sort(sort, SortOrder.fromString(rule))
            // 超时时间
            .timeout(TimeValue.timeValueSeconds(10));
        // 如果关键词不为空, 组装关键词匹配
        if (ObjectUtil.isNotEmpty(keyword)) {
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
