package com.cafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.dao.RoleMenuRelationMapper;
import com.cafe.admin.model.RoleMenuRelation;
import com.cafe.admin.service.RoleMenuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (服务实现类)
 */
@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation> implements RoleMenuRelationService {

    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    public RoleMenuRelationServiceImpl(RoleMenuRelationMapper roleMenuRelationMapper) {
        this.roleMenuRelationMapper = roleMenuRelationMapper;
    }
}
