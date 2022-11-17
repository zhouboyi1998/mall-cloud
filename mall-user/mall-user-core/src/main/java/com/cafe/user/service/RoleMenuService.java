package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.RoleMenu;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联业务接口
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 初始化菜单路径-角色名称对应关系到 Redis 中
     */
    void initMenuRoleBO();

    /**
     * 更新 Redis 中的菜单路径-角色名称对应关系
     *
     * @param menuIds 对应关系发生变更的菜单ids
     */
    void refreshMenuRoleBO(List<Long> menuIds);
}
