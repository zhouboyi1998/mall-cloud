package com.cafe.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.storage.model.StockDetail;
import com.cafe.storage.service.StockDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping(value = "/stock-detail")
public class StockDetailController {

    private final StockDetailService stockDetailService;

    @Autowired
    public StockDetailController(StockDetailService stockDetailService) {
        this.stockDetailService = stockDetailService;
    }

    @ApiLogPrint(value = "查询库存明细数量")
    @ApiOperation(value = "查询库存明细数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = stockDetailService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询库存明细数量")
    @ApiOperation(value = "根据条件查询库存明细数量")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody StockDetail stockDetail) {
        QueryWrapper<StockDetail> wrapper = WrapperUtil.createQueryWrapper(stockDetail);
        Integer count = stockDetailService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询库存明细列表")
    @ApiOperation(value = "查询库存明细列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<StockDetail>> list() {
        List<StockDetail> stockDetailList = stockDetailService.list();
        return ResponseEntity.ok(stockDetailList);
    }

    @ApiLogPrint(value = "根据条件查询库存明细列表")
    @ApiOperation(value = "根据条件查询库存明细列表")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<StockDetail>> list(@RequestBody StockDetail stockDetail) {
        QueryWrapper<StockDetail> wrapper = WrapperUtil.createQueryWrapper(stockDetail);
        List<StockDetail> stockDetailList = stockDetailService.list(wrapper);
        return ResponseEntity.ok(stockDetailList);
    }

    @ApiLogPrint(value = "分页查询库存明细列表")
    @ApiOperation(value = "分页查询库存明细列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<StockDetail>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<StockDetail> page = new Page<>(current, size);
        Page<StockDetail> stockDetailPage = stockDetailService.page(page);
        return ResponseEntity.ok(stockDetailPage);
    }

    @ApiLogPrint(value = "根据条件分页查询库存明细")
    @ApiOperation(value = "根据条件分页查询库存明细")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<StockDetail>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody StockDetail stockDetail
    ) {
        Page<StockDetail> page = new Page<>(current, size);
        QueryWrapper<StockDetail> wrapper = WrapperUtil.createQueryWrapper(stockDetail);
        Page<StockDetail> stockDetailPage = stockDetailService.page(page, wrapper);
        return ResponseEntity.ok(stockDetailPage);
    }

    @ApiLogPrint(value = "根据id查询单个库存明细")
    @ApiOperation(value = "根据id查询单个库存明细")
    @ApiImplicitParam(value = "库存明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<StockDetail> one(@PathVariable(value = "id") Long id) {
        StockDetail stockDetail = stockDetailService.getById(id);
        return ResponseEntity.ok(stockDetail);
    }

    @ApiLogPrint(value = "根据条件查询单个库存明细")
    @ApiOperation(value = "根据条件查询单个库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<StockDetail> one(@RequestBody StockDetail stockDetail) {
        QueryWrapper<StockDetail> wrapper = WrapperUtil.createQueryWrapper(stockDetail);
        StockDetail one = stockDetailService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增库存明细")
    @ApiOperation(value = "新增库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody StockDetail stockDetail) {
        Boolean code = stockDetailService.save(stockDetail);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增库存明细")
    @ApiOperation(value = "批量新增库存明细")
    @ApiImplicitParam(value = "库存明细列表", name = "stockDetailList", dataType = "List<StockDetail>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<StockDetail> stockDetailList) {
        Boolean code = stockDetailService.saveBatch(stockDetailList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改库存明细")
    @ApiOperation(value = "根据id修改库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody StockDetail stockDetail) {
        Boolean code = stockDetailService.updateById(stockDetail);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改库存明细")
    @ApiOperation(value = "根据ids批量修改库存明细")
    @ApiImplicitParam(value = "库存明细列表", name = "stockDetailList", dataType = "List<StockDetail>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<StockDetail> stockDetailList) {
        Boolean code = stockDetailService.updateBatchById(stockDetailList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除库存明细")
    @ApiOperation(value = "根据id删除库存明细")
    @ApiImplicitParam(value = "库存明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = stockDetailService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除库存明细")
    @ApiOperation(value = "根据ids批量删除库存明细")
    @ApiImplicitParam(value = "库存明细id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = stockDetailService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除库存明细")
    @ApiOperation(value = "根据条件批量删除库存明细")
    @ApiImplicitParam(value = "库存明细Model", name = "stockDetail", dataType = "StockDetail", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody StockDetail stockDetail) {
        QueryWrapper<StockDetail> wrapper = WrapperUtil.createQueryWrapper(stockDetail);
        Boolean code = stockDetailService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
