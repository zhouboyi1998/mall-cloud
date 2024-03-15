package com.cafe.clickhouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.clickhouse.mapper.GoodsMapper;
import com.cafe.clickhouse.model.Goods;
import com.cafe.clickhouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.clickhouse.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/15 17:10
 * @Description: 商品业务实现类
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }
}
