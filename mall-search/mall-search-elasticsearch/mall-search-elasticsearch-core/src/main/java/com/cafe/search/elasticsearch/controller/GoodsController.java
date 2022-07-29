package com.cafe.search.elasticsearch.controller;

import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.search.elasticsearch.constant.ElasticSearchConstant;
import com.cafe.search.elasticsearch.service.GoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:17
 * @Description:
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "批量插入商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<SkuElasticSearchDTO> dtoList) throws IOException {
        return ResponseEntity.ok(goodsService.insertBatch(dtoList));
    }

    @ApiOperation(value = "批量更新商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<SkuElasticSearchDTO> dtoList) throws IOException {
        return ResponseEntity.ok(goodsService.updateBatch(dtoList));
    }

    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(name = "esIds", value = "ElasticSearch ID列表", required = true, paramType = "body", dataType = "List<String>")
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> esIds) throws IOException {
        return ResponseEntity.ok(goodsService.deleteBatch(esIds));
    }

    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "size", value = "每页数据数量", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "keyword", value = "关键词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sort", value = "排序属性", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "rule", value = "排序规则", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping(value = "/search/{current}/{size}")
    public ResponseEntity<SearchResponse> search(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT) String sort,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_RULE) String rule
    ) throws IOException {
        return ResponseEntity.ok(goodsService.search(current, size, keyword, sort, rule));
    }

    @ApiOperation(value = "批量导入商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页数据数量", required = true, paramType = "path", dataType = "Long")
    })
    @PostMapping(value = "/import/{current}/{size}")
    public ResponseEntity<BulkResponse> importBatch(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) throws IOException {
        return ResponseEntity.ok(goodsService.importBatch(current, size));
    }
}
