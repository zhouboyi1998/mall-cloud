package com.cafe.search.solr.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.search.solr.model.SolrGoods;
import com.cafe.search.solr.service.SolrGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @LogPrint(value = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "path", dataType = "String")
    @GetMapping(value = "/{id}")
    public ResponseEntity<SolrGoods> one(@PathVariable(value = "id") String id) {
        SolrGoods solrGoods = solrGoodsService.one(id);
        return ResponseEntity.ok(solrGoods);
    }

    @LogPrint(value = "插入商品/更新商品")
    @ApiOperation(value = "插入商品/更新商品")
    @ApiImplicitParam(name = "solrGoods", value = "商品", required = true, paramType = "body", dataType = "SolrGoods")
    @PostMapping(value = "")
    public ResponseEntity<String> save(@RequestBody SolrGoods solrGoods) {
        solrGoodsService.save(solrGoods);
        return ResponseEntity.ok("save success!");
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, paramType = "path", dataType = "String")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
        solrGoodsService.delete(id);
        return ResponseEntity.ok("delete success!");
    }

    @LogPrint(value = "批量插入商品/批量更新商品")
    @ApiOperation(value = "批量插入商品/批量更新商品")
    @ApiImplicitParam(name = "solrGoodsList", value = "商品列表", required = true, paramType = "body", dataType = "List<SolrGoods>")
    @PostMapping(value = "/batch")
    public ResponseEntity<String> saveBatch(@RequestBody List<SolrGoods> solrGoodsList) {
        solrGoodsService.saveBatch(solrGoodsList);
        return ResponseEntity.ok("batch save success!");
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(name = "ids", value = "商品id列表", required = true, paramType = "body", dataType = "List<String>")
    @DeleteMapping(value = "/batch")
    public ResponseEntity<String> deleteBatch(@RequestBody List<String> ids) {
        solrGoodsService.deleteBatch(ids);
        return ResponseEntity.ok("batch delete success!");
    }
}
