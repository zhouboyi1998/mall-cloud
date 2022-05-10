package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.model.Role;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色 (服务类)
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据管理员id查询角色名称列表
     *
     * @param adminId 管理员id
     * @return
     */
    List<String> listRoleName(Long adminId);
}
