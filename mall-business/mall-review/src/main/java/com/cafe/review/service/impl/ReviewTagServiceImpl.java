package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.ReviewTagMapper;
import com.cafe.review.model.entity.ReviewTag;
import com.cafe.review.service.ReviewTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 评论-标签关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class ReviewTagServiceImpl extends ServiceImpl<ReviewTagMapper, ReviewTag> implements ReviewTagService {

    private final ReviewTagMapper reviewTagMapper;
}
