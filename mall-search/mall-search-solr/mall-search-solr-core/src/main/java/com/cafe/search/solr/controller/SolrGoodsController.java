package com.cafe.search.solr.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.search.solr.model.SolrGoods;
import com.cafe.search.solr.service.SolrGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<SolrGoods> one(@PathVariable(value = "id") String id) {
        SolrGoods solrGoods = solrGoodsService.one(id);
        return ResponseEntity.ok(solrGoods);
    }

    @LogPrint(value = "插入商品/更新商品")
    @ApiOperation(value = "插入商品/更新商品")
    @ApiImplicitParam(value = "商品Model", name = "solrGoods", dataType = "SolrGoods", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<String> save(@RequestBody SolrGoods solrGoods) {
        solrGoodsService.save(solrGoods);
        return ResponseEntity.ok("save success!");
    }

    @LogPrint(value = "批量插入商品/批量更新商品")
    @ApiOperation(value = "批量插入商品/批量更新商品")
    @ApiImplicitParam(value = "商品列表", name = "solrGoodsList", dataType = "List<SolrGoods>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<String> saveBatch(@RequestBody List<SolrGoods> solrGoodsList) {
        solrGoodsService.saveBatch(solrGoodsList);
        return ResponseEntity.ok("batch save success!");
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
        solrGoodsService.delete(id);
        return ResponseEntity.ok("delete success!");
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<String> deleteBatch(@RequestBody List<String> ids) {
        solrGoodsService.deleteBatch(ids);
        return ResponseEntity.ok("batch delete success!");
    }

    @LogPrint(value = "批量导入商品")
    @ApiOperation(value = "批量导入商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页数据数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @PostMapping(value = "/import/{current}/{size}")
    public ResponseEntity<String> importBatch(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        solrGoodsService.importBatch(current, size);
        return ResponseEntity.ok("batch import success!");
    }
}
