package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.OrderIndex;

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
     * @return 修改的订单索引
     */
    OrderIndex update(OrderIndex orderIndex);

    /**
     * 批量修改订单索引
     *
     * @param orderIndexList 订单索引列表
     * @return 修改的订单索引列表
     */
    List<OrderIndex> updateBatch(List<OrderIndex> orderIndexList);

    /**
     * 删除订单索引
     *
     * @param id 订单id
     */
    void delete(Long id);

    /**
     * 批量删除订单索引
     *
     * @param ids 订单id列表
     */
    void deleteBatch(List<Long> ids);
}
