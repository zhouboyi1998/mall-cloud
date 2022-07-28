package com.cafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.dao.RoleMapper;
import com.cafe.admin.model.Role;
import com.cafe.admin.service.RoleService;
import com.cafe.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色 (服务实现类)
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private RoleMapper roleMapper;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RoleServiceImpl(
        RoleMapper roleMapper,
        RedisTemplate<String, Object> redisTemplate
    ) {
        this.roleMapper = roleMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<String> listRoleName() {
        return roleMapper.listRoleName();
    }

    @Override
    public List<String> listRoleNameByAdminId(Long adminId) {
        return roleMapper.listRoleNameByAdminId(adminId);
    }

    @PostConstruct
    @Override
    public void initRoleNameMap() {
        // 获取所有角色名称
        List<String> roleNameList = roleMapper.listRoleName();
        // 清空 Redis 中原来的 List
        redisTemplate.opsForList().trim(RedisConstant.ROLE_NAME_LIST, 0, 0);
        redisTemplate.opsForList().leftPop(RedisConstant.ROLE_NAME_LIST);
        // 初始化之前先删除对应的 key, 清空旧的数据
        redisTemplate.delete(RedisConstant.ROLE_NAME_LIST);
        // 将角色名称保存到 Redis 中
        for (String roleName : roleNameList) {
            redisTemplate.opsForList().rightPush(RedisConstant.ROLE_NAME_LIST, roleName);
        }
    }

    @Override
    public void saveRoleNameMap(String roleName) {
        redisTemplate.opsForList().rightPush(RedisConstant.ROLE_NAME_LIST, roleName);
    }

    @Override
    public void removeRoleNameMap(String roleName) {
        redisTemplate.opsForList().remove(RedisConstant.ROLE_NAME_LIST, 1, roleName);
    }
}
