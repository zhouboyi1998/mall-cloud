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
     * 根据用户名和客户端id查询单个用户
     *
     * @param username
     * @param clientId
     * @return
     */
    User detail(String username, String clientId);
}
