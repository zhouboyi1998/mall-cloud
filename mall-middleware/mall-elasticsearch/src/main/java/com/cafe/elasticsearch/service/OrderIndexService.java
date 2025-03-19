package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.OrderIndex;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

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
     * @param id 订单ID
     * @return 获取响应
     */
    GetResponse one(String id);

    /**
     * 插入订单索引
     *
     * @param orderIndex 订单索引
     * @return 索引响应
     */
    IndexResponse insert(OrderIndex orderIndex);

    /**
     * 更新订单索引
     *
     * @param orderIndex 订单索引
     * @return 更新响应
     */
    UpdateResponse update(OrderIndex orderIndex);

    /**
     * 删除订单索引
     *
     * @param id 订单ID
     * @return 删除响应
     */
    DeleteResponse delete(String id);

    /**
     * 批量插入订单索引
     *
     * @param orderIndexList 订单索引列表
     * @return 主体响应
     */
    BulkResponse insertBatch(List<OrderIndex> orderIndexList);

    /**
     * 批量更新订单索引
     *
     * @param orderIndexList 订单索引列表
     * @return 主体响应
     */
    BulkResponse updateBatch(List<OrderIndex> orderIndexList);

    /**
     * 批量删除订单索引
     *
     * @param ids 订单ID列表
     * @return 主体响应
     */
    BulkResponse deleteBatch(List<String> ids);
}
