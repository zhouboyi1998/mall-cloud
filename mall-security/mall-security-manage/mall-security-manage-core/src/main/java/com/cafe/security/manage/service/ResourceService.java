package com.cafe.security.manage.service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.manage.service
 * @Author: zhouboyi
 * @Date: 2022/5/18 16:28
 * @Description: 资源业务接口
 */
public interface ResourceService {

    /**
     * 初始化菜单路径和角色名称对应关系到 Redis 中
     */
    void initMenuRoleRelation();

    /**
     * 更新菜单路径和角色名称对应关系
     *
     * @param menuIds 对应关系发生变更的菜单id列表
     */
    void updateMenuRoleRelation(List<Long> menuIds);

    /**
     * 初始化角色名称到 Redis 中
     */
    void initRoleNameMap();
}
