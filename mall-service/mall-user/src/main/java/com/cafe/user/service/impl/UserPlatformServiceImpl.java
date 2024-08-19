package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.UserPlatformMapper;
import com.cafe.user.model.UserPlatform;
import com.cafe.user.service.UserPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 用户-平台关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class UserPlatformServiceImpl extends ServiceImpl<UserPlatformMapper, UserPlatform> implements UserPlatformService {

    private final UserPlatformMapper userPlatformMapper;
}
