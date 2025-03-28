package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.repository.OrderIndexRepository;
import com.cafe.elasticsearch.service.OrderIndexService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service.impl
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class OrderIndexServiceImpl implements OrderIndexService {

    private final OrderIndexRepository orderIndexRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public OrderIndex one(Long id) {
        return orderIndexRepository.findById(id).orElse(null);
    }

    @Override
    public OrderIndex insert(OrderIndex orderIndex) {
        return orderIndexRepository.save(orderIndex);
    }

    @Override
    public OrderIndex update(OrderIndex orderIndex) {
        return orderIndexRepository.save(orderIndex);
    }

    @Override
    public void delete(Long id) {
        orderIndexRepository.deleteById(id);
    }

    @Override
    public List<OrderIndex> insertBatch(List<OrderIndex> orderIndexList) {
        return (List<OrderIndex>) orderIndexRepository.saveAll(orderIndexList);
    }

    @Override
    public List<OrderIndex> updateBatch(List<OrderIndex> orderIndexList) {
        return (List<OrderIndex>) orderIndexRepository.saveAll(orderIndexList);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        Iterable<OrderIndex> iterable = orderIndexRepository.findAllById(ids);
        List<OrderIndex> orderIndexList = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        orderIndexRepository.deleteAll(orderIndexList);
    }

    @Override
    public Page<OrderIndex> page(Pageable pageable) {
        return orderIndexRepository.findAll(pageable);
    }

    @Override
    public Page<OrderIndex> search(String keyword, Pageable pageable) {
        if (ObjectUtils.isEmpty(keyword)) {
            return orderIndexRepository.findAll(pageable);
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.multiMatchQuery(keyword)
                .field("orderNo", 2.0f)
                .field("customerName")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
            .withPageable(pageable)
            .build();
            
        SearchHits<OrderIndex> searchHits = elasticsearchRestTemplate.search(searchQuery, OrderIndex.class);
        List<OrderIndex> content = searchHits.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
            
        return new PageImpl<>(content, pageable, searchHits.getTotalHits());
    }
}
