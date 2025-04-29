package com.cafe.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.order.model.entity.Order;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单业务接口
 */
public interface OrderService extends IService<Order> {

    /**
     * 评价订单
     *
     * @param orderId 订单ID
     * @return 是否评价成功
     */
    Boolean review(Long orderId);

    /**
     * 批量评价订单
     *
     * @param orderIds 订单ID列表
     * @return 是否评价成功
     */
    Boolean reviewBatch(List<Long> orderIds);
}
