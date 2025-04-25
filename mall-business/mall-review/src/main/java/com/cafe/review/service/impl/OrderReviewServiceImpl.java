package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.OrderReviewMapper;
import com.cafe.review.model.entity.OrderReview;
import com.cafe.review.service.OrderReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 订单-评论关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class OrderReviewServiceImpl extends ServiceImpl<OrderReviewMapper, OrderReview> implements OrderReviewService {

    private final OrderReviewMapper orderReviewMapper;
}
