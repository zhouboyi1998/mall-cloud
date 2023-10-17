package com.cafe.search.solr.service.impl;

import com.cafe.common.constant.solr.SolrConstant;
import com.cafe.search.solr.model.Goods;
import com.cafe.search.solr.service.SolrGoodsService;
import org.apache.solr.common.SolrException;
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
        Goods goods = solrTemplate.getById(SolrConstant.Goods.INDEX, id, Goods.class)
            .orElseThrow(() -> new SolrException(SolrException.ErrorCode.NOT_FOUND, "Goods id is not exist!"));
        solrTemplate.commit(SolrConstant.Goods.INDEX);
        return goods;
    }

    @Override
    public void save(Goods goods) {
        solrTemplate.saveBean(SolrConstant.Goods.INDEX, goods);
        solrTemplate.commit(SolrConstant.Goods.INDEX);
    }

    @Override
    public void saveBatch(List<Goods> goodsList) {
        solrTemplate.saveBeans(SolrConstant.Goods.INDEX, goodsList);
        solrTemplate.commit(SolrConstant.Goods.INDEX);
    }

    @Override
    public void delete(String id) {
        solrTemplate.deleteByIds(SolrConstant.Goods.INDEX, id);
        solrTemplate.commit(SolrConstant.Goods.INDEX);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        solrTemplate.deleteByIds(SolrConstant.Goods.INDEX, ids);
        solrTemplate.commit(SolrConstant.Goods.INDEX);
    }
}
