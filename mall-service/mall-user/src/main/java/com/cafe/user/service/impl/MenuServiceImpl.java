package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.user.mapper.MenuMapper;
import com.cafe.user.model.entity.Menu;
import com.cafe.user.model.vo.MenuTreeVO;
import com.cafe.user.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单业务实现类
 */
@RequiredArgsConstructor
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<Tree> treeList(List<String> authorities) {
        // 根据权限列表查询菜单列表
        List<MenuTreeVO> menuList = menuMapper.selectTreeVOList(authorities);
        // 组装成树形格式
        return TreeUtil.buildTreeList(menuList);
    }
}
