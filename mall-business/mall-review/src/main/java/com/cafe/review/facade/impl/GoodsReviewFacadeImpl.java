package com.cafe.review.facade.impl;

import com.cafe.common.constant.app.FieldConstant;
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
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private static final Map<String, Integer> ZERO_STATISTIC = new HashMap<String, Integer>(4) {{
        put(FieldConstant.TOTAL_REVIEW, 0);
        put(FieldConstant.GOOD_REVIEW, 0);
        put(FieldConstant.MEDIUM_REVIEW, 0);
        put(FieldConstant.BAD_REVIEW, 0);
    }};

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

    @Override
    public Map<String, Integer> statistic(Long skuId) {
        Map<String, Integer> statistic = new HashMap<>(4);
        // 查询 SKU 关联的所有评论 ID
        List<Long> reviewIds = goodsReviewService.lambdaQuery()
            .eq(GoodsReview::getSkuId, skuId)
            .select(GoodsReview::getReviewId)
            .list()
            .stream()
            .map(GoodsReview::getReviewId)
            .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(reviewIds)) {
            return ZERO_STATISTIC;
        }

        // 查询 SKU 关联的所有评分
        List<Integer> ratingList = reviewService.lambdaQuery()
            .in(Review::getId, reviewIds)
            .select(Review::getRating)
            .list()
            .stream()
            .map(Review::getRating)
            .collect(Collectors.toList());
        // 统计总评论数
        statistic.put(FieldConstant.TOTAL_REVIEW, ratingList.size());
        // 统计好评数
        statistic.put(FieldConstant.GOOD_REVIEW, (int) ratingList.stream().filter(rating -> rating >= 4).count());
        // 统计中评数
        statistic.put(FieldConstant.MEDIUM_REVIEW, (int) ratingList.stream().filter(rating -> rating == 3).count());
        // 统计差评数
        statistic.put(FieldConstant.BAD_REVIEW, (int) ratingList.stream().filter(rating -> rating <= 2).count());
        return statistic;
    }

    @Override
    public Map<Long, Map<String, Integer>> statisticBatch(List<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) {
            return Collections.emptyMap();
        }
        return skuIds.parallelStream().collect(Collectors.toMap(Function.identity(), goodsReviewFacade::statistic));
    }
}
