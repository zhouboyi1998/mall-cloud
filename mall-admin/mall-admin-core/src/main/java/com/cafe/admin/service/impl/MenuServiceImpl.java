package com.cafe.admin.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.admin.dao.MenuMapper;
import com.cafe.admin.model.Menu;
import com.cafe.admin.service.MenuService;
import com.cafe.admin.vo.MenuTreeVO;
import com.cafe.common.constant.AuthenticationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单 (服务实现类)
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuTreeVO> listMenuTree(HttpServletRequest request) {
        // 获取请求头中的 user 详细信息, 转换为 JSON 对象
        JSONObject userDetails
            = JSONUtil.parseObj(request.getHeader(AuthenticationConstant.USER_DETAILS_HEADER));
        // 获取角色列表, 转换为 List 对象
        List<String> roleNameList
            = ((JSONArray) userDetails.get(AuthenticationConstant.AUTHORITY_CLAIM_NAME)).toList(String.class);

        // 根据角色列表获取对应的菜单列表
        List<MenuTreeVO> menuList = menuMapper.listMenuTreeVO(roleNameList);
        // 组装成树形格式, 设置 children 字段
        List<MenuTreeVO> menuTreeVOList = menuList
            .stream()
            // 筛选出所有一级菜单
            .filter(menuTreeVO -> menuTreeVO.getParentId().equals(0L))
            // 调用 getChildren() 方法, 为每个一级菜单获取子菜单
            .map((menuTreeVO) -> menuTreeVO.setChildren(getChildren(menuTreeVO, menuList)))
            .collect(Collectors.toList());

        return menuTreeVOList;
    }

    /**
     * 组装树形
     *
     * @param root     当前节点
     * @param menuList 所有节点
     * @return
     */
    private List<MenuTreeVO> getChildren(MenuTreeVO root, List<MenuTreeVO> menuList) {
        List<MenuTreeVO> children = menuList
            .stream()
            // 筛选出当前节点的所有子节点
            .filter(menuTreeVO -> menuTreeVO.getParentId().equals(root.getId()))
            // 递归调用组装树形结构
            .map(menuTreeVO -> menuTreeVO.setChildren(getChildren(menuTreeVO, menuList)))
            .collect(Collectors.toList());
        return children;
    }
}
