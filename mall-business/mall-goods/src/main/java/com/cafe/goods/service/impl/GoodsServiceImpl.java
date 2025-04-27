package com.cafe.goods.service.impl;

import com.cafe.goods.mapper.GoodsMapper;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.service.GoodsService;
import com.cafe.infrastructure.redis.annotation.FallbackCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:36
 * @Description:
 */
@RequiredArgsConstructor
@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper;

    @FallbackCache(condition = "#queryType == T(com.cafe.common.constant.model.GoodsConstant.QueryType).FULL")
    @Override
    public List<Goods> list(String queryType, List<Long> skuIds) {
        return goodsMapper.list(queryType, skuIds);
    }
}
