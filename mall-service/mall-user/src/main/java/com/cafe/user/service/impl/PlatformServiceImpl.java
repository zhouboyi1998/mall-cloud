package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.PlatformMapper;
import com.cafe.user.model.entity.Platform;
import com.cafe.user.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 平台业务实现类
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {

    private final PlatformMapper platformMapper;
}
