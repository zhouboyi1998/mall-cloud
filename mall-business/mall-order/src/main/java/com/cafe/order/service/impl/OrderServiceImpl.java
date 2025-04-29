package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.order.mapper.OrderMapper;
import com.cafe.order.model.entity.Order;
import com.cafe.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单业务实现类
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public Boolean review(Long orderId) {
        return lambdaUpdate()
            .eq(Order::getId, orderId)
            .set(Order::getReview, OrderConstant.Review.REVIEWED)
            .update();
    }

    @Override
    public Boolean reviewBatch(List<Long> orderIds) {
        return lambdaUpdate()
            .in(Order::getId, orderIds)
            .set(Order::getReview, OrderConstant.Review.REVIEWED)
            .update();
    }
}
