package com.cafe.review.facade;

import com.cafe.review.model.query.OrderReviewSaveQuery;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.facade
 * @Author: zhouboyi
 * @Date: 2025/5/2 19:43
 * @Description: 订单-评论关联关系防腐层接口
 */
public interface OrderReviewFacade {

    /**
     * 保存订单评论
     *
     * @param query 保存订单评论请求
     * @return 是否保存成功
     */
    Boolean review(OrderReviewSaveQuery query);

    /**
     * 批量保存订单评论
     *
     * @param queryList 保存订单评论请求列表
     * @return 是否保存成功
     */
    Boolean reviewBatch(List<OrderReviewSaveQuery> queryList);
}
