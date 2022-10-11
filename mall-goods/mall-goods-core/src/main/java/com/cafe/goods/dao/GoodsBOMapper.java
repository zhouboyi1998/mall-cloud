package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.goods.bo.GoodsBO;
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
public interface GoodsBOMapper extends BaseMapper<GoodsBO> {

    /**
     * 根据 SKU ids 查询商品列表
     *
     * @param ids
     * @return
     */
    List<GoodsBO> list(@Param(value = "ids") List<Long> ids);

    /**
     * 分页查询商品列表
     *
     * @param page
     * @return
     */
    Page<GoodsBO> page(Page<GoodsBO> page);
}
