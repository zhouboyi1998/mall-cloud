package com.cafe.security.manage.service.impl;

import com.cafe.admin.bo.MenuPathAndRoleNameBO;
import com.cafe.admin.feign.RoleMenuRelationFeign;
import com.cafe.common.constant.RedisConstant;
import com.cafe.security.manage.service.ResourceService;
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
 * @Package: com.cafe.security.manage.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:21
 * @Description: 资源业务实现类
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private RedisTemplate<String, Object> redisTemplate;

    private RoleMenuRelationFeign roleMenuRelationFeign;

    @Autowired
    public ResourceServiceImpl(
        RedisTemplate<String, Object> redisTemplate,
        RoleMenuRelationFeign roleMenuRelationFeign
    ) {
        this.redisTemplate = redisTemplate;
        this.roleMenuRelationFeign = roleMenuRelationFeign;
    }

    @PostConstruct
    @Override
    public void initRelationData() {
        // 获取所有菜单路径和角色名称对应关系
        List<MenuPathAndRoleNameBO> boList = roleMenuRelationFeign.listMenuPathAndRoleNameBO().getBody();
        // 将对应关系组装成 Map 格式
        Map<String, ArrayList<String>> relationMap = new TreeMap<String, ArrayList<String>>();
        for (MenuPathAndRoleNameBO bo : boList) {
            relationMap.put(bo.getMenuPath(), bo.getRoleNameList());
        }
        // 将对应关系放入 Redis 中, 提供给网关查询
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLE_MAP, relationMap);
    }

    @Override
    public void updateRelationData(List<Long> menuIds) {
        // 按菜单ids获取菜单路径和角色名称对应关系列表
        List<MenuPathAndRoleNameBO> boList = roleMenuRelationFeign.listMenuPathAndRoleNameBO(menuIds).getBody();
        // 更新 Redis 中的对应关系
        for (MenuPathAndRoleNameBO bo : boList) {
            redisTemplate.opsForHash().put(RedisConstant.RESOURCE_ROLE_MAP, bo.getMenuPath(), bo.getRoleNameList());
        }
    }
}
