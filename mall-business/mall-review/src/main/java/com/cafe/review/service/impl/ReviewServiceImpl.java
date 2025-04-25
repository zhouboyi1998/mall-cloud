package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.ReviewMapper;
import com.cafe.review.model.entity.Review;
import com.cafe.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 评论业务实现类
 */
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    private final ReviewMapper reviewMapper;
}
