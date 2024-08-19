package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.UserRoleMapper;
import com.cafe.user.model.UserRole;
import com.cafe.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final UserRoleMapper userRoleMapper;
}
