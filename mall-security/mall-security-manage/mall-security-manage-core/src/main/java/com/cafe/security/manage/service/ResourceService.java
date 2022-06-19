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
     * 在依赖注入结束, 类初始化后立即执行此方法
     */
    void initRelationData();

    /**
     * 更新菜单路径和角色名称对应关系
     *
     * @param ids
     */
    void updateRelationData(List<Long> ids);
}
