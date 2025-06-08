package com.cafe.meilisearch.service.impl;

import com.cafe.common.constant.meilisearch.MeiliSearchConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.meilisearch.model.index.GoodsIndex;
import com.cafe.meilisearch.service.GoodsIndexService;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.TaskInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public GoodsIndex one(Long id) {
        return client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .getDocument(String.valueOf(id), GoodsIndex.class);
    }

    @Override
    public TaskInfo save(GoodsIndex goodsIndex) {
        return client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .addDocuments(JacksonUtil.writeValueAsString(goodsIndex), MeiliSearchConstant.Goods.PRIMARY_KEY);
    }

    @Override
    public List<TaskInfo> saveBatch(List<GoodsIndex> goodsIndexList) {
        TaskInfo[] taskInfos = client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .addDocumentsInBatches(JacksonUtil.writeValueAsString(goodsIndexList), IntegerConstant.ONE_THOUSAND, MeiliSearchConstant.Goods.PRIMARY_KEY);
        return Arrays.stream(taskInfos).collect(Collectors.toList());
    }

    @Override
    public TaskInfo update(GoodsIndex goodsIndex) {
        return client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .updateDocuments(JacksonUtil.writeValueAsString(goodsIndex), MeiliSearchConstant.Goods.PRIMARY_KEY);
    }

    @Override
    public List<TaskInfo> updateBatch(List<GoodsIndex> goodsIndexList) {
        TaskInfo[] taskInfos = client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .updateDocumentsInBatches(JacksonUtil.writeValueAsString(goodsIndexList), IntegerConstant.ONE_THOUSAND, MeiliSearchConstant.Goods.PRIMARY_KEY);
        return Arrays.stream(taskInfos).collect(Collectors.toList());
    }

    @Override
    public TaskInfo delete(Long id) {
        return client.getIndex(MeiliSearchConstant.Goods.INDEX)
            .deleteDocument(String.valueOf(id));
    }

    @Override
    public List<TaskInfo> deleteBatch(List<Long> ids) {
        Index index = client.getIndex(MeiliSearchConstant.Goods.INDEX);
        return ids.stream()
            .map(id -> index.deleteDocument(String.valueOf(id)))
            .collect(Collectors.toList());
    }
}
