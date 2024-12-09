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
     * @param id
     * @return
     */
    GetResponse one(String id);

    /**
     * 插入订单索引
     *
     * @param orderIndex
     * @return
     */
    IndexResponse insert(OrderIndex orderIndex);

    /**
     * 更新订单索引
     *
     * @param orderIndex
     * @return
     */
    UpdateResponse update(OrderIndex orderIndex);

    /**
     * 删除订单索引
     *
     * @param id
     * @return
     */
    DeleteResponse delete(String id);

    /**
     * 批量插入订单索引
     *
     * @param orderIndexList
     * @return
     */
    BulkResponse insertBatch(List<OrderIndex> orderIndexList);

    /**
     * 批量更新订单索引
     *
     * @param orderIndexList
     * @return
     */
    BulkResponse updateBatch(List<OrderIndex> orderIndexList);

    /**
     * 批量删除订单索引
     *
     * @param ids
     * @return
     */
    BulkResponse deleteBatch(List<String> ids);
}
