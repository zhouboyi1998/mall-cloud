package com.cafe.review.feign;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.review.model.query.GoodsReviewSaveQuery;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.feign
 * @Author: zhouboyi
 * @Date: 2025/4/29 17:27
 * @Description:
 */
@FeignClient(value = ServiceConstant.MALL_REVIEW, contextId = "goods-review", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/goods-review")
public interface GoodsReviewFeign {

    /**
     * 保存商品评论
     *
     * @param query 保存商品评论请求
     * @return 是否保存成功
     */
    @PostMapping(value = "/review")
    ResponseEntity<Boolean> review(@RequestBody GoodsReviewSaveQuery query);

    /**
     * 批量保存商品评论
     *
     * @param queryList 保存商品评论请求列表
     * @return 是否保存成功
     */
    @PostMapping(value = "/review/batch")
    ResponseEntity<Boolean> reviewBatch(@RequestBody List<GoodsReviewSaveQuery> queryList);

    /**
     * 统计 SKU 评论数量
     *
     * @param skuId SKU ID
     * @return SKU 评论数量
     */
    @GetMapping(value = "/statistic/{skuId}")
    ResponseEntity<Map<String, Integer>> statistic(@PathVariable(value = "skuId") Long skuId);

    /**
     * 批量统计 SKU 评论数量
     *
     * @param skuIds SKU ID 列表
     * @return SKU 评论数量集合
     */
    @PostMapping(value = "/statistic/batch")
    ResponseEntity<Map<Long, Map<String, Integer>>> statisticBatch(@RequestBody List<Long> skuIds);
}
