package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.foundation.mapper.SensitiveMapper;
import com.cafe.foundation.model.entity.Sensitive;
import com.cafe.foundation.service.SensitiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service.impl
 * @Author: zhouboyi
 * @Date: 2025-07-27
 * @Description: 敏感词业务实现类
 */
@RequiredArgsConstructor
@Service
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, Sensitive> implements SensitiveService {

    private final SensitiveMapper sensitiveMapper;
}
