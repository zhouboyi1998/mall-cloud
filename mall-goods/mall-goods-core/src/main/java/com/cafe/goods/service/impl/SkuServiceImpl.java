package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.SkuMapper;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.goods.model.Sku;
import com.cafe.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Stock Keeping Unit 库存量单位 (服务实现类)
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    private SkuMapper skuMapper;

    @Autowired
    public SkuServiceImpl(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }

    @Override
    public Page<SkuElasticSearchDTO> pageSkuElasticSearchDTO(Page<SkuElasticSearchDTO> page) {
        return skuMapper.pageSkuElasticSearchDTO(page);
    }
}
