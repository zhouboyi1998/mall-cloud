package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.bo.MenuRoleBO;
import com.cafe.user.dao.RoleMenuMapper;
import com.cafe.user.model.RoleMenu;
import com.cafe.user.service.RoleMenuService;
import com.cafe.common.constant.AuthenticationConstant;
import com.cafe.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联业务实现类
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    private RoleMenuMapper roleMenuMapper;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RoleMenuServiceImpl(
        RoleMenuMapper roleMenuMapper,
        RedisTemplate<String, Object> redisTemplate
    ) {
        this.roleMenuMapper = roleMenuMapper;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    @Override
    public void initMenuRoleBO() {
        List<Long> menuIds = new ArrayList<Long>();
        // 获取所有 <菜单路径-角色名称> 的对应关系
        List<MenuRoleBO> boList = roleMenuMapper.listMenuRoleBO(menuIds);
        // 将对应关系组装成 Map 格式
        Map<String, List<String>> relationMap = new TreeMap<String, List<String>>();
        for (MenuRoleBO bo : boList) {
            // 添加权限前缀
            List<String> roleNameList = bo.getRoleNameList().stream()
                .map(i -> i = AuthenticationConstant.AUTHORITY_PREFIX + i)
                .collect(Collectors.toList());
            relationMap.put(bo.getMenuPath(), roleNameList);
        }
        // 初始化之前先删除对应的 key, 清空旧的数据
        redisTemplate.delete(RedisConstant.MENU_ROLE_MAP);
        // 将对应关系保存到 Redis 中
        redisTemplate.opsForHash().putAll(RedisConstant.MENU_ROLE_MAP, relationMap);
    }

    @Override
    public void refreshMenuRoleBO(List<Long> menuIds) {
        // 按菜单ids获取 <菜单路径-角色名称> 对应关系列表
        List<MenuRoleBO> boList = roleMenuMapper.listMenuRoleBO(menuIds);
        // 更新 Redis 中的对应关系
        for (MenuRoleBO bo : boList) {
            // 添加权限前缀
            List<String> roleList = bo.getRoleNameList().stream()
                .map(i -> i = AuthenticationConstant.AUTHORITY_PREFIX + i)
                .collect(Collectors.toList());
            // 将对应关系保存到 Redis 中
            redisTemplate.opsForHash().put(RedisConstant.MENU_ROLE_MAP, bo.getMenuPath(), bo.getRoleNameList());
        }
    }
}
