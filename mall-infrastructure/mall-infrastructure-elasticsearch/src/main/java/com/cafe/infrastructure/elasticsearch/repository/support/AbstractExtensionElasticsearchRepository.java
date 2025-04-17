package com.cafe.infrastructure.elasticsearch.repository.support;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.infrastructure.elasticsearch.repository.ExtensionElasticsearchRepository;
import com.cafe.infrastructure.elasticsearch.util.DocumentUtil;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.elasticsearch.repository.support
 * @Author: zhouboyi
 * @Date: 2025/4/17 16:14
 * @Description:
 */
public abstract class AbstractExtensionElasticsearchRepository<T, ID> extends AbstractElasticsearchRepository<T, ID> implements ExtensionElasticsearchRepository<T, ID> {

    public AbstractExtensionElasticsearchRepository(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, "Cannot update 'null' entity.");
        // 获取索引坐标
        IndexCoordinates indexCoordinates = this.getIndexCoordinates();
        // 获取文档ID
        ID documentId = this.extractIdFromBean(entity);
        // 查询文档当前的数据
        T currentDocument = this.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found."));
        // 组装修改参数
        Document document = DocumentUtil.updateDocument(currentDocument, entity);
        // 构建修改请求
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(documentId)).withDocument(document).build();
        // 修改文档
        UpdateResponse updateResponse = this.operations.update(updateQuery, indexCoordinates);
        // 判断修改结果
        if (!Objects.equals(updateResponse.getResult(), UpdateResponse.Result.UPDATED)) {
            throw new RuntimeException("Document update fail.");
        }
        // 返回修改后的文档
        return JacksonUtil.convertValue(document, (Class<S>) entity.getClass());
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public <S extends T> Iterable<S> updateAll(Iterable<S> entityIterable) {
        List<S> entityList = StreamSupport.stream(entityIterable.spliterator(), false).collect(Collectors.toList());
        Assert.notEmpty(entityList, "Cannot update 'empty' entity list.");
        // 获取索引坐标
        IndexCoordinates indexCoordinates = this.getIndexCoordinates();
        // 获取文档ID与文档的映射
        Map<ID, S> entityMap = entityList.stream().collect(Collectors.toMap(this::extractIdFromBean, Function.identity()));
        // 查询文档列表当前的数据
        Iterable<T> currentDocumentIterable = this.findAllById(entityMap.keySet());
        // 组装修改参数列表
        List<Document> documentList = StreamSupport.stream(currentDocumentIterable.spliterator(), false)
            .map(currentDocument -> DocumentUtil.updateDocument(currentDocument, entityMap.get(this.extractIdFromBean(currentDocument))))
            .collect(Collectors.toList());
        // 构建修改请求列表
        List<UpdateQuery> updateQueryList = documentList.stream()
            .map(document -> UpdateQuery.builder(String.valueOf(document.get(FieldConstant.ID))).withDocument(document).build())
            .collect(Collectors.toList());
        // 批量修改文档
        this.operations.bulkUpdate(updateQueryList, indexCoordinates);
        // 返回修改后的文档列表
        return documentList.stream()
            .map(document -> JacksonUtil.convertValue(document, (Class<S>) entityList.get(0).getClass()))
            .collect(Collectors.toList());
    }

    @NonNull
    @Override
    protected ID extractIdFromBean(@NonNull T entity) {
        return Optional.ofNullable(super.extractIdFromBean(entity)).orElseThrow(() -> new RuntimeException("Cannot get entity id."));
    }

    private IndexCoordinates getIndexCoordinates() {
        return this.operations.getIndexCoordinatesFor(this.getEntityClass());
    }
}
