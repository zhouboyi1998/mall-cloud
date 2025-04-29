package com.cafe.order.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.feign
 * @Author: zhouboyi
 * @Date: 2025/5/9 16:57
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_ORDER, contextId = "order", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/order")
public interface OrderFeign {

    /**
     * 评价订单
     *
     * @param orderId 订单ID
     * @return 是否评价成功
     */
    @GetMapping(value = "/review/{orderId}")
    ResponseEntity<Boolean> review(@PathVariable(value = "orderId") Long orderId);

    /**
     * 批量评价订单
     *
     * @param orderIds 订单ID列表
     * @return 是否评价成功
     */
    @PutMapping(value = "/review/batch")
    ResponseEntity<Boolean> reviewBatch(@RequestBody List<Long> orderIds);
}
