package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.OrderIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description:
 */
public interface OrderIndexService {

    /**
     * 获取订单索引
     *
     * @param id 订单id
     * @return 订单索引
     */
    OrderIndex one(Long id);

    /**
     * 保存订单索引
     *
     * @param orderIndex 订单索引
     * @return 保存的订单索引
     */
    OrderIndex save(OrderIndex orderIndex);

    /**
     * 批量保存订单索引
     *
     * @param orderIndexList 订单索引列表
     * @return 保存的订单索引列表
     */
    List<OrderIndex> saveBatch(List<OrderIndex> orderIndexList);

    /**
     * 修改订单索引
     *
     * @param orderIndex 订单索引
     * @return 订单索引
     */
    OrderIndex update(OrderIndex orderIndex);

    /**
     * 批量修改订单索引
     * 删除订单索引
     *
     * @param id 订单ID
     */
    void delete(Long id);

    /**
     * 批量插入订单索引
     *
     * @param orderIndexList 订单索引列表
     * @return 修改的订单索引列表
     */
    List<OrderIndex> updateBatch(List<OrderIndex> orderIndexList);

    /**
     * 批量删除订单索引
     *
     * @param ids 订单ID列表
     */
    void deleteBatch(List<Long> ids);

    /**
     * 分页查询订单索引
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<OrderIndex> page(Pageable pageable);

    /**
     * 根据关键词搜索订单索引
     *
     * @param keyword 关键词
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<OrderIndex> search(String keyword, Pageable pageable);
}
