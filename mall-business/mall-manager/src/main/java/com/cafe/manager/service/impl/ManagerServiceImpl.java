package com.cafe.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.manager.mapper.ManagerMapper;
import com.cafe.manager.model.entity.Manager;
import com.cafe.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.manager.service.impl
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 管理员业务实现类
 */
@RequiredArgsConstructor
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    private final ManagerMapper managerMapper;
}
