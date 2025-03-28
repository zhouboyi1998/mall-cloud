package com.cafe.elasticsearch.service.impl;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.repository.GoodsIndexRepository;
import com.cafe.elasticsearch.service.GoodsIndexService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @Date: 2022/7/28 9:39
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class GoodsIndexServiceImpl implements GoodsIndexService {

    private final GoodsIndexRepository goodsIndexRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public GoodsIndex one(Long id) {
        return goodsIndexRepository.findById(id).orElse(null);
    }

    @Override
    public GoodsIndex insert(GoodsIndex goodsIndex) {
        return goodsIndexRepository.save(goodsIndex);
    }

    @Override
    public GoodsIndex update(GoodsIndex goodsIndex) {
        return goodsIndexRepository.save(goodsIndex);
    }

    @Override
    public void delete(Long id) {
        goodsIndexRepository.deleteById(id);
    }

    @Override
    public List<GoodsIndex> insertBatch(List<GoodsIndex> goodsIndexList) {
        Iterable<GoodsIndex> iterable = goodsIndexRepository.saveAll(goodsIndexList);
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<GoodsIndex> updateBatch(List<GoodsIndex> goodsIndexList) {
        Iterable<GoodsIndex> iterable = goodsIndexRepository.saveAll(goodsIndexList);
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        Iterable<GoodsIndex> iterable = goodsIndexRepository.findAllById(ids);
        List<GoodsIndex> goodsIndexList = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        goodsIndexRepository.deleteAll(goodsIndexList);
    }

    @Override
    public List<GoodsIndex> list(String keyword) {
        if (ObjectUtils.isEmpty(keyword)) {
            return (List<GoodsIndex>) goodsIndexRepository.findAll();
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.multiMatchQuery(keyword)
                .field("name", 2.0f)
                .field("description")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
            .build();
            
        SearchHits<GoodsIndex> searchHits = elasticsearchRestTemplate.search(searchQuery, GoodsIndex.class);
        return searchHits.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    @Override
    public Page<GoodsIndex> page(Integer current, Integer size, String keyword, String sort, String rule) {
        PageRequest pageRequest = PageRequest.of(current - 1, size, Sort.by(Sort.Direction.fromString(rule), sort));
        
        if (ObjectUtils.isEmpty(keyword)) {
            return goodsIndexRepository.findAll(pageRequest);
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.multiMatchQuery(keyword)
                .field("name", 2.0f)
                .field("description")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
            .withPageable(pageRequest)
            .build();
            
        SearchHits<GoodsIndex> searchHits = elasticsearchRestTemplate.search(searchQuery, GoodsIndex.class);
        List<GoodsIndex> content = searchHits.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
            
        return new PageImpl<>(content, pageRequest, searchHits.getTotalHits());
    }
}
