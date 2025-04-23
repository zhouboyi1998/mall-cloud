package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.mapper.SkuMapper;
import com.cafe.goods.model.entity.Sku;
import com.cafe.goods.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位业务实现类
 */
@RequiredArgsConstructor
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    private final SkuMapper skuMapper;

    @Override
    public List<Sku> offShelveList(List<Long> skuIds) {
        return skuMapper.offShelveList(skuIds);
    }

    @Override
    public Integer updateStatus(Integer status, List<Long> skuIds) {
        return skuMapper.updateStatus(status, skuIds);
    }
}
