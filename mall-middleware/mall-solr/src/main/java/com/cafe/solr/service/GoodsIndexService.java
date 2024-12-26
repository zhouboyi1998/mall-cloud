package com.cafe.solr.service;

import com.cafe.solr.model.index.GoodsIndex;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.service
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description: 商品全文索引业务接口
 */
public interface GoodsIndexService {

    /**
     * 获取商品索引
     *
     * @param id 商品id
     * @return 商品索引
     */
    GoodsIndex one(String id);

    /**
     * 插入/更新商品索引
     *
     * @param goodsIndex 商品索引
     */
    void save(GoodsIndex goodsIndex);

    /**
     * 批量插入/批量更新商品索引
     *
     * @param goodsIndexList 商品索引列表
     */
    void saveBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 删除商品索引
     *
     * @param id 商品id
     */
    void delete(String id);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品id列表
     */
    void deleteBatch(List<String> ids);
}
