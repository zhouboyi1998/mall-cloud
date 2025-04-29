package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.ReviewTagMapper;
import com.cafe.review.model.entity.ReviewTag;
import com.cafe.review.service.ReviewTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 评论-标签关联关系业务实现类
 */
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class ReviewTagServiceImpl extends ServiceImpl<ReviewTagMapper, ReviewTag> implements ReviewTagService {

    private final ReviewTagService reviewTagService;

    private final ReviewTagMapper reviewTagMapper;

    @Transactional(
        propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        timeout = 5,
        isolation = Isolation.READ_COMMITTED
    )
    @Override
    public void removeAndSave(Long reviewId, List<Long> tagIds) {
        // 删除旧的关联关系
        reviewTagMapper.delete(Wrappers.lambdaQuery(ReviewTag.class).eq(ReviewTag::getReviewId, reviewId));
        // 保存新的关联关系
        if (CollectionUtils.isEmpty(tagIds)) {
            return;
        }
        List<ReviewTag> reviewTagList = tagIds.stream()
            .map(tagId -> new ReviewTag().setReviewId(reviewId).setTagId(tagId))
            .collect(Collectors.toList());
        reviewTagService.saveBatch(reviewTagList);
    }
}
