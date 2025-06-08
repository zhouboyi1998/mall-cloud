package com.cafe.meilisearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.meilisearch.model.index.GoodsIndex;
import com.cafe.meilisearch.service.GoodsIndexService;
import com.meilisearch.sdk.model.TaskInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * @Package: com.cafe.meilisearch.controller
 * @Author: zhouboyi
 * @Date: 2025/6/5 22:16
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
    public ResponseEntity<TaskInfo> save(@RequestBody GoodsIndex goodsIndex) {
        TaskInfo taskInfo = goodsIndexService.save(goodsIndex);
        return ResponseEntity.ok(taskInfo);
    }

    @ApiLogPrint(value = "批量保存商品索引")
    @ApiOperation(value = "批量保存商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<List<TaskInfo>> saveBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        List<TaskInfo> taskInfoList = goodsIndexService.saveBatch(goodsIndexList);
        return ResponseEntity.ok(taskInfoList);
    }

    @ApiLogPrint(value = "修改商品索引")
    @ApiOperation(value = "修改商品索引")
    @ApiImplicitParam(value = "商品索引", name = "goodsIndex", dataType = "GoodsIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<TaskInfo> update(@RequestBody GoodsIndex goodsIndex) {
        TaskInfo taskInfo = goodsIndexService.update(goodsIndex);
        return ResponseEntity.ok(taskInfo);
    }

    @ApiLogPrint(value = "批量修改商品索引")
    @ApiOperation(value = "批量修改商品索引")
    @ApiImplicitParam(value = "商品索引列表", name = "goodsIndexList", dataType = "List<GoodsIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<List<TaskInfo>> updateBatch(@RequestBody List<GoodsIndex> goodsIndexList) {
        List<TaskInfo> taskInfoList = goodsIndexService.updateBatch(goodsIndexList);
        return ResponseEntity.ok(taskInfoList);
    }

    @ApiLogPrint(value = "删除商品索引")
    @ApiOperation(value = "删除商品索引")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TaskInfo> delete(@PathVariable(value = "id") Long id) {
        TaskInfo taskInfo = goodsIndexService.delete(id);
        return ResponseEntity.ok(taskInfo);
    }

    @ApiLogPrint(value = "批量删除商品索引")
    @ApiOperation(value = "批量删除商品索引")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<List<TaskInfo>> deleteBatch(@RequestBody List<Long> ids) {
        List<TaskInfo> taskInfoList = goodsIndexService.deleteBatch(ids);
        return ResponseEntity.ok(taskInfoList);
    }
}
