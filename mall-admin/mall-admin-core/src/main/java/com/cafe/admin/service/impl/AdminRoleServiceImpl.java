package com.cafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.dao.AdminRoleMapper;
import com.cafe.admin.model.AdminRole;
import com.cafe.admin.service.AdminRoleService;
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
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    private AdminRoleMapper adminRoleMapper;

    @Autowired
    public AdminRoleServiceImpl(AdminRoleMapper adminRoleMapper) {
        this.adminRoleMapper = adminRoleMapper;
    }
}
