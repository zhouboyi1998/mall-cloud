package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.bo.ResourceRoleBO;
import com.cafe.user.model.entity.RoleResource;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-资源关联关系业务接口
 */
public interface RoleResourceService extends IService<RoleResource> {

    /**
     * 根据资源ids获取 [资源内容-角色名称] 对应关系列表
     *
     * @param resourceIds 资源ID列表
     * @return [资源内容-角色名称] 对应关系列表
     */
    List<ResourceRoleBO> resourceRoleBOList(List<Long> resourceIds);
}
