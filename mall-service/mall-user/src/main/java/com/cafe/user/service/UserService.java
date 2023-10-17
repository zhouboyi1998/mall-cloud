package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.User;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户业务接口
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名和客户端id查询用户详情
     *
     * @param username
     * @param clientId
     * @return
     */
    User detailByUsername(String username, String clientId);

    /**
     * 根据手机号和客户端id查询用户详情
     *
     * @param mobile
     * @param clientId
     * @return
     */
    User detailByMobile(String mobile, String clientId);
}
