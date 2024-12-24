package com.cafe.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.storage.model.entity.StockItem;
import com.cafe.storage.service.StockItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @Package: com.cafe.storage.controller
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 库存明细接口
 */
@Api(value = "库存明细接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/stock-item")
public class StockItemController {

    private final StockItemService stockItemService;

    @ApiLogPrint(value = "查询库存明细数量")
    @ApiOperation(value = "查询库存明细数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = stockItemService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询库存明细数量")
    @ApiOperation(value = "根据条件查询库存明细数量")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody StockItem stockItem) {
        QueryWrapper<StockItem> wrapper = WrapperUtil.createQueryWrapper(stockItem);
        Integer count = stockItemService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询库存明细列表")
    @ApiOperation(value = "查询库存明细列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<StockItem>> list() {
        List<StockItem> stockItemList = stockItemService.list();
        return ResponseEntity.ok(stockItemList);
    }

    @ApiLogPrint(value = "根据条件查询库存明细列表")
    @ApiOperation(value = "根据条件查询库存明细列表")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<StockItem>> list(@RequestBody StockItem stockItem) {
        QueryWrapper<StockItem> wrapper = WrapperUtil.createQueryWrapper(stockItem);
        List<StockItem> stockItemList = stockItemService.list(wrapper);
        return ResponseEntity.ok(stockItemList);
    }

    @ApiLogPrint(value = "分页查询库存明细列表")
    @ApiOperation(value = "分页查询库存明细列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<StockItem>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<StockItem> page = new Page<>(current, size);
        Page<StockItem> stockItemPage = stockItemService.page(page);
        return ResponseEntity.ok(stockItemPage);
    }

    @ApiLogPrint(value = "根据条件分页查询库存明细")
    @ApiOperation(value = "根据条件分页查询库存明细")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<StockItem>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody StockItem stockItem
    ) {
        Page<StockItem> page = new Page<>(current, size);
        QueryWrapper<StockItem> wrapper = WrapperUtil.createQueryWrapper(stockItem);
        Page<StockItem> stockItemPage = stockItemService.page(page, wrapper);
        return ResponseEntity.ok(stockItemPage);
    }

    @ApiLogPrint(value = "根据id查询单个库存明细")
    @ApiOperation(value = "根据id查询单个库存明细")
    @ApiImplicitParam(value = "库存明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<StockItem> one(@PathVariable(value = "id") Long id) {
        StockItem stockItem = stockItemService.getById(id);
        return ResponseEntity.ok(stockItem);
    }

    @ApiLogPrint(value = "根据条件查询单个库存明细")
    @ApiOperation(value = "根据条件查询单个库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<StockItem> one(@RequestBody StockItem stockItem) {
        QueryWrapper<StockItem> wrapper = WrapperUtil.createQueryWrapper(stockItem);
        StockItem one = stockItemService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增库存明细")
    @ApiOperation(value = "新增库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody StockItem stockItem) {
        Boolean code = stockItemService.save(stockItem);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增库存明细")
    @ApiOperation(value = "批量新增库存明细")
    @ApiImplicitParam(value = "库存明细列表", name = "stockItemList", dataType = "List<StockItem>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<StockItem> stockItemList) {
        Boolean code = stockItemService.saveBatch(stockItemList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改库存明细")
    @ApiOperation(value = "根据id修改库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody StockItem stockItem) {
        Boolean code = stockItemService.updateById(stockItem);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改库存明细")
    @ApiOperation(value = "根据ids批量修改库存明细")
    @ApiImplicitParam(value = "库存明细列表", name = "stockItemList", dataType = "List<StockItem>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<StockItem> stockItemList) {
        Boolean code = stockItemService.updateBatchById(stockItemList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除库存明细")
    @ApiOperation(value = "根据id删除库存明细")
    @ApiImplicitParam(value = "库存明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = stockItemService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除库存明细")
    @ApiOperation(value = "根据ids批量删除库存明细")
    @ApiImplicitParam(value = "库存明细id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = stockItemService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除库存明细")
    @ApiOperation(value = "根据条件批量删除库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockItem", dataType = "StockItem", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody StockItem stockItem) {
        QueryWrapper<StockItem> wrapper = WrapperUtil.createQueryWrapper(stockItem);
        Boolean code = stockItemService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
