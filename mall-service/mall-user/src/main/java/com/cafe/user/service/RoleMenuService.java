package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.bo.MenuRoleBO;
import com.cafe.user.model.entity.RoleMenu;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联关系业务接口
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 根据菜单ids获取 <菜单路径-角色名称> 对应关系列表
     *
     * @param menuIds
     * @return
     */
    List<MenuRoleBO> menuRoleBOList(List<Long> menuIds);

    /**
     * 初始化菜单路径-角色名称对应关系到 Redis 缓存中
     */
    void initMenuRoleCache();

    /**
     * 更新 Redis 缓存中的菜单路径-角色名称对应关系
     *
     * @param menuIds 对应关系发生变更的菜单ids
     */
    void refreshMenuRoleCache(List<Long> menuIds);
}
