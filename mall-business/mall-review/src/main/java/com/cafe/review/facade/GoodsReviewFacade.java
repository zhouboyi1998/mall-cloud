package com.cafe.review.facade;

import com.cafe.review.model.query.GoodsReviewSaveQuery;

import java.util.List;

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
}
