package com.cafe.user.facade.impl;

import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.user.facade.RoleMenuFacade;
import com.cafe.user.model.Menu;
import com.cafe.user.model.Role;
import com.cafe.user.model.RoleMenu;
import com.cafe.user.service.MenuService;
import com.cafe.user.service.RoleMenuService;
import com.cafe.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.facade.impl
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:35
 * @Description: 角色-菜单关联关系防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class RoleMenuFacadeImpl implements RoleMenuFacade {

    private final RoleMenuService roleMenuService;

    private final RoleService roleService;

    private final MenuService menuService;

    @Override
    public List<String> roleNameList(String menuPath) {
        return Optional.ofNullable(menuPath)
            // 根据菜单路径查询菜单
            .map(path -> menuService.lambdaQuery().eq(Menu::getMenuPath, path).one())
            // 根据菜单id查询角色-菜单关联关系
            .map(menu -> roleMenuService.lambdaQuery().eq(RoleMenu::getMenuId, menu.getId()).list())
            // 获取角色id列表
            .map(roleMenuList -> roleMenuList.stream().map(RoleMenu::getRoleId).collect(Collectors.toList()))
            // 校验角色id列表是否为空
            .map(roleIds -> CollectionUtils.isEmpty(roleIds) ? null : roleIds)
            // 根据角色id列表查询角色列表
            .map(roleService::listByIds)
            // 获取角色名称列表
            .map(roleList -> roleList.stream().map(Role::getRoleName).collect(Collectors.toList()))
            // 添加权限前缀
            .map(roleNameList -> roleNameList.stream().map(roleName -> AuthorizationConstant.AUTHORITY_PREFIX + roleName).collect(Collectors.toList()))
            // 默认值 (empty list)
            .orElse(new ArrayList<>());
    }
}
