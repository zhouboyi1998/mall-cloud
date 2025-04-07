package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.repository.GoodsIndexRepository;
import com.cafe.elasticsearch.service.GoodsIndexService;
import com.cafe.elasticsearch.util.DocumentUtil;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    private final GoodsIndexRepository goodsIndexRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public GoodsIndex one(Long id) {
        return goodsIndexRepository.findById(id).orElse(new GoodsIndex());
    }

    @Override
    public GoodsIndex save(GoodsIndex goodsIndex) {
        return goodsIndexRepository.save(goodsIndex);
    }

    @Override
    public List<GoodsIndex> saveBatch(List<GoodsIndex> goodsIndexList) {
        Iterable<GoodsIndex> saveIterable = goodsIndexRepository.saveAll(goodsIndexList);
        return StreamSupport.stream(saveIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public GoodsIndex update(GoodsIndex goodsIndex) {
        // 获取商品索引
        GoodsIndex currentGoodsIndex = goodsIndexRepository.findById(goodsIndex.getId())
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_INDEX_NOT_FOUND, goodsIndex));
        // 组装修改参数
        Document document = DocumentUtil.updateDocument(currentGoodsIndex, goodsIndex);
        // 组装修改请求
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(goodsIndex.getId()))
            .withDocument(document)
            .build();
        // 修改商品索引
        UpdateResponse updateResponse = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(ElasticSearchConstant.Goods.INDEX));
        // 判断修改结果
        if (!Objects.equals(updateResponse.getResult(), UpdateResponse.Result.UPDATED)) {
            throw new BusinessException(HttpStatusEnum.GOODS_INDEX_UPDATE_FAIL, goodsIndex);
        }
        return JacksonUtil.convertValue(document, GoodsIndex.class);
    }

    @Override
    public List<GoodsIndex> updateBatch(List<GoodsIndex> goodsIndexList) {
        Map<Long, GoodsIndex> goodsIndexMap = goodsIndexList.stream()
            .collect(Collectors.toMap(GoodsIndex::getId, Function.identity()));
        // 获取商品索引列表
        Iterable<GoodsIndex> currentGoodsIndexIterable = goodsIndexRepository.findAllById(goodsIndexMap.keySet());
        // 组装批量修改参数
        List<Document> documentList = StreamSupport.stream(currentGoodsIndexIterable.spliterator(), false)
            .map(currentGoodsIndex -> DocumentUtil.updateDocument(currentGoodsIndex, goodsIndexMap.get(currentGoodsIndex.getId())))
            .collect(Collectors.toList());
        // 组装批量修改请求
        List<UpdateQuery> updateQueryList = documentList.stream()
            .map(document -> UpdateQuery.builder(String.valueOf(document.get(FieldConstant.ID))).withDocument(document).build())
            .collect(Collectors.toList());
        // 批量修改商品索引
        elasticsearchRestTemplate.bulkUpdate(updateQueryList, IndexCoordinates.of(ElasticSearchConstant.Goods.INDEX));
        // 返回修改后的商品索引列表
        return documentList.stream()
            .map(document -> JacksonUtil.convertValue(document, GoodsIndex.class))
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        goodsIndexRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        List<GoodsIndex> goodsIndexList = ids.stream().map(id -> new GoodsIndex().setId(id)).collect(Collectors.toList());
        goodsIndexRepository.deleteAll(goodsIndexList);
    }

    @SneakyThrows
    @Override
    public Page<GoodsIndex> search(Integer current, Integer size, String sortField, String sortRule, String keyword) {
        // 组装搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
            // 滚动分页
            .from((current - 1) * size)
            .size(size)
            // 排序
            .sort(sortField, SortOrder.fromString(sortRule))
            // 超时时间
            .timeout(TimeValue.timeValueSeconds(IntegerConstant.TWENTY));
        // 如果关键词不为空, 匹配关键词
        if (ObjectUtils.isNotEmpty(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, ElasticSearchConstant.Goods.SEARCH_FIELD);
            searchSourceBuilder.query(queryBuilder);
        }
        // 组装搜索请求
        SearchRequest searchRequest = new SearchRequest(ElasticSearchConstant.Goods.INDEX).source(searchSourceBuilder);
        // 发起请求, 获取响应
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 获取搜索命中结果
        org.elasticsearch.search.SearchHit[] searchHits = searchResponse.getHits().getHits();
        // 转换成商品索引列表
        List<GoodsIndex> goodsIndexList = Arrays.stream(searchHits)
            .map(SearchHit::getSourceAsMap)
            .map(sourceAsMap -> JacksonUtil.convertValue(sourceAsMap, GoodsIndex.class))
            .collect(Collectors.toList());
        return new AggregatedPageImpl<>(goodsIndexList, searchResponse.getScrollId());
    }
}
