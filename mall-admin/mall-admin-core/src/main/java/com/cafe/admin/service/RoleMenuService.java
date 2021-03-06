package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.bo.MenuRoleRelationBO;
import com.cafe.admin.model.RoleMenu;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (服务类)
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 获取菜单路径和角色名称对应关系
     *
     * @param menuIds
     * @return
     */
    List<MenuRoleRelationBO> listMenuRoleRelationBO(List<Long> menuIds);

    /**
     * 初始化 菜单路径-角色名称 对应关系到 Redis 中
     */
    void initMenuRoleRelationBO();

    /**
     * 更新 Redis 中的 菜单路径-角色名称 对应关系
     *
     * @param menuIds 对应关系发生变更的菜单id列表
     */
    void refreshMenuRoleRelationBO(List<Long> menuIds);
}
