package com.cafe.review.facade.impl;

import com.cafe.common.constant.model.ReviewConstant;
import com.cafe.review.facade.GoodsReviewFacade;
import com.cafe.review.model.converter.GoodsReviewConverter;
import com.cafe.review.model.entity.GoodsReview;
import com.cafe.review.model.entity.Review;
import com.cafe.review.model.query.GoodsReviewSaveQuery;
import com.cafe.review.service.GoodsReviewService;
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
 * @Date: 2025/4/30 16:41
 * @Description: 商品-评论关联关系防腐层实现类
 */
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class GoodsReviewFacadeImpl implements GoodsReviewFacade {

    private final GoodsReviewFacade goodsReviewFacade;

    private final GoodsReviewService goodsReviewService;

    private final ReviewService reviewService;

    private final ReviewTagService reviewTagService;

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 10,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public Boolean review(GoodsReviewSaveQuery query) {
        // 保存评论
        List<Long> tagIds = query.getTagIds();
        Review review = query.getReview()
            .setTargetType(ReviewConstant.TargetType.GOODS);
        review.setReviewType(ReviewUtil.checkReviewType(review, tagIds));
        reviewService.saveOrUpdate(review);
        // 保存评论-标签关联关系
        Long reviewId = review.getId();
        reviewTagService.removeAndSave(reviewId, tagIds);
        // 保存商品-评论关联关系
        GoodsReview goodsReview = GoodsReviewConverter.INSTANCE.toEntity(query).setReviewId(reviewId);
        return goodsReviewService.saveOrUpdate(goodsReview);
    }

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 20,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public Boolean reviewBatch(List<GoodsReviewSaveQuery> queryList) {
        // 循环保存商品评论
        return queryList.stream().allMatch(goodsReviewFacade::review);
    }
}
