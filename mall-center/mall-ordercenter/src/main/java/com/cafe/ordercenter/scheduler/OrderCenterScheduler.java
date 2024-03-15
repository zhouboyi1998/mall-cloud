package com.cafe.ordercenter.scheduler;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.ordercenter.service.OrderCenterService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.scheduler
 * @Author: zhouboyi
 * @Date: 2023/10/30 14:51
 * @Description: 订单中心 XXL-JOB 定时任务
 */
@Component
public class OrderCenterScheduler {

    private final OrderCenterService orderCenterService;

    @Autowired
    public OrderCenterScheduler(OrderCenterService orderCenterService) {
        this.orderCenterService = orderCenterService;
    }

    /**
     * 自动取消超时未支付的订单
     */
    @XxlJob(value = "handleAutoCancelUnpaidOrder")
    public ReturnT<String> handleAutoCancelUnpaidOrder() throws Exception {
        orderCenterService.autoCancel(LocalDateTime.now(), IntegerConstant.TEN);
        XxlJobHelper.log("OrderCenterScheduler.handleAutoCancelUnpaidOrder(): auto cancel unpaid order.");
        return ReturnT.SUCCESS;
    }
}
