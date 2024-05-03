package com.cafe.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.manager.mapper.ManagerMapper;
import com.cafe.manager.model.Manager;
import com.cafe.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.manager.service.impl
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 管理员业务实现类
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerServiceImpl(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }
}
