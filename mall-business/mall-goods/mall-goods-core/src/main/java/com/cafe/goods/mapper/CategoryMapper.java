package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类数据访问接口
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
