package com.cafe.elasticsearch.controller;

import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.database.DatabaseConstant;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.jackson.util.JacksonUtil;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.elasticsearch.service.GoodsIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:17
 * @Description: 商品全文索引接口
 */
@Api(value = "商品全文索引接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/goods-index")
public class GoodsIndexController {

    private final GoodsIndexService goodsIndexService;

    @ApiLogPrint(value = "获取商品索引")
    @ApiOperation(value = "获取商品索引")
    @ApiImplicitParam(value = "商品ID", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GoodsIndex> one(@PathVariable(value = "id") Long id) {
        GoodsIndex goodsIndex = goodsIndexService.one(id);
        return ResponseEntity.ok(goodsIndex);
    }

    @ApiLogPrint(value = "插入商品索引")
    @ApiOperation(value = "插入商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<GoodsIndex> insert(@RequestBody GoodsIndex goodsIndex) {
        GoodsIndex savedGoodsIndex = goodsIndexService.insert(goodsIndex);
        return ResponseEntity.ok(savedGoodsIndex);
    }

    @ApiLogPrint(value = "更新商品索引")
    @ApiOperation(value = "更新商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<GoodsIndex> update(@RequestBody GoodsIndex goodsIndex) {
        GoodsIndex updatedGoodsIndex = goodsIndexService.update(goodsIndex);
        return ResponseEntity.ok(updatedGoodsIndex);
    }

    @ApiLogPrint(value = "删除商品索引")
    @ApiOperation(value = "删除商品索引")
    @ApiImplicitParam(value = "商品ID", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        goodsIndexService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "批量插入商品索引")
    @ApiOperation(value = "批量插入商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<List<GoodsIndex>> insertBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        List<GoodsIndex> savedGoodsIndexList = goodsIndexService.insertBatch(goodsIndexList);
        return ResponseEntity.ok(savedGoodsIndexList);
    }

    @ApiLogPrint(value = "批量更新商品索引")
    @ApiOperation(value = "批量更新商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<List<GoodsIndex>> updateBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        List<GoodsIndex> updatedGoodsIndexList = goodsIndexService.updateBatch(goodsIndexList);
        return ResponseEntity.ok(updatedGoodsIndexList);
    }

    @ApiLogPrint(value = "批量删除商品索引")
    @ApiOperation(value = "批量删除商品索引")
    @ApiImplicitParam(value = "商品ID列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<Long> ids) {
        goodsIndexService.deleteBatch(ids);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "根据关键词查询商品索引列表")
    @ApiImplicitParam(name = "keyword", value = "关键词", required = false, dataType = "String")
    @GetMapping("/list")
    public ResponseEntity<List<GoodsIndex>> list(@RequestParam(required = false) String keyword) {
        List<GoodsIndex> goodsIndexList = goodsIndexService.list(keyword);
        return ResponseEntity.ok(goodsIndexList);
    }

    @ApiLogPrint(value = "分页查询商品索引")
    @ApiOperation(value = "分页查询商品索引")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页数量", name = "size", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序字段", name = "sort", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序规则", name = "rule", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<GoodsIndex>> page(
        @RequestParam(value = "current") Integer current,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "sort") String sort,
        @RequestParam(value = "rule") String rule
    ) {
        Page<GoodsIndex> page = goodsIndexService.page(current, size, keyword, sort, rule);
        return ResponseEntity.ok(page);
    }

    @ApiLogPrint(value = "搜索商品索引")
    @ApiOperation(value = "搜索商品索引")
    @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true)
    @GetMapping(value = "/search/index")
    public ResponseEntity<List<GoodsIndex>> searchIndex(@RequestParam(value = "keyword") String keyword) {
        List<GoodsIndex> goodsIndexList = goodsIndexService.list(keyword);
        return ResponseEntity.ok(goodsIndexList);
    }

    @ApiLogPrint(value = "搜索商品ID")
    @ApiOperation(value = "搜索商品ID")
    @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true)
    @GetMapping(value = "/search/id")
    public ResponseEntity<List<Long>> searchId(@RequestParam(value = "keyword") String keyword) {
        List<GoodsIndex> goodsIndexList = goodsIndexService.list(keyword);
        List<Long> ids = goodsIndexList.stream()
            .map(GoodsIndex::getId)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ids);
    }
}
