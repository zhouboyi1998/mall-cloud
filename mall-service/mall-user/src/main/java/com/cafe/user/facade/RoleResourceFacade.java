package com.cafe.user.facade;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.facade
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:35
 * @Description: 角色-资源关联关系防腐层接口
 */
public interface RoleResourceFacade {

    /**
     * 初始化 [资源内容-角色名称] 对应关系列表到 Redis 缓存中
     */
    void initResourceRoleCache();

    /**
     * 更新 Redis 缓存中的 [资源内容-角色名称] 对应关系列表
     *
     * @param resourceIds 对应关系发生变更的资源ids
     */
    void refreshResourceRoleCache(List<Long> resourceIds);
}
