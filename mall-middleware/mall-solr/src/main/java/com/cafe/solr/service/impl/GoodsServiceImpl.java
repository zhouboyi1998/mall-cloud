package com.cafe.solr.service.impl;

import com.cafe.common.constant.solr.SolrConstant;
import com.cafe.solr.model.Goods;
import com.cafe.solr.service.GoodsService;
import org.apache.solr.common.SolrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:18
 * @Description: Solr 商品业务实现类
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private final SolrTemplate solrTemplate;

    @Autowired
    public GoodsServiceImpl(SolrTemplate solrTemplate) {
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
