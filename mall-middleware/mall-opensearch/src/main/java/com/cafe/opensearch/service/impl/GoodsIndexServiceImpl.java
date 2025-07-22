package com.cafe.opensearch.service.impl;

import com.cafe.common.constant.opensearch.OpenSearchConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.infrastructure.elasticsearch.model.converter.PageConverter;
import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;
import com.cafe.opensearch.model.index.GoodsIndex;
import com.cafe.opensearch.service.GoodsIndexService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.search.SearchResponse;
import org.opensearch.action.update.UpdateRequest;
import org.opensearch.action.update.UpdateResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.QueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.SearchHit;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            .timeout(TimeValue.timeValueSeconds(10))
            .id(String.valueOf(goodsIndex.getId()))
            .source(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse saveBatch(List<GoodsIndex> goodsIndexList) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
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
            .timeout(TimeValue.timeValueSeconds(10))
            .doc(JacksonUtil.writeValueAsString(goodsIndex), XContentType.JSON);
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse updateBatch(List<GoodsIndex> goodsIndexList) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
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
            .timeout(TimeValue.timeValueSeconds(10));
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public BulkResponse deleteBatch(List<Long> ids) {
        BulkRequest bulkRequest = new BulkRequest().timeout(TimeValue.timeValueSeconds(60));
        for (Long id : ids) {
            DeleteRequest deleteRequest = new DeleteRequest(OpenSearchConstant.Goods.INDEX, String.valueOf(id));
            bulkRequest.add(deleteRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @SneakyThrows
    @Override
    public AggregatedPageVO<GoodsIndex> search(Integer current, Integer size, String sortField, String sortRule, String keyword) {
        // 组装搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            // 滚动分页
            .from((current - 1) * size)
            .size(size)
            // 排序
            .sort(sortField, SortOrder.fromString(sortRule))
            // 超时时间
            .timeout(TimeValue.timeValueSeconds(20));
        // 如果关键词不为空, 匹配关键词
        if (ObjectUtils.isNotEmpty(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, OpenSearchConstant.Goods.SEARCH_FIELD);
            searchSourceBuilder.query(queryBuilder);
        }
        // 组装搜索请求
        SearchRequest searchRequest = new SearchRequest(OpenSearchConstant.Goods.INDEX).source(searchSourceBuilder);
        // 发起请求, 获取响应
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 获取搜索命中结果
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        // 转换成商品索引列表
        List<GoodsIndex> goodsIndexList = Arrays.stream(searchHits)
            .map(SearchHit::getSourceAsMap)
            .map(sourceAsMap -> JacksonUtil.convertValue(sourceAsMap, GoodsIndex.class))
            .collect(Collectors.toList());
        AggregatedPageImpl<GoodsIndex> goodsIndexPage = new AggregatedPageImpl<>(goodsIndexList, searchResponse.getScrollId());
        return PageConverter.INSTANCE.toAggregatedPageVO(goodsIndexPage);
    }
}
