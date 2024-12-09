package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.user.mapper.RoleMenuMapper;
import com.cafe.user.model.bo.MenuRoleBO;
import com.cafe.user.model.entity.RoleMenu;
import com.cafe.user.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<MenuRoleBO> menuRoleBOList(List<Long> menuIds) {
        return roleMenuMapper.menuRoleBOList(menuIds);
    }

    @PostConstruct
    @Override
    public void initMenuRoleCache() {
        // 获取所有 <菜单路径-角色名称> 的对应关系
        List<MenuRoleBO> menuRoleBOList = roleMenuMapper.menuRoleBOList(Collections.emptyList());
        // 将对应关系组装成 Map 格式
        Map<String, List<String>> relationMap = new TreeMap<>();
        for (MenuRoleBO menuRoleBO : menuRoleBOList) {
            // 添加权限前缀
            List<String> roleNameList = menuRoleBO.getRoleNameList().stream()
                .map(roleName -> AuthorizationConstant.AUTHORITY_PREFIX + roleName)
                .collect(Collectors.toList());
            relationMap.put(menuRoleBO.getMenuPath(), roleNameList);
        }
        // 初始化之前先删除对应的 key, 清空旧的数据
        redisTemplate.delete(RedisConstant.MENU_ROLE_MAP);
        // 将对应关系保存到 Redis 中
        redisTemplate.opsForHash().putAll(RedisConstant.MENU_ROLE_MAP, relationMap);
    }

    @Override
    public void refreshMenuRoleCache(List<Long> menuIds) {
        // 根据菜单ids获取 <菜单路径-角色名称> 对应关系列表
        List<MenuRoleBO> menuRoleBOList = roleMenuMapper.menuRoleBOList(menuIds);
        // 更新 Redis 中的对应关系
        for (MenuRoleBO menuRoleBO : menuRoleBOList) {
            // 添加权限前缀
            List<String> roleNameList = menuRoleBO.getRoleNameList().stream()
                .map(roleName -> AuthorizationConstant.AUTHORITY_PREFIX + roleName)
                .collect(Collectors.toList());
            // 将对应关系保存到 Redis 中
            redisTemplate.opsForHash().put(RedisConstant.MENU_ROLE_MAP, menuRoleBO.getMenuPath(), roleNameList);
        }
    }
}
