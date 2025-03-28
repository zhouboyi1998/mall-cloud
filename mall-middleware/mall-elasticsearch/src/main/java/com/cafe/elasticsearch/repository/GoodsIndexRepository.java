package com.cafe.elasticsearch.repository;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsIndexRepository extends ElasticsearchRepository<GoodsIndex, Long> {
}
