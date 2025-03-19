package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.RoleResourceMapper;
import com.cafe.user.model.bo.ResourceRoleBO;
import com.cafe.user.model.entity.RoleResource;
import com.cafe.user.service.RoleResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-资源关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    private final RoleResourceMapper roleResourceMapper;

    @Override
    public List<ResourceRoleBO> resourceRoleBOList(List<Long> resourceIds) {
        return roleResourceMapper.resourceRoleBOList(resourceIds);
    }
}
