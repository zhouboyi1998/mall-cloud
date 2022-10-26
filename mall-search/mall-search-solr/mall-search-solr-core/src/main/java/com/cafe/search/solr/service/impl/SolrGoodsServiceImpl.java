package com.cafe.search.solr.service.impl;

import com.cafe.goods.feign.GoodsFeign;
import com.cafe.search.solr.constant.SolrConstant;
import com.cafe.search.solr.model.SolrGoods;
import com.cafe.search.solr.service.SolrGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description:
 */
@Service
public class SolrGoodsServiceImpl implements SolrGoodsService {

    private SolrTemplate solrTemplate;

    private GoodsFeign goodsFeign;

    @Autowired
    public SolrGoodsServiceImpl(SolrTemplate solrTemplate, GoodsFeign goodsFeign) {
        this.solrTemplate = solrTemplate;
        this.goodsFeign = goodsFeign;
    }

    @Override
    public void save(SolrGoods solrGoods) {
        solrTemplate.saveBean(SolrConstant.GOODS_INDEX, solrGoods);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }
}
