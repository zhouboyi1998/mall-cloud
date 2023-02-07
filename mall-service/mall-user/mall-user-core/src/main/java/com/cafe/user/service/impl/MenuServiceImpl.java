package com.cafe.user.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.user.dao.MenuMapper;
import com.cafe.user.model.Menu;
import com.cafe.user.service.MenuService;
import com.cafe.user.vo.MenuTreeVO;
import com.cafe.common.constant.AuthorizationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单业务实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuTreeVO> tree(String userDetails) {
        // 从用户详细信息中获取角色列表
        List<String> roleNameList = ((JSONArray) JSONUtil.parseObj(userDetails).get(AuthorizationConstant.AUTHORITIES_CLAIM_NAME)).toList(String.class);
        // 根据角色列表获取对应的菜单列表树形格式 VO
        List<MenuTreeVO> menuList = menuMapper.tree(roleNameList);

        // 组装成树形格式
        return menuList.stream()
            // 筛选出所有一级菜单
            .filter(menuTreeVO -> menuTreeVO.getParentId().equals(0L))
            // 为所有一级菜单组装子菜单树
            .map(menuTreeVO -> menuTreeVO.setChildren(buildSubtree(menuTreeVO, menuList)))
            // 重新组装成 List
            .collect(Collectors.toList());
    }

    /**
     * 为当前菜单组装子菜单树
     *
     * @param node     当前菜单
     * @param menuList 所有菜单
     * @return
     */
    private List<MenuTreeVO> buildSubtree(MenuTreeVO node, List<MenuTreeVO> menuList) {
        return menuList.stream()
            // 从所有菜单中筛选出当前菜单的子菜单
            .filter(menuTreeVO -> menuTreeVO.getParentId().equals(node.getId()))
            // 递归调用组装树形结构
            .map(menuTreeVO -> menuTreeVO.setChildren(buildSubtree(menuTreeVO, menuList)))
            // 重新组装成 List
            .collect(Collectors.toList());
    }
}
