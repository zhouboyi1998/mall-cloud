package com.cafe.user.facade;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.facade
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:35
 * @Description: 角色-菜单关联关系防腐层接口
 */
public interface RoleMenuFacade {

    /**
     * 根据菜单路径获取角色名称列表
     *
     * @param menuPath 菜单路径
     * @return 角色名称列表
     */
    List<String> roleNameList(String menuPath);
}
