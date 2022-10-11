package com.cafe.search.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.bo.GoodsBO;
import com.cafe.search.elasticsearch.constant.ElasticSearchConstant;
import com.cafe.search.elasticsearch.service.GoodsService;
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
 * @Description: 商品接口
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @LogPrint(value = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(name = "_id", value = "ElasticSearch ID", required = true, paramType = "path", dataType = "String")
    @GetMapping(value = "/{_id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "_id") String _id) throws IOException {
        GetResponse getResponse = goodsService.one(_id);
        return ResponseEntity.ok(getResponse);
    }

    @LogPrint(value = "插入商品")
    @ApiOperation(value = "插入商品")
    @ApiImplicitParam(name = "dto", value = "商品", required = true, paramType = "body", dataType = "GoodsBO")
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody GoodsBO goodsBO) throws IOException {
        IndexResponse indexResponse = goodsService.insert(goodsBO);
        return ResponseEntity.ok(indexResponse);
    }

    @LogPrint(value = "更新商品")
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(name = "dto", value = "商品", required = true, paramType = "body", dataType = "GoodsBO")
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody GoodsBO goodsBO) throws IOException {
        UpdateResponse updateResponse = goodsService.update(goodsBO);
        return ResponseEntity.ok(updateResponse);
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "_id", value = "ElasticSearch ID", required = true, paramType = "path", dataType = "String")
    @DeleteMapping(value = "/{_id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "_id") String _id) throws IOException {
        DeleteResponse deleteResponse = goodsService.delete(_id);
        return ResponseEntity.ok(deleteResponse);
    }

    @LogPrint(value = "批量插入商品")
    @ApiOperation(value = "批量插入商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<GoodsBO> goodsBOList) throws IOException {
        BulkResponse bulkResponse = goodsService.insertBatch(goodsBOList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量更新商品")
    @ApiOperation(value = "批量更新商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<GoodsBO> goodsBOList) throws IOException {
        BulkResponse bulkResponse = goodsService.updateBatch(goodsBOList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(name = "_ids", value = "ElasticSearch ID List", required = true, paramType = "body", dataType = "List<String>")
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> _ids) throws IOException {
        BulkResponse bulkResponse = goodsService.deleteBatch(_ids);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "分页查询商品")
    @ApiOperation(value = "分页查询商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "size", value = "每页数据数量", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "keyword", value = "关键词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sort", value = "排序属性", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "rule", value = "排序规则", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<SearchResponse> page(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "sort", required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT) String sort,
        @RequestParam(value = "rule", required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_RULE) String rule
    ) throws IOException {
        SearchResponse searchResponse = goodsService.page(current, size, keyword, sort, rule);
        return ResponseEntity.ok(searchResponse);
    }

    @LogPrint(value = "批量导入商品")
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
        BulkResponse bulkResponse = goodsService.importBatch(current, size);
        return ResponseEntity.ok(bulkResponse);
    }
}
