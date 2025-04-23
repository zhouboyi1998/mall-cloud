package com.cafe.goods.service;

import com.cafe.goods.model.bo.Goods;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:35
 * @Description:
 */
public interface GoodsService {

    /**
     * 根据 skuIds 查询商品列表
     *
     * @param queryType 查询类型
     * @param skuIds    库存量单位ID列表
     * @return 商品列表
     */
    List<Goods> list(String queryType, List<Long> skuIds);
}
