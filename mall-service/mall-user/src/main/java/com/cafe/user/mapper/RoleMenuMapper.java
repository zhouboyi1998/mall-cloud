package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.bo.MenuRoleBO;
import com.cafe.user.model.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联关系数据访问接口
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据菜单ids获取 <菜单路径-角色名称> 对应关系列表
     *
     * @param menuIds 菜单ids
     * @return
     */
    List<MenuRoleBO> menuRoleBOList(@Param(value = "menuIds") List<Long> menuIds);
}
