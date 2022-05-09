package com.cafe.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.admin.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色 (Mapper 接口)
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
