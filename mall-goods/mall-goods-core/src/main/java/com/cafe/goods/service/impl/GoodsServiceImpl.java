package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.GoodsMapper;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:36
 * @Description:
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<Goods> list(List<Long> ids) {
        return goodsMapper.list(ids);
    }

    @Override
    public Page<Goods> page(Page<Goods> page) {
        return goodsMapper.page(page);
    }
}
