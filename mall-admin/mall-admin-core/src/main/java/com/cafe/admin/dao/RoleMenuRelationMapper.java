package com.cafe.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.admin.model.RoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (Mapper 接口)
 */
@Mapper
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

}
