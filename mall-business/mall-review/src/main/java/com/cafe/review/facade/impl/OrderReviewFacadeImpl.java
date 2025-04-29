package com.cafe.review.facade.impl;

import com.cafe.common.constant.model.ReviewConstant;
import com.cafe.review.facade.OrderReviewFacade;
import com.cafe.review.model.converter.OrderReviewConverter;
import com.cafe.review.model.entity.OrderReview;
import com.cafe.review.model.entity.Review;
import com.cafe.review.model.query.OrderReviewSaveQuery;
import com.cafe.review.service.OrderReviewService;
import com.cafe.review.service.ReviewService;
import com.cafe.review.service.ReviewTagService;
import com.cafe.review.util.ReviewUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.facade.impl
 * @Author: zhouboyi
 * @Date: 2025/5/2 19:43
 * @Description: 订单-评论关联关系防腐层实现类
 */
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class OrderReviewFacadeImpl implements OrderReviewFacade {

    private final OrderReviewFacade orderReviewFacade;

    private final OrderReviewService orderReviewService;

    private final ReviewService reviewService;

    private final ReviewTagService reviewTagService;

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 10,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public Boolean review(OrderReviewSaveQuery query) {
        // 保存评论
        List<Long> tagIds = query.getTagIds();
        Review review = query.getReview()
            .setTargetType(ReviewConstant.TargetType.ORDER);
        review.setReviewType(ReviewUtil.checkReviewType(review, tagIds));
        reviewService.saveOrUpdate(review);
        // 保存评论-标签关联关系
        Long reviewId = review.getId();
        reviewTagService.removeAndSave(reviewId, tagIds);
        // 保存订单-评论关联关系
        OrderReview orderReview = OrderReviewConverter.INSTANCE.toEntity(query).setReviewId(reviewId);
        return orderReviewService.saveOrUpdate(orderReview);
    }

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 20,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public Boolean reviewBatch(List<OrderReviewSaveQuery> queryList) {
        // 循环保存订单评论
        return queryList.stream().allMatch(orderReviewFacade::review);
    }
}
