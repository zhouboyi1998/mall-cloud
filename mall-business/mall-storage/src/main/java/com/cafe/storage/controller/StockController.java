package com.cafe.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.storage.model.Stock;
import com.cafe.storage.service.StockService;
import com.cafe.storage.vo.CartVO;
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
 * @Date: 2022-12-29
 * @Description: 库存接口
 */
@Api(value = "库存接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/stock")
public class StockController {

    private final StockService stockService;

    @ApiLogPrint(value = "查询库存数量")
    @ApiOperation(value = "查询库存数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = stockService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询库存数量")
    @ApiOperation(value = "根据条件查询库存数量")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Stock stock) {
        QueryWrapper<Stock> wrapper = WrapperUtil.createQueryWrapper(stock);
        Integer count = stockService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询库存列表")
    @ApiOperation(value = "查询库存列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Stock>> list() {
        List<Stock> stockList = stockService.list();
        return ResponseEntity.ok(stockList);
    }

    @ApiLogPrint(value = "根据条件查询库存列表")
    @ApiOperation(value = "根据条件查询库存列表")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Stock>> list(@RequestBody Stock stock) {
        QueryWrapper<Stock> wrapper = WrapperUtil.createQueryWrapper(stock);
        List<Stock> stockList = stockService.list(wrapper);
        return ResponseEntity.ok(stockList);
    }

    @ApiLogPrint(value = "分页查询库存列表")
    @ApiOperation(value = "分页查询库存列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Stock>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Stock> page = new Page<>(current, size);
        Page<Stock> stockPage = stockService.page(page);
        return ResponseEntity.ok(stockPage);
    }

    @ApiLogPrint(value = "根据条件分页查询库存")
    @ApiOperation(value = "根据条件分页查询库存")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Stock>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Stock stock
    ) {
        Page<Stock> page = new Page<>(current, size);
        QueryWrapper<Stock> wrapper = WrapperUtil.createQueryWrapper(stock);
        Page<Stock> stockPage = stockService.page(page, wrapper);
        return ResponseEntity.ok(stockPage);
    }

    @ApiLogPrint(value = "根据id查询单个库存")
    @ApiOperation(value = "根据id查询单个库存")
    @ApiImplicitParam(value = "库存id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Stock> one(@PathVariable(value = "id") Long id) {
        Stock stock = stockService.getById(id);
        return ResponseEntity.ok(stock);
    }

    @ApiLogPrint(value = "根据条件查询单个库存")
    @ApiOperation(value = "根据条件查询单个库存")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Stock> one(@RequestBody Stock stock) {
        QueryWrapper<Stock> wrapper = WrapperUtil.createQueryWrapper(stock);
        Stock one = stockService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增库存")
    @ApiOperation(value = "新增库存")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Stock stock) {
        Boolean code = stockService.save(stock);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增库存")
    @ApiOperation(value = "批量新增库存")
    @ApiImplicitParam(value = "库存列表", name = "stockList", dataType = "List<Stock>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Stock> stockList) {
        Boolean code = stockService.saveBatch(stockList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改库存")
    @ApiOperation(value = "根据id修改库存")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Stock stock) {
        Boolean code = stockService.updateById(stock);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改库存")
    @ApiOperation(value = "根据ids批量修改库存")
    @ApiImplicitParam(value = "库存列表", name = "stockList", dataType = "List<Stock>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Stock> stockList) {
        Boolean code = stockService.updateBatchById(stockList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除库存")
    @ApiOperation(value = "根据id删除库存")
    @ApiImplicitParam(value = "库存id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = stockService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除库存")
    @ApiOperation(value = "根据ids批量删除库存")
    @ApiImplicitParam(value = "库存id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = stockService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除库存")
    @ApiOperation(value = "根据条件批量删除库存")
    @ApiImplicitParam(value = "库存Model", name = "stock", dataType = "Stock", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Stock stock) {
        QueryWrapper<Stock> wrapper = WrapperUtil.createQueryWrapper(stock);
        Boolean code = stockService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量入库")
    @ApiOperation(value = "批量入库")
    @ApiImplicitParam(value = "购物车视图模型列表", name = "cartVOList", dataType = "List<CartVO>", paramType = "body", required = true)
    @PutMapping(value = "/inbound/batch")
    public ResponseEntity<Void> inboundBatch(@RequestBody List<CartVO> cartVOList) {
        stockService.inboundBatch(cartVOList);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "批量出库")
    @ApiOperation(value = "批量出库")
    @ApiImplicitParam(value = "购物车视图模型列表", name = "cartVOList", dataType = "List<CartVO>", paramType = "body", required = true)
    @PutMapping(value = "/outbound/batch")
    public ResponseEntity<List<String>> outboundBatch(@RequestBody List<CartVO> cartVOList) {
        List<String> failIds = stockService.outboundBatch(cartVOList);
        return ResponseEntity.ok(failIds);
    }
}
