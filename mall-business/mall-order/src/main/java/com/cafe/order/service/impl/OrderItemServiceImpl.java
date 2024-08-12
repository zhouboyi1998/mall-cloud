package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.order.mapper.OrderItemMapper;
import com.cafe.order.model.OrderItem;
import com.cafe.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细表业务实现类
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }
}
