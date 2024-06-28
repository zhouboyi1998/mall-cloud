package com.cafe.elasticsearch.service;

import com.cafe.order.vo.OrderVO;
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
public interface OrderService {

    /**
     * 获取订单
     *
     * @param id
     * @return
     * @throws IOException
     */
    GetResponse one(String id) throws IOException;

    /**
     * 插入订单
     *
     * @param orderVO
     * @return
     * @throws IOException
     */
    IndexResponse insert(OrderVO orderVO) throws IOException;

    /**
     * 更新订单
     *
     * @param orderVO
     * @return
     * @throws IOException
     */
    UpdateResponse update(OrderVO orderVO) throws IOException;

    /**
     * 删除订单
     *
     * @param id
     * @return
     * @throws IOException
     */
    DeleteResponse delete(String id) throws IOException;

    /**
     * 批量插入订单
     *
     * @param orderVOList
     * @return
     * @throws IOException
     */
    BulkResponse insertBatch(List<OrderVO> orderVOList) throws IOException;

    /**
     * 批量更新订单
     *
     * @param orderVOList
     * @return
     * @throws IOException
     */
    BulkResponse updateBatch(List<OrderVO> orderVOList) throws IOException;

    /**
     * 批量删除订单
     *
     * @param ids
     * @return
     * @throws IOException
     */
    BulkResponse deleteBatch(List<String> ids) throws IOException;
}
