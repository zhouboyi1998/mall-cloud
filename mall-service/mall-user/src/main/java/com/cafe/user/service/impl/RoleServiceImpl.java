package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.RoleMapper;
import com.cafe.user.model.Role;
import com.cafe.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色业务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<String> listRoleName(Long userId) {
        return roleMapper.listRoleName(userId);
    }
}
