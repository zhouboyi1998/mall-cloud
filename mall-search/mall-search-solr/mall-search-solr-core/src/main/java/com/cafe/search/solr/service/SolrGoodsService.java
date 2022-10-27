package com.cafe.search.solr.service;

import com.cafe.search.solr.model.SolrGoods;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.service
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description:
 */
public interface SolrGoodsService {

    /**
     * 获取商品
     *
     * @param id
     * @return
     */
    SolrGoods one(String id);

    /**
     * 插入商品/更新商品
     *
     * @param solrGoods
     */
    void save(SolrGoods solrGoods);

    /**
     * 批量插入商品/批量更新商品
     *
     * @param solrGoodsList
     */
    void saveBatch(List<SolrGoods> solrGoodsList);

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

    /**
     * 批量导入商品
     *
     * @param current
     * @param size
     */
    void importBatch(Long current, Long size);
}
