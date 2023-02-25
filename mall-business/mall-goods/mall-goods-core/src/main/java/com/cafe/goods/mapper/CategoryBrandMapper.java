package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类-品牌关联关系数据访问接口
 */
@Mapper
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {

}
