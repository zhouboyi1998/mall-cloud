package com.cafe.goodscenter.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.elasticsearch.feign.GoodsIndexFeign;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.goods.feign.SpuFeign;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.model.vo.SpuVO;
import com.cafe.goodscenter.model.converter.GoodsConverter;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.model.vo.SpuDetail;
import com.cafe.goodscenter.service.GoodsCenterService;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<GoodsSummary> summary(Integer current, Integer size, String sortField, String sortRule, String keyword) {
        // 使用关键词搜索商品索引列表
        List<GoodsIndex> goodsIndexList = Optional.ofNullable(goodsIndexFeign.search(current, size, sortField, sortRule, keyword))
            .map(ResponseEntity::getBody)
            .map(Page::getContent)
            .orElse(Collections.emptyList());
        List<Long> skuIds = goodsIndexList.stream().map(GoodsIndex::getId).collect(Collectors.toList());
        // 如果没有搜索到商品索引, 返回空列表
        if (CollectionUtils.isEmpty(skuIds)) {
            return Collections.emptyList();
        }
        // 查询商品列表
        List<Goods> goodsList = Optional.ofNullable(goodsFeign.list(skuIds))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_SUMMARY_NOT_FOUND, skuIds));
        // 转换成商品概要列表
        return GoodsConverter.INSTANCE.toSummaryList(goodsList);
    }

    @Override
    public SpuDetail detail(Long skuId) {
        // 根据 SKU id 查询 SPU 视图模型
        SpuVO spuVO = Optional.ofNullable(spuFeign.vo(skuId))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.GOODS_DETAIL_NOT_FOUND, skuId));
        // 转换成商品详情并返回
        return GoodsConverter.INSTANCE.toDetail(spuVO);
    }
}
