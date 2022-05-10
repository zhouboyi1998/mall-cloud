package com.cafe.security.management.service.impl;

import com.cafe.admin.bo.MenuPathAndRoleNameBO;
import com.cafe.admin.feign.RoleMenuRelationFeign;
import com.cafe.security.management.constant.RedisEnum;
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
 * @Package: com.cafe.security.management.service.impl
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:21
 * @Description: 资源业务类
 */
@Service
public class ResourceServiceImpl {

    private Map<String, ArrayList<String>> relationMap;

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

    /**
     * 在依赖注入结束, 类初始化后立即执行此方法
     */
    @PostConstruct
    public void initRelationData() {
        // 获取菜单路径和角色名称对应关系列表
        List<MenuPathAndRoleNameBO> boList = roleMenuRelationFeign.listMenuPathAndRoleNameBO().getBody();

        // 将对应关系组装成 Map 格式
        relationMap = new TreeMap<String, ArrayList<String>>();
        for (MenuPathAndRoleNameBO bo : boList) {
            relationMap.put(bo.getPath(), bo.getRoleNameList());
        }

        // 将对应关系放入 Redis 中, 提供给网关查询
        redisTemplate.opsForHash().putAll(RedisEnum.RESOURCE_ROLE_MAP.getValue(), relationMap);
    }
}
