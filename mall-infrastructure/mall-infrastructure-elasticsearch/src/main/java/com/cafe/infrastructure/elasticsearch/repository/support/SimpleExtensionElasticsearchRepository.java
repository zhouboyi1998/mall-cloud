package com.cafe.infrastructure.elasticsearch.repository.support;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.lang.Nullable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.repository.support
 * @Author: zhouboyi
 * @Date: 2025/4/17 17:27
 * @Description:
 */
public class SimpleExtensionElasticsearchRepository<T, ID> extends AbstractExtensionElasticsearchRepository<T, ID> {

    public SimpleExtensionElasticsearchRepository(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @Nullable
    @Override
    protected String stringIdRepresentation(@Nullable ID id) {
        return this.operations.stringIdRepresentation(id);
    }
}
