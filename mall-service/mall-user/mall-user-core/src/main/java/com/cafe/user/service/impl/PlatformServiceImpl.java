package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.PlatformMapper;
import com.cafe.user.model.Platform;
import com.cafe.user.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 平台业务实现类
 */
@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {

    private final PlatformMapper platformMapper;

    @Autowired
    public PlatformServiceImpl(PlatformMapper platformMapper) {
        this.platformMapper = platformMapper;
    }
}
