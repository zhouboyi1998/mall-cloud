package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.order.mapper.OrderItemMapper;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细业务实现类
 */
@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    private final OrderItemMapper orderItemMapper;

    @Override
    public Boolean review(Long orderItemId) {
        return lambdaUpdate()
            .eq(OrderItem::getId, orderItemId)
            .set(OrderItem::getReview, OrderConstant.Review.REVIEWED)
            .update();
    }

    @Override
    public Boolean reviewBatch(List<Long> orderItemIds) {
        return lambdaUpdate()
            .in(OrderItem::getId, orderItemIds)
            .set(OrderItem::getReview, OrderConstant.Review.REVIEWED)
            .update();
    }
}
