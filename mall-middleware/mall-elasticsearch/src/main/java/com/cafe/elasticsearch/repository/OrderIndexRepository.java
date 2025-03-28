package com.cafe.elasticsearch.repository;

import com.cafe.elasticsearch.model.index.OrderIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderIndexRepository extends ElasticsearchRepository<OrderIndex, Long> {
    
    /**
     * 根据订单号或客户名称搜索
     *
     * @param orderNo 订单号
     * @param customerName 客户名称
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<OrderIndex> findByOrderNoContainingOrCustomerNameContaining(String orderNo, String customerName, Pageable pageable);
} 