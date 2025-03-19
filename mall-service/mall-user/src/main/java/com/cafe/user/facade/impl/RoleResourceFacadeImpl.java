package com.cafe.user.facade.impl;

import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.constant.security.AuthorizationConstant;
import com.cafe.user.facade.RoleResourceFacade;
import com.cafe.user.model.bo.ResourceRoleBO;
import com.cafe.user.service.RoleResourceService;
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
 * @Package: com.cafe.user.facade.impl
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:35
 * @Description: 角色-资源关联关系防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class RoleResourceFacadeImpl implements RoleResourceFacade {

    private final RoleResourceService roleResourceService;

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    @Override
    public void initResourceRoleCache() {
        // 获取 [资源内容-角色名称] 对应关系列表
        List<ResourceRoleBO> roleNameListBOList = roleResourceService.resourceRoleBOList(Collections.emptyList());
        // 将对应关系组装成 Map 格式
        Map<String, List<String>> relationMap = new TreeMap<>();
        for (ResourceRoleBO resourceRoleBO : roleNameListBOList) {
            // 添加权限前缀
            List<String> roleNameList = resourceRoleBO.getRoleNameList().stream()
                .map(roleName -> AuthorizationConstant.AUTHORITY_PREFIX + roleName)
                .collect(Collectors.toList());
            relationMap.put(resourceRoleBO.getResourceContent(), roleNameList);
        }
        // 初始化之前先删除对应的 key, 清空旧的数据
        redisTemplate.delete(RedisConstant.RESOURCE_ROLE_MAP);
        // 将对应关系保存到 Redis 中
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLE_MAP, relationMap);
    }

    @Override
    public void refreshResourceRoleCache(List<Long> resourceIds) {
        // 根据资源ID列表获取 [资源内容-角色名称] 对应关系列表
        List<ResourceRoleBO> roleNameListBOList = roleResourceService.resourceRoleBOList(resourceIds);
        // 更新 Redis 中的对应关系
        for (ResourceRoleBO resourceRoleBO : roleNameListBOList) {
            // 添加权限前缀
            List<String> roleNameList = resourceRoleBO.getRoleNameList().stream()
                .map(roleName -> AuthorizationConstant.AUTHORITY_PREFIX + roleName)
                .collect(Collectors.toList());
            // 将对应关系保存到 Redis 中
            redisTemplate.opsForHash().put(RedisConstant.RESOURCE_ROLE_MAP, resourceRoleBO.getResourceContent(), roleNameList);
        }
    }
}
