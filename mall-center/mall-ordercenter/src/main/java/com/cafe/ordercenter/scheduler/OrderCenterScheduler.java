package com.cafe.ordercenter.scheduler;

import com.cafe.common.constant.date.DateTimeConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.ordercenter.service.OrderCenterService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.scheduler
 * @Author: zhouboyi
 * @Date: 2023/10/30 14:51
 * @Description: 订单中心 XXL-JOB 定时任务
 */
@RequiredArgsConstructor
@Component
public class OrderCenterScheduler {

    private final OrderCenterService orderCenterService;

    /**
     * 定时取消超时未支付的订单
     */
    @XxlJob(value = "handleCancelTimeoutUnpaidOrder")
    public ReturnT<String> handleCancelTimeoutUnpaidOrder() {
        LocalDateTime now = LocalDateTime.now();
        orderCenterService.cancel(now, IntegerConstant.TEN);
        XxlJobHelper.log("OrderCenterScheduler.handleCancelTimeoutUnpaidOrder(): Successful to cancel timeout unpaid order. execute time -> {}",
            now.format(DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE_TIME)));
        return ReturnT.SUCCESS;
    }
}
