package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.repository.GoodsIndexRepository;
import com.cafe.elasticsearch.service.GoodsIndexService;
import com.cafe.infrastructure.elasticsearch.model.converter.PageConverter;
import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;
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
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        return goodsIndexRepository.update(goodsIndex);
    }

    @Override
    public List<GoodsIndex> updateBatch(List<GoodsIndex> goodsIndexList) {
        Iterable<GoodsIndex> updateIterable = goodsIndexRepository.updateAll(goodsIndexList);
        return StreamSupport.stream(updateIterable.spliterator(), false).collect(Collectors.toList());
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
    public AggregatedPageVO<GoodsIndex> search(Integer current, Integer size, String sortField, String sortRule, String keyword) {
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
        AggregatedPageImpl<GoodsIndex> goodsIndexPage = new AggregatedPageImpl<>(goodsIndexList, searchResponse.getScrollId());
        return PageConverter.INSTANCE.toAggregatedPageVO(goodsIndexPage);
    }
}
