package com.cafe.order.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.feign
 * @Author: zhouboyi
 * @Date: 2025/5/9 16:58
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_ORDER, contextId = "order-item", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/order-item")
public interface OrderItemFeign {

    /**
     * 评价订单明细
     *
     * @param orderItemId 订单明细ID
     * @return 是否评价成功
     */
    @GetMapping(value = "/review/{orderItemId}")
    ResponseEntity<Boolean> review(@PathVariable(value = "orderItemId") Long orderItemId);

    /**
     * 批量评价订单明细
     *
     * @param orderItemIds 订单明细ID列表
     * @return 是否评价成功
     */
    @PutMapping(value = "/review/batch")
    ResponseEntity<Boolean> reviewBatch(@RequestBody List<Long> orderItemIds);

    /**
     * 统计 SKU 销量
     *
     * @param skuId SKU ID
     * @return SKU 销量
     */
    @GetMapping(value = "/sale/{skuId}")
    ResponseEntity<Integer> sale(@PathVariable(value = "skuId") Long skuId);

    /**
     * 批量统计 SKU 销量
     *
     * @param skuIds SKU ID 列表
     * @return SKU 销量集合
     */
    @PostMapping(value = "/sale/batch")
    ResponseEntity<Map<Long, Integer>> saleBatch(@RequestBody List<Long> skuIds);
}
