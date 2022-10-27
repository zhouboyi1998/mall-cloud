package com.cafe.search.solr.service.impl;

import cn.hutool.json.JSONUtil;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.search.solr.constant.SolrConstant;
import com.cafe.search.solr.model.SolrGoods;
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

    private SolrTemplate solrTemplate;

    private GoodsFeign goodsFeign;

    @Autowired
    public SolrGoodsServiceImpl(SolrTemplate solrTemplate, GoodsFeign goodsFeign) {
        this.solrTemplate = solrTemplate;
        this.goodsFeign = goodsFeign;
    }

    @Override
    public SolrGoods one(String id) {
        SolrGoods solrGoods = solrTemplate.getById(SolrConstant.GOODS_INDEX, id, SolrGoods.class).get();
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
        return solrGoods;
    }

    @Override
    public void save(SolrGoods solrGoods) {
        solrTemplate.saveBean(SolrConstant.GOODS_INDEX, solrGoods);
        solrTemplate.commit(SolrConstant.GOODS_INDEX);
    }

    @Override
    public void saveBatch(List<SolrGoods> solrGoodsList) {
        solrTemplate.saveBeans(SolrConstant.GOODS_INDEX, solrGoodsList);
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

    @Override
    public void importBatch(Long current, Long size) {
        // 分页获取商品列表
        List<Goods> goodsList = goodsFeign.page(current, size).getBody().getRecords();
        // 转换类型
        List<SolrGoods> solrGoodsList = JSONUtil.toList(JSONUtil.parseArray(goodsList), SolrGoods.class);
        // 批量插入商品数据
        saveBatch(solrGoodsList);
    }
}
