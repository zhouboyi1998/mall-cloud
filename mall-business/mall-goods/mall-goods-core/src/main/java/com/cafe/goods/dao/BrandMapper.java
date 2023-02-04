package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 品牌数据访问接口
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

}
