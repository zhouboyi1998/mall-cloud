package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.common.util.tree.TreeUtil;
import com.cafe.goods.converter.CategoryConverter;
import com.cafe.goods.mapper.CategoryMapper;
import com.cafe.goods.model.Category;
import com.cafe.goods.service.CategoryService;
import com.cafe.goods.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Tree> treeList() {
        List<Category> categoryList = categoryMapper.selectList(WrapperUtil.emptyQueryWrapper());
        List<CategoryTreeVO> treeVOList = CategoryConverter.INSTANCE.toTreeVOList(categoryList);
        return TreeUtil.buildTreeList(treeVOList);
    }

    @Override
    public List<Tree> treeList(Long parentId) {
        List<CategoryTreeVO> treeVOList = categoryMapper.selectTreeList(parentId);
        return TreeUtil.buildTreeList(treeVOList, parentId);
    }

    @Override
    public Tree tree(Long id) {
        List<CategoryTreeVO> treeVOList = categoryMapper.selectTree(id);
        return TreeUtil.buildTree(treeVOList, id);
    }
}
