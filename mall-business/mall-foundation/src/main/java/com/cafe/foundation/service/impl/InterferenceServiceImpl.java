package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.foundation.mapper.InterferenceMapper;
import com.cafe.foundation.model.entity.Interference;
import com.cafe.foundation.service.InterferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.service.impl
 * @Author: zhouboyi
 * @Date: 2025-07-28
 * @Description: 干扰符业务实现类
 */
@RequiredArgsConstructor
@Service
public class InterferenceServiceImpl extends ServiceImpl<InterferenceMapper, Interference> implements InterferenceService {

    private final InterferenceMapper interferenceMapper;
}
