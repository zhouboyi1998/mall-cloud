package com.cafe.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联数据访问接口
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
