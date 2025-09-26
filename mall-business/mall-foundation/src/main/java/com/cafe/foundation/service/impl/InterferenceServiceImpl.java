package com.cafe.foundation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.constant.model.InterferenceConstant;
import com.cafe.foundation.mapper.InterferenceMapper;
import com.cafe.foundation.model.entity.Interference;
import com.cafe.foundation.service.InterferenceService;
import com.cafe.infrastructure.caffeine.annotation.CaffeineCache;
import com.cafe.infrastructure.caffeine.annotation.Expire;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @CaffeineCache(expire = @Expire(expireTime = 1, expireUnit = TimeUnit.DAYS))
    @Override
    public List<Interference> enableList() {
        return this.lambdaQuery().eq(Interference::getStatus, InterferenceConstant.Status.ENABLE).list();
    }
}
