package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.mapper.SkuMapper;
import com.cafe.goods.model.Sku;
import com.cafe.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位业务实现类
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    private final SkuMapper skuMapper;

    @Autowired
    public SkuServiceImpl(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }
}
