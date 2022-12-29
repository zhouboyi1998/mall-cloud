package com.cafe.merchant.dao;

import com.cafe.merchant.model.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.dao
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺数据访问接口
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

}
