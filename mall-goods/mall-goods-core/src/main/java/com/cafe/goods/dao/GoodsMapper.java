package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.bo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.dao
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:38
 * @Description:
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据 SKU ids 更新 SKU 状态
     *
     * @param ids
     * @param status
     */
    void updateStatus(@Param(value = "ids") List<Long> ids, @Param(value = "status") Integer status);

    /**
     * 根据 SKU ids 查询商品列表
     *
     * @param ids
     * @return
     */
    List<Goods> list(@Param(value = "ids") List<Long> ids);
}
