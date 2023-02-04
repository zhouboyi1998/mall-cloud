package com.cafe.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.user.model.Menu;
import com.cafe.user.vo.MenuTreeVO;

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
     * 根据角色列表获取树形格式的菜单列表
     *
     * @param userDetails 用户详细信息
     * @return
     */
    List<MenuTreeVO> listMenuTree(String userDetails);
}
