package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.bo.MenuPathAndRoleNameBO;
import com.cafe.admin.model.RoleMenuRelation;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (服务类)
 */
public interface RoleMenuRelationService extends IService<RoleMenuRelation> {

    /**
     * 获取菜单路径和角色名称对应关系列表
     *
     * @return
     */
    List<MenuPathAndRoleNameBO> listMenuPathAndRoleNameBO();
}