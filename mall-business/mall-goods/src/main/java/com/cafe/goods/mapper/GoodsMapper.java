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
     * 根据库存量单位ids查询商品列表
     *
     * @param ids
     * @return
     */
    List<Goods> list(@Param(value = "ids") List<Long> ids);

    /**
     * 根据ids更新库存量单位状态
     *
     * @param ids
     * @param status
     */
    void updateStatus(@Param(value = "ids") List<Long> ids, @Param(value = "status") Integer status);
}
