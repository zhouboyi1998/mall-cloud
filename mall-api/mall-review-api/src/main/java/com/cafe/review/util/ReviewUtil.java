package com.cafe.review.util;

import com.cafe.common.constant.model.ReviewConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.review.model.entity.Review;
import com.cafe.starter.boot.model.exception.BusinessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.util
 * @Author: zhouboyi
 * @Date: 2025/5/10 1:47
 * @Description: 评论工具类
 */
public class ReviewUtil {

    /**
     * 检查评论类型
     *
     * @param review 评论
     * @param tagIds 标签ID列表
     */
    public static Integer checkReviewType(Review review, List<Long> tagIds) {
        if (StringUtils.hasText(review.getComment())) {
            return ReviewConstant.ReviewType.COMMENT;
        } else if (!CollectionUtils.isEmpty(tagIds)) {
            return ReviewConstant.ReviewType.TAG;
        } else if (Objects.nonNull(review.getRating())) {
            return ReviewConstant.ReviewType.RATING;
        } else {
            throw new BusinessException(HttpStatusEnum.UNKNOWN_REVIEW_TYPE);
        }
    }
}
