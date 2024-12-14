package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.user.model.entity.Resource;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 资源业务接口
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据权限列表查询菜单树列表
     *
     * @param authorities 权限列表
     * @return 菜单树列表
     */
    List<Tree> menuTreeList(List<String> authorities);
}
