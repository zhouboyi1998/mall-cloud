package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.entity.User;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户业务接口
 */
public interface UserService extends IService<User> {

    /**
     * 根据客户端id和用户信息查询用户
     *
     * @param clientId
     * @param user
     * @return
     */
    User detail(String clientId, User user);
}
