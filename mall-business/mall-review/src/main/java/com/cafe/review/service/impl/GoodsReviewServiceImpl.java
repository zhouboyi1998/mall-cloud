package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.GoodsReviewMapper;
import com.cafe.review.model.entity.GoodsReview;
import com.cafe.review.service.GoodsReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 商品-评论关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class GoodsReviewServiceImpl extends ServiceImpl<GoodsReviewMapper, GoodsReview> implements GoodsReviewService {

    private final GoodsReviewMapper goodsReviewMapper;
}
