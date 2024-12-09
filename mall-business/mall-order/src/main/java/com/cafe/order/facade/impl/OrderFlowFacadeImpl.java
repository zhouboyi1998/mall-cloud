package com.cafe.order.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.facade.OrderFlowFacade;
import com.cafe.order.model.converter.OrderConverter;
import com.cafe.order.model.entity.Order;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.order.service.OrderItemService;
import com.cafe.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.facade.impl
 * @Author: zhouboyi
 * @Date: 2023/10/27 15:00
 * @Description: 订单流转防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class OrderFlowFacadeImpl implements OrderFlowFacade {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 30,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public OrderVO save(OrderVO orderVO) {
        // 保存订单主体
        Order order = OrderConverter.INSTANCE.toEntity(orderVO);
        orderService.save(order);

        // 使用订单编号获取新保存的订单主体
        Order newOrder = orderService.getOne(WrapperUtil.createQueryWrapper(new Order().setOrderNo(order.getOrderNo())));

        // 遍历订单明细, 设置订单id
        Long orderId = newOrder.getId();
        List<OrderItem> orderItemList = orderVO.getOrderItemList();
        orderItemList.forEach(orderItem -> orderItem.setOrderId(orderId));
        // 保存订单明细
        orderItemService.saveBatch(orderItemList);

        // 使用订单id获取新保存的订单明细列表
        List<OrderItem> newOrderItemList = orderItemService.list(WrapperUtil.createQueryWrapper(new OrderItem().setOrderId(orderId)));

        // 返回新保存的订单
        return OrderConverter.INSTANCE.toVO(newOrder).setOrderItemList(newOrderItemList);
    }

    @Override
    public List<OrderItem> cancel(LocalDateTime now, Integer duration) {
        // 取消超时未支付的订单
        LambdaUpdateWrapper<Order> orderUpdateWrapper = new LambdaUpdateWrapper<Order>()
            .eq(Order::getStatus, OrderConstant.Status.CREATE)
            .le(Order::getCreateTime, now.minusMinutes(IntegerConstant.TEN))
            .set(Order::getStatus, OrderConstant.Status.CANCEL)
            .set(Order::getUpdateTime, now)
            .set(Order::getCompletionTime, now);
        orderService.update(orderUpdateWrapper);

        // 查询超时未支付的订单明细 (用于还原库存)
        LambdaQueryWrapper<OrderItem> orderItemQueryWrapper = new LambdaQueryWrapper<OrderItem>()
            .eq(OrderItem::getStatus, OrderConstant.Status.CREATE)
            .le(OrderItem::getCreateTime, now.minusMinutes(IntegerConstant.TEN));
        List<OrderItem> orderItemList = orderItemService.list(orderItemQueryWrapper);

        // 订单明细状态设置为取消
        LambdaUpdateWrapper<OrderItem> orderItemUpdateWrapper = new LambdaUpdateWrapper<OrderItem>()
            .eq(OrderItem::getStatus, OrderConstant.Status.CREATE)
            .le(OrderItem::getCreateTime, now.minusMinutes(IntegerConstant.TEN))
            .set(OrderItem::getStatus, OrderConstant.Status.CANCEL)
            .set(OrderItem::getUpdateTime, now)
            .set(OrderItem::getCompletionTime, now);
        orderItemService.update(orderItemUpdateWrapper);

        return orderItemList;
    }
}
