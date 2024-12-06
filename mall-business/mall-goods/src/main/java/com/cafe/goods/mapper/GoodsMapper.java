package com.cafe.goods.mapper;

import com.cafe.goods.bo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:38
 * @Description:
 */
@Mapper
public interface GoodsMapper {

    /**
     * 根据 skuIds 查询商品列表
     *
     * @param ids 库存量单位ID列表
     * @return 商品列表
     */
    List<Goods> list(@Param(value = "ids") List<Long> ids);
}
