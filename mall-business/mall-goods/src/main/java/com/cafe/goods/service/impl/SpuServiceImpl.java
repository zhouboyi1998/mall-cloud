package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.goods.converter.SpuConverter;
import com.cafe.goods.mapper.SkuMapper;
import com.cafe.goods.mapper.SpuMapper;
import com.cafe.goods.model.Sku;
import com.cafe.goods.model.Spu;
import com.cafe.goods.service.SpuService;
import com.cafe.goods.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元业务实现类
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    private final SpuMapper spuMapper;

    private final SkuMapper skuMapper;

    @Autowired
    public SpuServiceImpl(SpuMapper spuMapper, SkuMapper skuMapper) {
        this.spuMapper = spuMapper;
        this.skuMapper = skuMapper;
    }

    @Override
    public SpuVO vo(Long skuId) {
        // 获取当前 SKU
        Sku sku = skuMapper.selectById(skuId);
        if (Objects.isNull(sku)) {
            return null;
        }
        // 获取 SPU
        Spu spu = spuMapper.selectById(sku.getSpuId());
        if (Objects.isNull(spu)) {
            return null;
        }
        // 获取 SKU 列表
        Sku query = new Sku().setSpuId(spu.getId()).setStatus(GoodsConstant.Status.LAUNCH);
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(query);
        List<Sku> skuList = skuMapper.selectList(wrapper);
        // 组装成 SpuVO 并返回
        return SpuConverter.INSTANCE.toVO(spu).setSkuList(skuList);
    }
}
