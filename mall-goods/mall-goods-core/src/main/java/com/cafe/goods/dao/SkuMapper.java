package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.goods.model.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Stock Keeping Unit 库存量单位 (Mapper 接口)
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 分页查询 SkuElasticSearchDTO 列表
     *
     * @param page
     * @return
     */
    Page<SkuElasticSearchDTO> pageSkuElasticSearchDTO(Page<SkuElasticSearchDTO> page);

    /**
     * 根据 SKU ids 查询 SkuElasticSearchDTO 列表
     *
     * @param ids
     * @return
     */
    List<SkuElasticSearchDTO> listSkuElasticSearchDTO(@Param(value = "ids") List<Long> ids);
}
