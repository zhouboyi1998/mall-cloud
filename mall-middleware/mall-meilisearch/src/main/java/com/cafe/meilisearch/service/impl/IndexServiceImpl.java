package com.cafe.meilisearch.service.impl;

import com.cafe.meilisearch.model.converter.IndexConverter;
import com.cafe.meilisearch.model.dto.IndexDTO;
import com.cafe.meilisearch.model.dto.Page;
import com.cafe.meilisearch.service.IndexService;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.IndexesQuery;
import com.meilisearch.sdk.model.Results;
import com.meilisearch.sdk.model.TaskInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service.impl
 * @Author: zhouboyi
 * @Date: 2025/6/2 2:33
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class IndexServiceImpl implements IndexService {

    private final Client client;

    @Override
    public List<IndexDTO> list() {
        Results<Index> indexResults = client.getIndexes();
        List<Index> indexList = Arrays.asList(indexResults.getResults());
        return IndexConverter.INSTANCE.toDTOList(indexList);
    }

    @Override
    public Page<IndexDTO> page(IndexesQuery indexesQuery) {
        Results<Index> indexResults = client.getIndexes(indexesQuery);
        List<Index> indexList = Arrays.asList(indexResults.getResults());
        List<IndexDTO> indexDTOList = IndexConverter.INSTANCE.toDTOList(indexList);
        return new Page<>(indexDTOList, indexResults.getLimit(), indexResults.getOffset(), indexResults.getTotal());
    }

    @Override
    public IndexDTO one(String uid) {
        Index index = client.getIndex(uid);
        return IndexConverter.INSTANCE.toDTO(index);
    }

    @Override
    public TaskInfo create(String uid, String primaryKey) {
        return StringUtils.hasText(primaryKey) ? client.createIndex(uid, primaryKey) : client.createIndex(uid);
    }

    @Override
    public TaskInfo update(String uid, String primaryKey) {
        return client.updateIndex(uid, primaryKey);
    }

    @Override
    public TaskInfo delete(String uid) {
        return client.deleteIndex(uid);
    }
}
