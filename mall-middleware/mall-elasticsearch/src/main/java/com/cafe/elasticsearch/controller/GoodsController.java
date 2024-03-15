package com.cafe.elasticsearch.controller;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.elasticsearch.model.Goods;
import com.cafe.elasticsearch.service.GoodsService;
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
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:17
 * @Description: ElasticSearch 商品接口
 */
@Api(value = "ElasticSearch 商品接口")
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @LogPrint(value = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "id") String id) throws IOException {
        GetResponse getResponse = goodsService.one(id);
        return ResponseEntity.ok(getResponse);
    }

    @LogPrint(value = "插入商品")
    @ApiOperation(value = "插入商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody Goods goods) throws IOException {
        IndexResponse indexResponse = goodsService.insert(goods);
        return ResponseEntity.ok(indexResponse);
    }

    @LogPrint(value = "更新商品")
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody Goods goods) throws IOException {
        UpdateResponse updateResponse = goodsService.update(goods);
        return ResponseEntity.ok(updateResponse);
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "id") String id) throws IOException {
        DeleteResponse deleteResponse = goodsService.delete(id);
        return ResponseEntity.ok(deleteResponse);
    }

    @LogPrint(value = "批量插入商品")
    @ApiOperation(value = "批量插入商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<Goods> goodsList) throws IOException {
        BulkResponse bulkResponse = goodsService.insertBatch(goodsList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量更新商品")
    @ApiOperation(value = "批量更新商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<Goods> goodsList) throws IOException {
        BulkResponse bulkResponse = goodsService.updateBatch(goodsList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(value = "ElasticSearch ids", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> ids) throws IOException {
        BulkResponse bulkResponse = goodsService.deleteBatch(ids);
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
        @RequestParam(value = "sort", required = false, defaultValue = ElasticSearchConstant.DEFAULT_SORT) String sort,
        @RequestParam(value = "rule", required = false, defaultValue = ElasticSearchConstant.DEFAULT_RULE) String rule
    ) throws IOException {
        SearchResponse searchResponse = goodsService.page(current, size, keyword, sort, rule);
        return ResponseEntity.ok(searchResponse);
    }
}
