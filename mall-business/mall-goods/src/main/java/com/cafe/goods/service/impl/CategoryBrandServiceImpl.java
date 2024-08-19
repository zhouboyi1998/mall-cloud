package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.mapper.CategoryBrandMapper;
import com.cafe.goods.model.CategoryBrand;
import com.cafe.goods.service.CategoryBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类-品牌关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {

    private final CategoryBrandMapper categoryBrandMapper;
}
