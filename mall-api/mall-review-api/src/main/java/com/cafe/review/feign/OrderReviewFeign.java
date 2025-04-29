package com.cafe.review.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.review.model.query.OrderReviewSaveQuery;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.feign
 * @Author: zhouboyi
 * @Date: 2025/4/29 17:35
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_REVIEW, contextId = "order-review", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/order-review")
public interface OrderReviewFeign {

    /**
     * 保存订单评论
     *
     * @param query 保存订单评论请求
     * @return 是否保存成功
     */
    @PostMapping(value = "/review")
    ResponseEntity<Boolean> review(@RequestBody OrderReviewSaveQuery query);

    /**
     * 批量保存订单评论
     *
     * @param queryList 保存订单评论请求列表
     * @return 是否保存成功
     */
    @PostMapping(value = "/review/batch")
    ResponseEntity<Boolean> reviewBatch(@RequestBody List<OrderReviewSaveQuery> queryList);
}
