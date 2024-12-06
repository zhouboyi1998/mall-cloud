package com.cafe.goods.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.goods.converter.SpuConverter;
import com.cafe.goods.facade.SpuFacade;
import com.cafe.goods.model.Sku;
import com.cafe.goods.model.Spu;
import com.cafe.goods.service.SkuService;
import com.cafe.goods.service.SpuService;
import com.cafe.goods.vo.SpuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.facade.impl
 * @Author: zhouboyi
 * @Date: 2024/12/3 17:29
 * @Description: 标准化产品单元防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class SpuFacadeImpl implements SpuFacade {

    private final SpuService spuService;

    private final SkuService skuService;

    @Override
    public SpuVO vo(Long skuId) {
        // 获取当前 SKU
        Sku sku = skuService.getById(skuId);
        if (Objects.isNull(sku)) {
            return null;
        }
        // 获取 SPU
        Spu spu = spuService.getById(sku.getSpuId());
        if (Objects.isNull(spu)) {
            return null;
        }
        // 获取 SKU 列表
        Sku query = new Sku().setSpuId(spu.getId()).setStatus(GoodsConstant.Status.LAUNCH);
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(query);
        List<Sku> skuList = skuService.list(wrapper);
        // 组装成 SpuVO 并返回
        return SpuConverter.INSTANCE.toVO(spu).setSkuList(skuList);
    }
}
