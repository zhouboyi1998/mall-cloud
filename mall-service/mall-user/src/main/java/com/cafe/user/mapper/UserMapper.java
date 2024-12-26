package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户数据访问接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据客户端id和用户查询条件查询用户信息
     *
     * @param clientId 客户端id
     * @param user     用户查询条件
     * @return 用户信息
     */
    User detail(@Param(value = "clientId") String clientId, @Param(value = "user") User user);
}
