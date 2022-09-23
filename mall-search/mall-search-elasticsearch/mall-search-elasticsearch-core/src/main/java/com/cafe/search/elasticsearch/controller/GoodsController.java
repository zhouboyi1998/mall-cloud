package com.cafe.search.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.dto.SkuElasticSearchDTO;
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

    @LogPrint(description = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(name = "_id", value = "ElasticSearch ID", required = true, paramType = "path", dataType = "String")
    @GetMapping(value = "/{_id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "_id") String _id) throws IOException {
        return ResponseEntity.ok(goodsService.one(_id));
    }

    @LogPrint(description = "插入商品")
    @ApiOperation(value = "插入商品")
    @ApiImplicitParam(name = "dto", value = "商品", required = true, paramType = "body", dataType = "SkuElasticSearchDTO")
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody SkuElasticSearchDTO dto) throws IOException {
        return ResponseEntity.ok(goodsService.insert(dto));
    }

    @LogPrint(description = "更新商品")
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(name = "dto", value = "商品", required = true, paramType = "body", dataType = "SkuElasticSearchDTO")
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody SkuElasticSearchDTO dto) throws IOException {
        return ResponseEntity.ok(goodsService.update(dto));
    }

    @LogPrint(description = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "_id", value = "ElasticSearch ID", required = true, paramType = "path", dataType = "String")
    @DeleteMapping(value = "/{_id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "_id") String _id) throws IOException {
        return ResponseEntity.ok(goodsService.delete(_id));
    }

    @LogPrint(description = "批量插入商品")
    @ApiOperation(value = "批量插入商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<SkuElasticSearchDTO> dtoList) throws IOException {
        return ResponseEntity.ok(goodsService.insertBatch(dtoList));
    }

    @LogPrint(description = "批量更新商品")
    @ApiOperation(value = "批量更新商品")
    @ApiImplicitParam(name = "dtoList", value = "商品列表", required = true, paramType = "body", dataType = "List<SkuElasticSearchDTO>")
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<SkuElasticSearchDTO> dtoList) throws IOException {
        return ResponseEntity.ok(goodsService.updateBatch(dtoList));
    }

    @LogPrint(description = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(name = "_ids", value = "ElasticSearch ID List", required = true, paramType = "body", dataType = "List<String>")
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> _ids) throws IOException {
        return ResponseEntity.ok(goodsService.deleteBatch(_ids));
    }

    @LogPrint(description = "分页查询商品")
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
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT) String sort,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_RULE) String rule
    ) throws IOException {
        return ResponseEntity.ok(goodsService.page(current, size, keyword, sort, rule));
    }

    @LogPrint(description = "批量导入商品")
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
