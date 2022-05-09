package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.CategoryBrandRelationMapper;
import com.cafe.goods.model.CategoryBrandRelation;
import com.cafe.goods.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类-品牌关联 (服务实现类)
 */
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements CategoryBrandRelationService {

    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    public CategoryBrandRelationServiceImpl(CategoryBrandRelationMapper categoryBrandRelationMapper) {
        this.categoryBrandRelationMapper = categoryBrandRelationMapper;
    }
}
