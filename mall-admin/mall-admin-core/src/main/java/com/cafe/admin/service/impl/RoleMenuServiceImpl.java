package com.cafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.bo.MenuRoleRelationBO;
import com.cafe.admin.dao.RoleMenuMapper;
import com.cafe.admin.model.RoleMenu;
import com.cafe.admin.service.RoleMenuService;
import com.cafe.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (服务实现类)
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

    @Override
    public List<MenuRoleRelationBO> listMenuRoleRelationBO(List<Long> menuIds) {
        return roleMenuMapper.listMenuRoleRelationBO(menuIds);
    }

    @PostConstruct
    @Override
    public void initMenuRoleRelationBO() {
        List<Long> menuIds = new ArrayList<Long>();
        // 获取所有 菜单路径-角色名称 的对应关系
        List<MenuRoleRelationBO> boList = roleMenuMapper.listMenuRoleRelationBO(menuIds);
        // 将对应关系组装成 Map 格式
        Map<String, ArrayList<String>> relationMap = new TreeMap<String, ArrayList<String>>();
        for (MenuRoleRelationBO bo : boList) {
            relationMap.put(bo.getMenuPath(), bo.getRoleNameList());
        }
        // 初始化之前先删除对应的 key, 清空旧的数据
        redisTemplate.delete(RedisConstant.MENU_ROLE_MAP);
        // 将对应关系保存到 Redis 中
        redisTemplate.opsForHash().putAll(RedisConstant.MENU_ROLE_MAP, relationMap);
    }

    @Override
    public void refreshMenuRoleRelationBO(List<Long> menuIds) {
        // 按菜单ids获取 菜单路径-角色名称 对应关系列表
        List<MenuRoleRelationBO> boList = roleMenuMapper.listMenuRoleRelationBO(menuIds);
        // 更新 Redis 中的对应关系
        for (MenuRoleRelationBO bo : boList) {
            redisTemplate.opsForHash().put(RedisConstant.MENU_ROLE_MAP, bo.getMenuPath(), bo.getRoleNameList());
        }
    }
}
