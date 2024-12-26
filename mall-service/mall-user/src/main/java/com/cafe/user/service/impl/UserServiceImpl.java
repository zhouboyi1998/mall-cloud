package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.mapper.UserMapper;
import com.cafe.user.model.converter.UserConverter;
import com.cafe.user.model.entity.User;
import com.cafe.user.model.vo.UserVO;
import com.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户业务实现类
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserVO detail(String clientId, User user) {
        User detail = userMapper.detail(clientId, user);
        return UserConverter.INSTANCE.toVO(detail);
    }
}
