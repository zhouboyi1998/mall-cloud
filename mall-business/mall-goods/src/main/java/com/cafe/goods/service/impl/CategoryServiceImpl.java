package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.goods.mapper.CategoryMapper;
import com.cafe.goods.model.converter.CategoryConverter;
import com.cafe.goods.model.dto.CategoryTreeDTO;
import com.cafe.goods.model.entity.Category;
import com.cafe.goods.model.query.CategoryTreeListQuery;
import com.cafe.goods.model.query.CategoryTreeNodeQuery;
import com.cafe.goods.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类业务实现类
 */
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public Tree treeNode(CategoryTreeNodeQuery query) {
        Category category = CategoryConverter.INSTANCE.toEntity(query);
        List<CategoryTreeDTO> dtoList = categoryMapper.selectTreeDTOList(category);
        return TreeUtil.buildTreeNode(dtoList, category.getId());
    }

    @Override
    public List<Tree> treeList(CategoryTreeListQuery query) {
        Category category = CategoryConverter.INSTANCE.toEntity(query);
        List<CategoryTreeDTO> dtoList = categoryMapper.selectTreeDTOList(category);
        return TreeUtil.buildTreeList(dtoList, query.getParentId());
    }
}
