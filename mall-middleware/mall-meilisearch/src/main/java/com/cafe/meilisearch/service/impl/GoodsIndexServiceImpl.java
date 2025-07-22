package com.cafe.meilisearch.service.impl;

import com.cafe.common.constant.meilisearch.MeiliSearchConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.meilisearch.model.index.GoodsIndex;
import com.cafe.meilisearch.service.GoodsIndexService;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.Searchable;
import com.meilisearch.sdk.model.TaskInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service.impl
 * @Author: zhouboyi
 * @Date: 2025/6/5 22:17
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class GoodsIndexServiceImpl implements GoodsIndexService {

    private final Client client;

    @PostConstruct
    @Override
    public List<TaskInfo> init() {
        Index index = client.index(MeiliSearchConstant.Goods.INDEX);
        return Stream.of(
            // 设置主键字段
            CompletableFuture.supplyAsync(() -> client.updateIndex(MeiliSearchConstant.Goods.INDEX, MeiliSearchConstant.Goods.PRIMARY_KEY)),
            // 设置全文搜索字段
            CompletableFuture.supplyAsync(() -> index.updateSearchableAttributesSettings(MeiliSearchConstant.Goods.SEARCH_FIELD)),
            // 设置过滤字段
            CompletableFuture.supplyAsync(() -> index.updateFilterableAttributesSettings(MeiliSearchConstant.Goods.FILTER_FIELD)),
            // 设置可排序字段
            CompletableFuture.supplyAsync(() -> index.updateSortableAttributesSettings(MeiliSearchConstant.Goods.SORT_FIELD))
        ).collect(Collectors.collectingAndThen(Collectors.toList(), completableFutureList ->
            completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList())
        ));
    }

    @Override
    public GoodsIndex one(Long id) {
        return client.index(MeiliSearchConstant.Goods.INDEX)
            .getDocument(String.valueOf(id), GoodsIndex.class);
    }

    @Override
    public TaskInfo save(GoodsIndex goodsIndex) {
        return client.index(MeiliSearchConstant.Goods.INDEX)
            .addDocuments(JacksonUtil.writeValueAsString(goodsIndex), MeiliSearchConstant.Goods.PRIMARY_KEY);
    }

    @Override
    public List<TaskInfo> saveBatch(List<GoodsIndex> goodsIndexList) {
        TaskInfo[] taskInfos = client.index(MeiliSearchConstant.Goods.INDEX)
            .addDocumentsInBatches(JacksonUtil.writeValueAsString(goodsIndexList), MeiliSearchConstant.BATCH_SIZE, MeiliSearchConstant.Goods.PRIMARY_KEY);
        return Arrays.stream(taskInfos).collect(Collectors.toList());
    }

    @Override
    public TaskInfo update(GoodsIndex goodsIndex) {
        return client.index(MeiliSearchConstant.Goods.INDEX)
            .updateDocuments(JacksonUtil.writeValueAsString(goodsIndex), MeiliSearchConstant.Goods.PRIMARY_KEY);
    }

    @Override
    public List<TaskInfo> updateBatch(List<GoodsIndex> goodsIndexList) {
        TaskInfo[] taskInfos = client.index(MeiliSearchConstant.Goods.INDEX)
            .updateDocumentsInBatches(JacksonUtil.writeValueAsString(goodsIndexList), MeiliSearchConstant.BATCH_SIZE, MeiliSearchConstant.Goods.PRIMARY_KEY);
        return Arrays.stream(taskInfos).collect(Collectors.toList());
    }

    @Override
    public TaskInfo delete(Long id) {
        return client.index(MeiliSearchConstant.Goods.INDEX)
            .deleteDocument(String.valueOf(id));
    }

    @Override
    public List<TaskInfo> deleteBatch(List<Long> ids) {
        Index index = client.index(MeiliSearchConstant.Goods.INDEX);
        return ids.stream()
            .map(id -> index.deleteDocument(String.valueOf(id)))
            .collect(Collectors.toList());
    }

    @Override
    public Searchable search(Integer current, Integer size, String sortField, String sortRule, String keyword) {
        // 组装搜索条件
        SearchRequest.SearchRequestBuilder searchRequestBuilder = SearchRequest.builder()
            // 滚动分页
            .offset((current - 1) * size)
            .limit(size)
            // 排序
            .sort(new String[]{sortField + ":" + sortRule});
        // 如果关键词不为空, 匹配关键词
        if (ObjectUtils.isNotEmpty(keyword)) {
            searchRequestBuilder.q(keyword);
        }
        // 获取搜索命中结果
        return client.index(MeiliSearchConstant.Goods.INDEX)
            .search(searchRequestBuilder.build());
    }
}
