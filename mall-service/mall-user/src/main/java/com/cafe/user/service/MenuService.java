package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.user.model.Menu;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单业务接口
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据权限列表查询菜单树列表
     *
     * @param authorities 权限列表
     * @return
     */
    List<Tree> treeList(List<String> authorities);
}
