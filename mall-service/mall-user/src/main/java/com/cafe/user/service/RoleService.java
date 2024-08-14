package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.Role;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色业务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询角色名称列表
     *
     * @param userId 用户id
     * @return
     */
    List<String> nameList(Long userId);
}
