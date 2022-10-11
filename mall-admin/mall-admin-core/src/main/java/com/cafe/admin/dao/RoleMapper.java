package com.cafe.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.admin.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色数据访问接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有角色名称列表
     *
     * @return
     */
    List<String> listRoleName();

    /**
     * 根据管理员id查询角色名称列表
     *
     * @param adminId 管理员id
     * @return
     */
    List<String> listRoleNameByAdminId(@Param(value = "adminId") Long adminId);
}
