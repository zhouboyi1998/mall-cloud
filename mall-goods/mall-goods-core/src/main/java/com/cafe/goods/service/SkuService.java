package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.goods.model.Sku;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Stock Keeping Unit 库存量单位 (服务类)
 */
public interface SkuService extends IService<Sku> {

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
    List<SkuElasticSearchDTO> listSkuElasticSearchDTO(List<Long> ids);
}
