package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.CategoryBrandMapper;
import com.cafe.goods.model.CategoryBrand;
import com.cafe.goods.service.CategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类-品牌关联业务实现类
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {

    private final CategoryBrandMapper categoryBrandMapper;

    @Autowired
    public CategoryBrandServiceImpl(CategoryBrandMapper categoryBrandMapper) {
        this.categoryBrandMapper = categoryBrandMapper;
    }
}
