package com.cafe.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.dto.UserDTO;
import com.cafe.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户数据访问接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询单个用户DTO
     *
     * @param username
     * @return
     */
    UserDTO oneDTO(@Param(value = "username") String username);
}
