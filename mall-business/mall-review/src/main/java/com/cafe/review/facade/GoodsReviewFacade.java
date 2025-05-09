package com.cafe.review.facade;

import com.cafe.review.model.query.GoodsReviewSaveQuery;

import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.facade
 * @Author: zhouboyi
 * @Date: 2025/4/30 16:40
 * @Description: 商品-评论关联关系防腐层接口
 */
public interface GoodsReviewFacade {

    /**
     * 保存商品评论
     *
     * @param query 保存商品评论请求
     * @return 是否保存成功
     */
    Boolean review(GoodsReviewSaveQuery query);

    /**
     * 批量保存商品评论
     *
     * @param queryList 保存商品评论请求列表
     * @return 是否保存成功
     */
    Boolean reviewBatch(List<GoodsReviewSaveQuery> queryList);

    /**
     * 统计 SKU 评论数量
     *
     * @param skuId SKU ID
     * @return SKU 评论数量
     */
    Map<String, Integer> statistic(Long skuId);

    /**
     * 批量统计 SKU 评论数量
     *
     * @param skuIds SKU ID 列表
     * @return SKU 评论数量集合
     */
    Map<Long, Map<String, Integer>> statisticBatch(List<Long> skuIds);
}
