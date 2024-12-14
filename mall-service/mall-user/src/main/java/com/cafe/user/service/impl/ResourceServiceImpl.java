package com.cafe.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.user.mapper.ResourceMapper;
import com.cafe.user.model.dto.MenuTreeDTO;
import com.cafe.user.model.entity.Resource;
import com.cafe.user.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 资源业务实现类
 */
@RequiredArgsConstructor
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceMapper resourceMapper;

    @Override
    public List<Tree> menuTreeList(List<String> authorities) {
        // 根据权限列表查询菜单树DTO列表
        List<MenuTreeDTO> dtoList = resourceMapper.selectMenuTreeDTOList(authorities);
        // 组装成树形格式
        return TreeUtil.buildTreeList(dtoList);
    }
}
