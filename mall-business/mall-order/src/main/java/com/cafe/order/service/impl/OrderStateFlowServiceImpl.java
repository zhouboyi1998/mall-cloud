package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.common.constant.pool.IntegerConstant;
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

import java.time.LocalDateTime;
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
    public OrderVO save(OrderVO orderVO) {
        // 保存订单主体
        Order order = OrderConverter.INSTANCE.toModel(orderVO);
        orderService.save(order);

        // 使用订单编号获取新保存的订单主体
        Order newOrder = orderService.getOne(WrapperUtil.createQueryWrapper(new Order().setOrderNo(order.getOrderNo())));

        // 遍历订单明细, 设置订单id
        Long orderId = newOrder.getId();
        List<OrderDetail> orderDetailList = orderVO.getOrderDetailList();
        orderDetailList.forEach(orderDetail -> orderDetail.setOrderId(orderId));
        // 保存订单明细
        orderDetailService.saveBatch(orderDetailList);

        // 使用订单id获取新保存的订单明细列表
        List<OrderDetail> newOrderDetailList = orderDetailService.list(WrapperUtil.createQueryWrapper(new OrderDetail().setOrderId(orderId)));

        // 返回新保存的订单
        return OrderConverter.INSTANCE.toVO(newOrder).setOrderDetailList(newOrderDetailList);
    }

    @Override
    public List<OrderDetail> autoCancel(LocalDateTime now, Integer duration) {
        // 自动取消超时未支付的订单
        LambdaUpdateWrapper<Order> orderUpdateWrapper = new LambdaUpdateWrapper<Order>()
            .eq(Order::getStatus, OrderConstant.Status.CREATE)
            .le(Order::getCreateTime, now.minusMinutes(IntegerConstant.TEN))
            .set(Order::getStatus, OrderConstant.Status.CANCEL)
            .set(Order::getUpdateTime, now)
            .set(Order::getCompletionTime, now);
        orderService.update(orderUpdateWrapper);

        // 查询自动取消的订单明细 (用于还原库存)
        LambdaQueryWrapper<OrderDetail> orderDetailQueryWrapper = new LambdaQueryWrapper<OrderDetail>()
            .eq(OrderDetail::getStatus, OrderConstant.Status.CREATE)
            .le(OrderDetail::getCreateTime, now.minusMinutes(IntegerConstant.TEN));
        List<OrderDetail> orderDetailList = orderDetailService.list(orderDetailQueryWrapper);

        // 订单明细状态设置为取消
        LambdaUpdateWrapper<OrderDetail> orderDetailUpdateWrapper = new LambdaUpdateWrapper<OrderDetail>()
            .eq(OrderDetail::getStatus, OrderConstant.Status.CREATE)
            .le(OrderDetail::getCreateTime, now.minusMinutes(IntegerConstant.TEN))
            .set(OrderDetail::getStatus, OrderConstant.Status.CANCEL)
            .set(OrderDetail::getUpdateTime, now)
            .set(OrderDetail::getCompletionTime, now);
        orderDetailService.update(orderDetailUpdateWrapper);

        return orderDetailList;
    }
}
