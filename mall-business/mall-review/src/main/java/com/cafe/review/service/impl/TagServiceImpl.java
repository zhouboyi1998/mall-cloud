package com.cafe.review.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.review.mapper.TagMapper;
import com.cafe.review.model.entity.Tag;
import com.cafe.review.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.service.impl
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 标签业务实现类
 */
@RequiredArgsConstructor
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final TagMapper tagMapper;
}
