package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.bo.ResourceRoleBO;
import com.cafe.user.model.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-资源关联关系数据访问接口
 */
@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    /**
     * 根据资源ids获取 [资源内容-角色名称] 对应关系列表
     *
     * @param resourceIds 资源ids
     * @return [资源内容-角色名称] 对应关系列表
     */
    List<ResourceRoleBO> resourceRoleBOList(@Param(value = "resourceIds") List<Long> resourceIds);
}
