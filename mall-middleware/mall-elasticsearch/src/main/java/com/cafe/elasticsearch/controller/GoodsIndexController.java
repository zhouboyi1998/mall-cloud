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
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
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
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "id") String id) {
        GetResponse getResponse = goodsIndexService.one(id);
        return ResponseEntity.ok(getResponse);
    }

    @ApiLogPrint(value = "插入商品索引")
    @ApiOperation(value = "插入商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody GoodsIndex goodsIndex) {
        IndexResponse indexResponse = goodsIndexService.insert(goodsIndex);
        return ResponseEntity.ok(indexResponse);
    }

    @ApiLogPrint(value = "更新商品索引")
    @ApiOperation(value = "更新商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody GoodsIndex goodsIndex) {
        UpdateResponse updateResponse = goodsIndexService.update(goodsIndex);
        return ResponseEntity.ok(updateResponse);
    }

    @ApiLogPrint(value = "删除商品索引")
    @ApiOperation(value = "删除商品索引")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "id") String id) {
        DeleteResponse deleteResponse = goodsIndexService.delete(id);
        return ResponseEntity.ok(deleteResponse);
    }

    @ApiLogPrint(value = "批量插入商品索引")
    @ApiOperation(value = "批量插入商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        BulkResponse bulkResponse = goodsIndexService.insertBatch(goodsIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "批量更新商品索引")
    @ApiOperation(value = "批量更新商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        BulkResponse bulkResponse = goodsIndexService.updateBatch(goodsIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "批量删除商品索引")
    @ApiOperation(value = "批量删除商品索引")
    @ApiImplicitParam(value = "ElasticSearch ids", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> ids) {
        BulkResponse bulkResponse = goodsIndexService.deleteBatch(ids);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "分页查询商品索引")
    @ApiOperation(value = "分页查询商品索引")
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
        @RequestParam(value = "sort", required = false, defaultValue = ElasticSearchConstant.INDEX_ID) String sort,
        @RequestParam(value = "rule", required = false, defaultValue = DatabaseConstant.Rule.ASC) String rule
    ) {
        SearchResponse searchResponse = goodsIndexService.page(current, size, keyword, sort, rule);
        return ResponseEntity.ok(searchResponse);
    }

    @ApiLogPrint(value = "搜索商品索引")
    @ApiOperation(value = "搜索商品索引")
    @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true)
    @GetMapping(value = "/search/index")
    public ResponseEntity<List<GoodsIndex>> searchIndex(@RequestParam(value = "keyword") String keyword) {
        // 查询商品索引
        SearchResponse searchResponse = goodsIndexService.list(keyword);
        // 获取搜索命中结果数组
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        // 转换成商品索引列表
        List<GoodsIndex> goodsIndexList = Arrays.stream(searchHits)
            .map(SearchHit::getSourceAsMap)
            .map(sourceAsMap -> JacksonUtil.convertValue(sourceAsMap, GoodsIndex.class))
            .collect(Collectors.toList());
        return ResponseEntity.ok(goodsIndexList);
    }

    @ApiLogPrint(value = "搜索商品ID")
    @ApiOperation(value = "搜索商品ID")
    @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true)
    @GetMapping(value = "/search/id")
    public ResponseEntity<List<Long>> searchId(@RequestParam(value = "keyword") String keyword) {
        // 查询商品索引
        SearchResponse searchResponse = goodsIndexService.list(keyword);
        // 获取搜索命中结果数组
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        // 转换成商品ID列表
        List<Long> ids = Arrays.stream(searchHits)
            .map(SearchHit::getSourceAsMap)
            .map(sourceAsMap -> sourceAsMap.get(FieldConstant.ID))
            .map(String::valueOf)
            .map(Long::valueOf)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ids);
    }
}
