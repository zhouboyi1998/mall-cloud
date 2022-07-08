package com.cafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.admin.model.Menu;
import com.cafe.admin.vo.MenuTreeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单 (服务类)
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取树形格式的菜单列表
     *
     * @param request
     * @return
     */
    List<MenuTreeVO> listMenuTree(HttpServletRequest request);
}
