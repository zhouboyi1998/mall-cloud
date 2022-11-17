package com.cafe.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.bo.MenuRoleBO;
import com.cafe.user.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联数据访问接口
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 按菜单ids获取菜单路径-角色名称对应关系
     *
     * @param menuIds 菜单ids
     * @return
     */
    List<MenuRoleBO> listMenuRoleBO(@Param(value = "menuIds") List<Long> menuIds);
}
