package com.cafe.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.order.model.entity.OrderItem;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细业务接口
 */
public interface OrderItemService extends IService<OrderItem> {

    /**
     * 评价订单明细
     *
     * @param orderItemId 订单明细ID
     * @return 是否评价成功
     */
    Boolean review(Long orderItemId);

    /**
     * 批量评价订单明细
     *
     * @param orderItemIds 订单明细ID列表
     * @return 是否评价成功
     */
    Boolean reviewBatch(List<Long> orderItemIds);
}
