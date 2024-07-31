package com.cafe.solr.service.impl;

import com.cafe.common.constant.solr.SolrConstant;
import com.cafe.solr.index.GoodsIndex;
import com.cafe.solr.service.GoodsIndexService;
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
 * @Description: 商品全文索引业务实现类
 */
@Service
public class GoodsIndexServiceImpl implements GoodsIndexService {

    private final SolrTemplate solrTemplate;

    @Autowired
    public GoodsIndexServiceImpl(SolrTemplate solrTemplate) {
        this.solrTemplate = solrTemplate;
    }

    @Override
    public GoodsIndex one(String id) {
        GoodsIndex goodsIndex = solrTemplate.getById(SolrConstant.Goods.INDEX, id, GoodsIndex.class)
            .orElseThrow(() -> new SolrException(SolrException.ErrorCode.NOT_FOUND, "Goods id is not exist!"));
        solrTemplate.commit(SolrConstant.Goods.INDEX);
        return goodsIndex;
    }

    @Override
    public void save(GoodsIndex goodsIndex) {
        solrTemplate.saveBean(SolrConstant.Goods.INDEX, goodsIndex);
        solrTemplate.commit(SolrConstant.Goods.INDEX);
    }

    @Override
    public void saveBatch(List<GoodsIndex> goodsIndexList) {
        solrTemplate.saveBeans(SolrConstant.Goods.INDEX, goodsIndexList);
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
