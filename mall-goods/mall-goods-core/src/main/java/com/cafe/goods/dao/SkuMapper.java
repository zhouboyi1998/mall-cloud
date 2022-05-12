package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Stock Keeping Unit 库存量单位 (Mapper 接口)
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

}