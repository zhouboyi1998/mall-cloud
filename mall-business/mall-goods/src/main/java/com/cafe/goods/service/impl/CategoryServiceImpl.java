package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.core.exception.BusinessException;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.goods.mapper.CategoryMapper;
import com.cafe.goods.model.Category;
import com.cafe.goods.service.CategoryService;
import com.cafe.goods.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类业务实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Tree treeNode(Category category) {
        if (Objects.isNull(category.getId())) {
            throw new BusinessException(HttpStatusEnum.FAIL.value(), "分类id不允许为空", category);
        }
        List<CategoryTreeVO> treeList = categoryMapper.selectTreeVOList(category);
        return TreeUtil.buildTreeNode(treeList, category.getId());
    }

    @Override
    public List<Tree> treeList(Category category) {
        if (Objects.isNull(category.getParentId())) {
            throw new BusinessException(HttpStatusEnum.FAIL.value(), "上级分类id不允许为空", category);
        }
        List<CategoryTreeVO> treeList = categoryMapper.selectTreeVOList(category);
        return TreeUtil.buildTreeList(treeList, category.getParentId());
    }
}
