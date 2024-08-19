package com.cafe.goodscenter.service.impl;

import com.cafe.common.core.exception.BusinessException;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.elasticsearch.feign.GoodsIndexFeign;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.goods.feign.SpuFeign;
import com.cafe.goods.vo.SpuVO;
import com.cafe.goodscenter.converter.GoodsConverter;
import com.cafe.goodscenter.service.GoodsCenterService;
import com.cafe.goodscenter.vo.GoodsSummary;
import com.cafe.goodscenter.vo.SpuDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.service.impl
 * @Author: zhouboyi
 * @Date: 2024/7/31 23:18
 * @Description: 商品中心业务实现类
 */
@RequiredArgsConstructor
@Service
public class GoodsCenterServiceImpl implements GoodsCenterService {

    private final GoodsIndexFeign goodsIndexFeign;

    private final GoodsFeign goodsFeign;

    private final SpuFeign spuFeign;

    @Override
    public List<GoodsSummary> summary(String keyword) {
        // 使用关键词搜索 SKU id 列表
        List<Long> skuIds = Optional.ofNullable(goodsIndexFeign.searchId(keyword))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.NO_MATCH_GOODS, keyword));
        // 使用 SKU id 列表获取商品列表
        List<Goods> goodsList = Optional.ofNullable(goodsFeign.list(skuIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_SUMMARY_NOT_FOUND, skuIds));
        // 转换成商品概要列表
        return GoodsConverter.INSTANCE.toSummaryList(goodsList);
    }

    @Override
    public SpuDetail detail(Long skuId) {
        // 根据 skuId 查询 SPU 视图模型
        SpuVO spuVO = Optional.ofNullable(spuFeign.vo(skuId))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.SPU_NOT_FOUND, skuId));
        // 转换成商品详情并返回
        return GoodsConverter.INSTANCE.toDetail(spuVO);
    }
}
