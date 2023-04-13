package com.cafe.search.elasticsearch.controller;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.search.elasticsearch.model.Goods;
import com.cafe.search.elasticsearch.service.ElasticSearchGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:17
 * @Description: ElasticSearch 商品接口
 */
@Api(value = "ElasticSearch 商品接口")
@RestController
@RequestMapping(value = "/search/goods")
public class ElasticSearchGoodsController {

    private final ElasticSearchGoodsService elasticSearchGoodsService;

    @Autowired
    public ElasticSearchGoodsController(ElasticSearchGoodsService elasticSearchGoodsService) {
        this.elasticSearchGoodsService = elasticSearchGoodsService;
    }

    @LogPrint(value = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(value = "ElasticSearch id", name = "esId", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{esId}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "esId") String esId) throws IOException {
        GetResponse getResponse = elasticSearchGoodsService.one(esId);
        return ResponseEntity.ok(getResponse);
    }

    @LogPrint(value = "插入商品")
    @ApiOperation(value = "插入商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody Goods goods) throws IOException {
        IndexResponse indexResponse = elasticSearchGoodsService.insert(goods);
        return ResponseEntity.ok(indexResponse);
    }

    @LogPrint(value = "更新商品")
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody Goods goods) throws IOException {
        UpdateResponse updateResponse = elasticSearchGoodsService.update(goods);
        return ResponseEntity.ok(updateResponse);
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(value = "ElasticSearch id", name = "esId", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{esId}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "esId") String esId) throws IOException {
        DeleteResponse deleteResponse = elasticSearchGoodsService.delete(esId);
        return ResponseEntity.ok(deleteResponse);
    }

    @LogPrint(value = "批量插入商品")
    @ApiOperation(value = "批量插入商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<Goods> goodsList) throws IOException {
        BulkResponse bulkResponse = elasticSearchGoodsService.insertBatch(goodsList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量更新商品")
    @ApiOperation(value = "批量更新商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<Goods> goodsList) throws IOException {
        BulkResponse bulkResponse = elasticSearchGoodsService.updateBatch(goodsList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(value = "ElasticSearch id列表", name = "esIds", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> esIds) throws IOException {
        BulkResponse bulkResponse = elasticSearchGoodsService.deleteBatch(esIds);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "分页查询商品")
    @ApiOperation(value = "分页查询商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页数据数量", name = "size", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query"),
        @ApiImplicitParam(value = "排序属性", name = "sort", dataType = "String", paramType = "query"),
        @ApiImplicitParam(value = "排序规则", name = "rule", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<SearchResponse> page(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "sort", required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT) String sort,
        @RequestParam(value = "rule", required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_RULE) String rule
    ) throws IOException {
        SearchResponse searchResponse = elasticSearchGoodsService.page(current, size, keyword, sort, rule);
        return ResponseEntity.ok(searchResponse);
    }
}
