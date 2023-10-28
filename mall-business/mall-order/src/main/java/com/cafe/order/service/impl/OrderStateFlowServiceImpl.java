package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.converter.OrderConverter;
import com.cafe.order.model.Order;
import com.cafe.order.model.OrderDetail;
import com.cafe.order.service.OrderDetailService;
import com.cafe.order.service.OrderService;
import com.cafe.order.service.OrderStateFlowService;
import com.cafe.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2023/10/27 15:00
 * @Description: 订单状态流转业务实现类
 */
@Service
public class OrderStateFlowServiceImpl implements OrderStateFlowService {

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderStateFlowServiceImpl(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @Override
    public void save(OrderVO orderVO) {
        // 保存订单主体
        Order order = OrderConverter.INSTANCE.toModel(orderVO);
        Long orderNo = order.getOrderNo();
        orderService.save(order);
        // 使用订单 NO 获取新保存的订单主体
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(new Order().setOrderNo(orderNo));
        Order newOrder = orderService.getOne(wrapper);

        // 设置订单 ID
        Long orderId = newOrder.getId();
        List<OrderDetail> orderDetailList = orderVO.getOrderDetailList();
        orderDetailList.forEach(orderDetail -> orderDetail.setOrderId(orderId));
        // 保存订单明细
        orderDetailService.saveBatch(orderDetailList);
    }
}
