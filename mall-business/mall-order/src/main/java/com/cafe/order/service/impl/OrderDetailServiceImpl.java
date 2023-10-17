package com.cafe.order.service.impl;

import com.cafe.order.model.OrderDetail;
import com.cafe.order.mapper.OrderDetailMapper;
import com.cafe.order.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }
}
