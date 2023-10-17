package com.cafe.goods.service;

import com.cafe.goods.bo.Goods;

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
     * 查询商品列表
     *
     * @return
     */
    List<Goods> list();

    /**
     * 批量上下架商品
     *
     * @param ids
     * @param status
     */
    void launch(List<Long> ids, Integer status);
}
