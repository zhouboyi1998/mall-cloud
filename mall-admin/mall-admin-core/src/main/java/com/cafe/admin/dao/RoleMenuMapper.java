package com.cafe.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.admin.bo.MenuPathAndRoleNameBO;
import com.cafe.admin.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (Mapper 接口)
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 获取菜单路径和角色名称对应关系
     *
     * @param menuIds
     * @return
     */
    List<MenuPathAndRoleNameBO> listMenuPathAndRoleNameBO(@Param(value = "menuIds") List<Long> menuIds);
}
