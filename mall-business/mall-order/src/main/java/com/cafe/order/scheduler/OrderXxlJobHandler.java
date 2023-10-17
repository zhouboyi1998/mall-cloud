package com.cafe.order.scheduler;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cafe.common.constant.model.OrderConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.order.mapper.OrderMapper;
import com.cafe.order.model.Order;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.scheduler
 * @Author: zhouboyi
 * @Date: 2023/5/5 14:54
 * @Description: 订单相关 XXL-JOB 定时任务
 */
@Component
public class OrderXxlJobHandler {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderXxlJobHandler(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 自动取消未支付的订单
     */
    @XxlJob(value = "handleCancelUnpaidOrder")
    public ReturnT<String> handleCancelUnpaidOrder() throws Exception {
        // 取消下单超过 10 分钟仍未支付的订单
        LocalDateTime now = LocalDateTime.now();
        LambdaUpdateWrapper<Order> wrapper = new LambdaUpdateWrapper<Order>()
            .eq(Order::getStatus, OrderConstant.Status.CREATE)
            .le(Order::getCreateTime, now.minusMinutes(IntegerConstant.TEN))
            .set(Order::getStatus, OrderConstant.Status.CANCEL)
            .set(Order::getUpdateTime, now)
            .set(Order::getCompletionTime, now);
        Integer count = orderMapper.update(null, wrapper);
        XxlJobHelper.log("OrderXxlJobHandler.handleCancelUnpaidOrder(): cancel unpaid order count -> {}", count);
        return ReturnT.SUCCESS;
    }
}
