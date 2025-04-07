package com.cafe.elasticsearch.service.impl;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.repository.OrderIndexRepository;
import com.cafe.elasticsearch.service.OrderIndexService;
import com.cafe.elasticsearch.util.DocumentUtil;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;

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
 * @Date: 2024/6/28 16:18
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class OrderIndexServiceImpl implements OrderIndexService {

    private final OrderIndexRepository orderIndexRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public OrderIndex one(Long id) {
        return orderIndexRepository.findById(id).orElse(new OrderIndex());
    }

    @Override
    public OrderIndex save(OrderIndex orderIndex) {
        return orderIndexRepository.save(orderIndex);
    }

    @Override
    public List<OrderIndex> saveBatch(List<OrderIndex> orderIndexList) {
        Iterable<OrderIndex> saveIterable = orderIndexRepository.saveAll(orderIndexList);
        return StreamSupport.stream(saveIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public OrderIndex update(OrderIndex orderIndex) {
        // 获取订单索引
        OrderIndex currentOrderIndex = orderIndexRepository.findById(orderIndex.getId())
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.ORDER_INDEX_NOT_FOUND, orderIndex));
        // 组装修改参数
        Document document = DocumentUtil.updateDocument(currentOrderIndex, orderIndex);
        // 组装修改请求
        UpdateQuery updateQuery = UpdateQuery.builder(String.valueOf(orderIndex.getId()))
            .withDocument(document)
            .build();
        // 修改订单索引
        UpdateResponse updateResponse = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(ElasticSearchConstant.Order.INDEX));
        // 判断修改结果
        if (!Objects.equals(updateResponse.getResult(), UpdateResponse.Result.UPDATED)) {
            throw new BusinessException(HttpStatusEnum.ORDER_INDEX_UPDATE_FAIL, orderIndex);
        }
        return JacksonUtil.convertValue(document, OrderIndex.class);
    }

    @Override
    public List<OrderIndex> updateBatch(List<OrderIndex> orderIndexList) {
        Map<Long, OrderIndex> orderIndexMap = orderIndexList.stream()
            .collect(Collectors.toMap(OrderIndex::getId, Function.identity()));
        // 获取当前的订单索引列表
        Iterable<OrderIndex> currentOrderIndexIterable = orderIndexRepository.findAllById(orderIndexMap.keySet());
        // 组装批量修改参数
        List<Document> documentList = StreamSupport.stream(currentOrderIndexIterable.spliterator(), false)
            .map(currentOrderIndex -> DocumentUtil.updateDocument(currentOrderIndex, orderIndexMap.get(currentOrderIndex.getId())))
            .collect(Collectors.toList());
        // 组装批量修改请求
        List<UpdateQuery> updateQueryList = documentList.stream()
            .map(document -> UpdateQuery.builder(String.valueOf(document.get(FieldConstant.ID))).withDocument(document).build())
            .collect(Collectors.toList());
        // 批量修改订单索引
        elasticsearchRestTemplate.bulkUpdate(updateQueryList, IndexCoordinates.of(ElasticSearchConstant.Order.INDEX));
        // 返回修改后的订单索引列表
        return documentList.stream()
            .map(document -> JacksonUtil.convertValue(document, OrderIndex.class))
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderIndexRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        List<OrderIndex> orderIndexList = ids.stream().map(id -> new OrderIndex().setId(id)).collect(Collectors.toList());
        orderIndexRepository.deleteAll(orderIndexList);
    }
}
