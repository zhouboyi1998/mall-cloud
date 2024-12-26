package com.cafe.order.feign;

import com.cafe.common.constant.date.DateTimeConstant;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.feign
 * @Author: zhouboyi
 * @Date: 2023/10/27 15:07
 * @Description:
 */
@FeignClient(value = "mall-order", contextId = "order-flow", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/order-flow")
public interface OrderFlowFeign {

    /**
     * 保存订单
     *
     * @param orderVO 订单数据
     * @return 新保存的订单
     */
    @PostMapping(value = "/save")
    ResponseEntity<OrderVO> save(@RequestBody OrderVO orderVO);

    /**
     * 取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     * @return 被取消的订单明细
     */
    @PutMapping(value = "/cancel/{now}/{duration}")
    ResponseEntity<List<OrderItem>> cancel(
        @PathVariable(value = "now") @DateTimeFormat(pattern = DateTimeConstant.DEFAULT_DATE_TIME) LocalDateTime now,
        @PathVariable(value = "duration") Integer duration
    );
}
