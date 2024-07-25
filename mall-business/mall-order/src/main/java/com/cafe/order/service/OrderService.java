package com.cafe.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.order.model.Order;
import com.cafe.order.query.OrderQuery;
import com.cafe.order.vo.OrderVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单业务接口
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据查询条件查询订单列表
     *
     * @param page
     * @param orderQuery
     * @return
     */
    Page<OrderVO> query(Page<Order> page, OrderQuery orderQuery);
}
