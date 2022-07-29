package com.cafe.search.elasticsearch.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.goods.feign.SkuFeign;
import com.cafe.search.elasticsearch.constant.ElasticSearchConstant;
import com.cafe.search.elasticsearch.service.GoodsService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
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
public class GoodsServiceImpl implements GoodsService {

    private RestHighLevelClient restHighLevelClient;

    private SkuFeign skuFeign;

    @Autowired
    public GoodsServiceImpl(
        RestHighLevelClient restHighLevelClient,
        SkuFeign skuFeign
    ) {
        this.restHighLevelClient = restHighLevelClient;
        this.skuFeign = skuFeign;
    }

    @Override
    public BulkResponse insertBatch(List<SkuElasticSearchDTO> dtoList) throws IOException {
        // 组装批量插入请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (SkuElasticSearchDTO dto : dtoList) {
            IndexRequest indexRequest = new IndexRequest(ElasticSearchConstant.GOODS_INDEX)
                // ElasticSearch ID 不自动生成, 使用数据库中存储的业务 ID
                .id(dto.getId().toString())
                .source(JSONUtil.toJsonStr(dto), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        // 批量插入数据
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse;
    }

    @Override
    public BulkResponse updateBatch(List<SkuElasticSearchDTO> dtoList) throws IOException {
        // 组装批量更新请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (SkuElasticSearchDTO dto : dtoList) {
            UpdateRequest updateRequest = new UpdateRequest(ElasticSearchConstant.GOODS_INDEX, dto.getId().toString())
                .doc(JSONUtil.toJsonStr(dto), XContentType.JSON);
            bulkRequest.add(updateRequest);
        }
        // 批量更新数据
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse;
    }

    @Override
    public BulkResponse deleteBatch(List<String> esIds) throws IOException {
        // 组装批量删除请求
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (String esId : esIds) {
            DeleteRequest deleteRequest = new DeleteRequest(ElasticSearchConstant.GOODS_INDEX, esId);
            bulkRequest.add(deleteRequest);
        }
        // 批量删除数据
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse;
    }

    @Override
    public SearchResponse search(Integer current, Integer size, String keyword, String sort, String rule) throws IOException {
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
            QueryBuilder queryBuilder
                = QueryBuilders.multiMatchQuery(keyword, ElasticSearchConstant.GOODS_SEARCH_FIELD);
            searchSourceBuilder.query(queryBuilder);
        }
        // 组装搜索请求
        SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.GOODS_INDEX).source(searchSourceBuilder);
        // 搜索获取返回值
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }

    @Override
    public BulkResponse importBatch(Long current, Long size) throws IOException {
        // 分页获取商品列表
        List<SkuElasticSearchDTO> dtoList = skuFeign.pageSkuElasticSearchDTO(current, size).getBody().getRecords();
        // 调用 insertBatch() 方法插入商品数据
        BulkResponse bulkResponse = insertBatch(dtoList);
        return bulkResponse;
    }
}
