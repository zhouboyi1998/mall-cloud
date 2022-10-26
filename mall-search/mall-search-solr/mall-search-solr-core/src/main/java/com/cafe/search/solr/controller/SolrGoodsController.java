package com.cafe.search.solr.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.search.solr.model.SolrGoods;
import com.cafe.search.solr.service.SolrGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.controller
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:17
 * @Description: Solr 商品接口
 */
@Api(value = "Solr 商品接口")
@RestController
@RequestMapping(value = "/solr/goods")
public class SolrGoodsController {

    private SolrGoodsService solrGoodsService;

    @Autowired
    public SolrGoodsController(SolrGoodsService solrGoodsService) {
        this.solrGoodsService = solrGoodsService;
    }

    @LogPrint(value = "保存商品 (ID不存在时插入商品 / ID存在时更新商品)")
    @ApiOperation(value = "保存商品 (ID不存在时插入商品 / ID存在时更新商品)")
    @ApiImplicitParam(name = "solrGoods", value = "Solr 商品对象", required = true, paramType = "body", dataType = "SolrGoods")
    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody SolrGoods solrGoods) {
        solrGoodsService.save(solrGoods);
        return ResponseEntity.ok("保存成功");
    }
}
