package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.model.Role;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色业务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询角色名称列表
     *
     * @param adminId 管理员id
     * @return
     */
    List<String> listRoleName(Long adminId);

    /**
     * 初始化角色名称列表到 Redis 中
     */
    void initRoleNameList();

    /**
     * 新增指定的角色名称到 Redis 的角色名称列表中
     *
     * @param roleName
     */
    void saveRoleNameList(String roleName);

    /**
     * 从 Redis 的角色名称列表中删除指定的角色名称
     *
     * @param roleName
     */
    void removeRoleNameList(String roleName);
}
