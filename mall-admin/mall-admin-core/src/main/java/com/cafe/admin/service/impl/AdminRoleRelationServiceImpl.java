package com.cafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.dao.AdminRoleRelationMapper;
import com.cafe.admin.model.AdminRoleRelation;
import com.cafe.admin.service.AdminRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联 (服务实现类)
 */
@Service
public class AdminRoleRelationServiceImpl extends ServiceImpl<AdminRoleRelationMapper, AdminRoleRelation> implements AdminRoleRelationService {

    private AdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    public AdminRoleRelationServiceImpl(AdminRoleRelationMapper adminRoleRelationMapper) {
        this.adminRoleRelationMapper = adminRoleRelationMapper;
    }
}
