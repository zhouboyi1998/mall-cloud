package com.cafe.elasticsearch.repository;

import com.cafe.elasticsearch.model.index.OrderIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.repository
 * @Author: zhouboyi
 * @Date: 2025/4/7 16:38
 * @Description:
 */
@Repository
public interface OrderIndexRepository extends ElasticsearchRepository<OrderIndex, Long> {

}
