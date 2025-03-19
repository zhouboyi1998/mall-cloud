package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.entity.User;
import com.cafe.user.model.vo.UserVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户业务接口
 */
public interface UserService extends IService<User> {

    /**
     * 根据客户端id和用户查询条件查询用户信息
     *
     * @param clientId 客户端id
     * @param user     用户查询条件
     * @return 用户信息
     */
    UserVO detail(String clientId, User user);
}
