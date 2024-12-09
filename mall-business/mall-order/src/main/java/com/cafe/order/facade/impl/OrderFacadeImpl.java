package com.cafe.order.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.lang.date.LocalDateTimePeriod;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.facade.OrderFacade;
import com.cafe.order.model.converter.OrderConverter;
import com.cafe.order.model.entity.Order;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.query.OrderQuery;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.order.service.OrderItemService;
import com.cafe.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.facade.impl
 * @Author: zhouboyi
 * @Date: 2024/12/6 19:27
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @Override
    public Page<OrderVO> query(Page<Order> page, OrderQuery orderQuery) {
        // 构建订单明细 QueryWrapper, 添加订单明细查询条件
        LambdaQueryWrapper<OrderItem> orderItemQueryWrapper = Optional.ofNullable(orderQuery.getOrderItem())
            .map(WrapperUtil::createLambdaQueryWrapper)
            .orElse(WrapperUtil.emptyLambdaQueryWrapper(new OrderItem()));
        // 查询订单明细列表
        List<OrderItem> orderItemList = orderItemService.list(orderItemQueryWrapper);
        Set<Long> orderIdSet = orderItemList.stream().map(OrderItem::getOrderId).collect(Collectors.toSet());

        // 构建订单 QueryWrapper, 添加订单查询条件
        Order order = OrderConverter.INSTANCE.toEntity(orderQuery);
        LambdaQueryWrapper<Order> orderQueryWrapper = WrapperUtil.createLambdaQueryWrapper(order);
        // 添加订单ID查询条件
        orderQueryWrapper.in(!CollectionUtils.isEmpty(orderIdSet), Order::getId, orderIdSet);
        // 获取所有时间区间查询条件
        LocalDateTimePeriod createTimePeriod = Optional.ofNullable(orderQuery.getCreateTimePeriod()).orElse(new LocalDateTimePeriod());
        LocalDateTimePeriod updateTimePeriod = Optional.ofNullable(orderQuery.getUpdateTimePeriod()).orElse(new LocalDateTimePeriod());
        LocalDateTimePeriod paymentTimePeriod = Optional.ofNullable(orderQuery.getPaymentTimePeriod()).orElse(new LocalDateTimePeriod());
        LocalDateTimePeriod deliverTimePeriod = Optional.ofNullable(orderQuery.getDeliverTimePeriod()).orElse(new LocalDateTimePeriod());
        LocalDateTimePeriod completionTimePeriod = Optional.ofNullable(orderQuery.getCompletionTimePeriod()).orElse(new LocalDateTimePeriod());
        // 添加时间区间查询条件
        orderQueryWrapper
            .ge(Objects.nonNull(createTimePeriod.getStart()), Order::getCreateTime, createTimePeriod.getStart())
            .le(Objects.nonNull(createTimePeriod.getEnd()), Order::getCreateTime, createTimePeriod.getEnd())
            .ge(Objects.nonNull(updateTimePeriod.getStart()), Order::getUpdateTime, updateTimePeriod.getStart())
            .le(Objects.nonNull(updateTimePeriod.getEnd()), Order::getUpdateTime, updateTimePeriod.getEnd())
            .ge(Objects.nonNull(paymentTimePeriod.getStart()), Order::getPaymentTime, paymentTimePeriod.getStart())
            .le(Objects.nonNull(paymentTimePeriod.getEnd()), Order::getPaymentTime, paymentTimePeriod.getEnd())
            .ge(Objects.nonNull(deliverTimePeriod.getStart()), Order::getDeliverTime, deliverTimePeriod.getStart())
            .le(Objects.nonNull(deliverTimePeriod.getEnd()), Order::getDeliverTime, deliverTimePeriod.getEnd())
            .ge(Objects.nonNull(completionTimePeriod.getStart()), Order::getCompletionTime, completionTimePeriod.getStart())
            .le(Objects.nonNull(completionTimePeriod.getEnd()), Order::getCompletionTime, completionTimePeriod.getEnd());
        // 分页查询订单列表
        Page<Order> orderPage = orderService.page(page, orderQueryWrapper);
        List<Order> orderList = orderPage.getRecords();
        List<Long> orderIdList = orderList.stream().map(Order::getId).collect(Collectors.toList());

        // 订单明细按订单ID分组
        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream()
            .filter(orderItem -> orderIdList.contains(orderItem.getOrderId()))
            .collect(Collectors.groupingBy(OrderItem::getOrderId));
        // 组装订单明细到对应的订单视图中
        List<OrderVO> orderVOList = OrderConverter.INSTANCE.toVOList(orderList).stream()
            .peek(orderVO -> orderVO.setOrderItemList(orderItemMap.get(orderVO.getId())))
            .collect(Collectors.toList());
        return new Page<OrderVO>(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal())
            .setRecords(orderVOList);
    }
}
