package com.cafe.search.solr.service;

import com.cafe.search.solr.model.SolrGoods;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.service
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description:
 */
public interface SolrGoodsService {

    /**
     * 保存商品 (ID不存在时插入商品 / ID存在时更新商品)
     *
     * @param solrGoods
     */
    void save(SolrGoods solrGoods);
}
