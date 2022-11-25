package com.cafe.search.solr.service.impl;

import com.cafe.common.constant.SolrConstant;
import com.cafe.search.solr.model.Goods;
import com.cafe.search.solr.service.SolrGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description:
 */
@Service
public class SolrGoodsServiceImpl implements SolrGoodsService {

    private final SolrTemplate solrTemplate;

    @Autowired
    public SolrGoodsServiceImpl(SolrTemplate solrTemplate) {
        this.solrTemplate = solrTemplate;
    }

    @Override
    public Goods one(String id) {
        Goods goods = solrTemplate.getById(SolrConstant.GOODS_INDEX, id, Goods.class).orElse(null);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
        return goods;
    }

    @Override
    public void save(Goods goods) {
        solrTemplate.saveBean(SolrConstant.GOODS_INDEX, goods);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }

    @Override
    public void saveBatch(List<Goods> goodsList) {
        solrTemplate.saveBeans(SolrConstant.GOODS_INDEX, goodsList);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }

    @Override
    public void delete(String id) {
        solrTemplate.deleteByIds(SolrConstant.GOODS_INDEX, id);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        solrTemplate.deleteByIds(SolrConstant.GOODS_INDEX, ids);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }
}
