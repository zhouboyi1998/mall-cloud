package com.cafe.opensearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.opensearch.model.index.GoodsIndex;
import com.cafe.opensearch.service.GoodsIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.update.UpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.controller
 * @Author: zhouboyi
 * @Date: 2025/6/16 21:06
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
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GoodsIndex> one(@PathVariable(value = "id") Long id) {
        GoodsIndex goodsIndex = goodsIndexService.one(id);
        return ResponseEntity.ok(goodsIndex);
    }

    @ApiLogPrint(value = "保存商品索引")
    @ApiOperation(value = "保存商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> save(@RequestBody GoodsIndex goodsIndex) {
        IndexResponse indexResponse = goodsIndexService.save(goodsIndex);
        return ResponseEntity.ok(indexResponse);
    }

    @ApiLogPrint(value = "批量保存商品索引")
    @ApiOperation(value = "批量保存商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> saveBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        BulkResponse bulkResponse = goodsIndexService.saveBatch(goodsIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "修改商品索引")
    @ApiOperation(value = "修改商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody GoodsIndex goodsIndex) {
        UpdateResponse updateResponse = goodsIndexService.update(goodsIndex);
        return ResponseEntity.ok(updateResponse);
    }

    @ApiLogPrint(value = "批量修改商品索引")
    @ApiOperation(value = "批量修改商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        BulkResponse bulkResponse = goodsIndexService.updateBatch(goodsIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "删除商品索引")
    @ApiOperation(value = "删除商品索引")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "id") Long id) {
        DeleteResponse deleteResponse = goodsIndexService.delete(id);
        return ResponseEntity.ok(deleteResponse);
    }

    @ApiLogPrint(value = "批量删除商品索引")
    @ApiOperation(value = "批量删除商品索引")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<Long> ids) {
        BulkResponse bulkResponse = goodsIndexService.deleteBatch(ids);
        return ResponseEntity.ok(bulkResponse);
    }
}
