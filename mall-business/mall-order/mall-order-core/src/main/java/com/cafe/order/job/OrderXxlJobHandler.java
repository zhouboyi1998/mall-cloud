package com.cafe.order.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cafe.common.constant.pool.NumberConstant;
import com.cafe.common.constant.status.OrderStatusConstant;
import com.cafe.order.mapper.OrderMapper;
import com.cafe.order.model.Order;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.job
 * @Author: zhouboyi
 * @Date: 2023/5/5 14:54
 * @Description: 订单相关定时任务
 */
@Component
public class OrderXxlJobHandler {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderXxlJobHandler(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 处理未支付的订单
     */
    @XxlJob(value = "handleUnpaidOrder")
    public void handleUnpaidOrder() throws Exception {
        // 自动取消下单 10 分钟仍未支付的订单
        LocalDateTime now = LocalDateTime.now();
        LambdaUpdateWrapper<Order> wrapper = new LambdaUpdateWrapper<Order>()
            .eq(Order::getStatus, OrderStatusConstant.CREATE)
            .le(Order::getCreateTime, now.minusMinutes(NumberConstant.TEN))
            .set(Order::getStatus, OrderStatusConstant.CANCEL)
            .set(Order::getUpdateTime, now)
            .set(Order::getCompletionTime, now);
        Integer count = orderMapper.update(null, wrapper);
        XxlJobHelper.log("OrderXxlJobHandler.handleUnpaidOrder(): cancel unpaid order count -> {}", count);
    }
}
