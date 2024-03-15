package com.cafe.solr.service;

import com.cafe.solr.model.Goods;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.service
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description: Solr 商品业务接口
 */
public interface GoodsService {

    /**
     * 获取商品
     *
     * @param id
     * @return
     */
    Goods one(String id);

    /**
     * 插入商品/更新商品
     *
     * @param goods
     */
    void save(Goods goods);

    /**
     * 批量插入商品/批量更新商品
     *
     * @param goodsList
     */
    void saveBatch(List<Goods> goodsList);

    /**
     * 删除商品
     *
     * @param id
     */
    void delete(String id);

    /**
     * 批量删除商品
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);
}
