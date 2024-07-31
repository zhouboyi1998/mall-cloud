package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.index.OrderIndex;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
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
     * @throws IOException
     */
    GetResponse one(String id) throws IOException;

    /**
     * 插入订单索引
     *
     * @param orderIndex
     * @return
     * @throws IOException
     */
    IndexResponse insert(OrderIndex orderIndex) throws IOException;

    /**
     * 更新订单索引
     *
     * @param orderIndex
     * @return
     * @throws IOException
     */
    UpdateResponse update(OrderIndex orderIndex) throws IOException;

    /**
     * 删除订单索引
     *
     * @param id
     * @return
     * @throws IOException
     */
    DeleteResponse delete(String id) throws IOException;

    /**
     * 批量插入订单索引
     *
     * @param orderIndexList
     * @return
     * @throws IOException
     */
    BulkResponse insertBatch(List<OrderIndex> orderIndexList) throws IOException;

    /**
     * 批量更新订单索引
     *
     * @param orderIndexList
     * @return
     * @throws IOException
     */
    BulkResponse updateBatch(List<OrderIndex> orderIndexList) throws IOException;

    /**
     * 批量删除订单索引
     *
     * @param ids
     * @return
     * @throws IOException
     */
    BulkResponse deleteBatch(List<String> ids) throws IOException;
}
