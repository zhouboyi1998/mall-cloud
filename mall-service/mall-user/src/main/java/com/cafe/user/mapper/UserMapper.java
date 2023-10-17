package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.User;
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
     * 根据用户名和客户端id查询用户详情
     *
     * @param username
     * @param clientId
     * @return
     */
    User detailByUsername(@Param(value = "username") String username, @Param(value = "clientId") String clientId);

    /**
     * 根据手机号和客户端id查询用户详情
     *
     * @param mobile
     * @param clientId
     * @return
     */
    User detailByMobile(@Param(value = "mobile") String mobile, @Param(value = "clientId") String clientId);
}
