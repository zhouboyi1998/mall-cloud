package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.model.Role;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色 (服务类)
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询所有角色名称列表
     *
     * @return
     */
    List<String> listRoleName();

    /**
     * 根据管理员id查询角色名称列表
     *
     * @param adminId 管理员id
     * @return
     */
    List<String> listRoleNameByAdminId(Long adminId);

    /**
     * 初始化角色名称列表到 Redis 中
     */
    void initRoleNameMap();
}
