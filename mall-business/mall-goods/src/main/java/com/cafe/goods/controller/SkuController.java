package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goods.model.entity.Sku;
import com.cafe.goods.service.SkuService;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
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
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位接口
 */
@Api(value = "库存量单位接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sku")
public class SkuController {

    private final SkuService skuService;

    @ApiLogPrint(value = "查询库存量单位数量")
    @ApiOperation(value = "查询库存量单位数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = skuService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询库存量单位数量")
    @ApiOperation(value = "根据条件查询库存量单位数量")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Sku sku) {
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(sku);
        Integer count = skuService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询库存量单位列表")
    @ApiOperation(value = "查询库存量单位列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Sku>> list() {
        List<Sku> skuList = skuService.list();
        return ResponseEntity.ok(skuList);
    }

    @ApiLogPrint(value = "根据条件查询库存量单位列表")
    @ApiOperation(value = "根据条件查询库存量单位列表")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Sku>> list(@RequestBody Sku sku) {
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(sku);
        List<Sku> skuList = skuService.list(wrapper);
        return ResponseEntity.ok(skuList);
    }

    @ApiLogPrint(value = "分页查询库存量单位列表")
    @ApiOperation(value = "分页查询库存量单位列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sku>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Sku> page = new Page<>(current, size);
        Page<Sku> skuPage = skuService.page(page);
        return ResponseEntity.ok(skuPage);
    }

    @ApiLogPrint(value = "根据条件分页查询库存量单位")
    @ApiOperation(value = "根据条件分页查询库存量单位")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sku>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Sku sku
    ) {
        Page<Sku> page = new Page<>(current, size);
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(sku);
        Page<Sku> skuPage = skuService.page(page, wrapper);
        return ResponseEntity.ok(skuPage);
    }

    @ApiLogPrint(value = "根据id查询单个库存量单位")
    @ApiOperation(value = "根据id查询单个库存量单位")
    @ApiImplicitParam(value = "库存量单位id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Sku> one(@PathVariable(value = "id") Long id) {
        Sku sku = skuService.getById(id);
        return ResponseEntity.ok(sku);
    }

    @ApiLogPrint(value = "根据条件查询单个库存量单位")
    @ApiOperation(value = "根据条件查询单个库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Sku> one(@RequestBody Sku sku) {
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(sku);
        Sku one = skuService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增库存量单位")
    @ApiOperation(value = "新增库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Sku sku) {
        Boolean code = skuService.save(sku);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增库存量单位")
    @ApiOperation(value = "批量新增库存量单位")
    @ApiImplicitParam(value = "库存量单位列表", name = "skuList", dataType = "List<Sku>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Sku> skuList) {
        Boolean code = skuService.saveBatch(skuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改库存量单位")
    @ApiOperation(value = "根据id修改库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Sku sku) {
        Boolean code = skuService.updateById(sku);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改库存量单位")
    @ApiOperation(value = "根据ids批量修改库存量单位")
    @ApiImplicitParam(value = "库存量单位列表", name = "skuList", dataType = "List<Sku>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Sku> skuList) {
        Boolean code = skuService.updateBatchById(skuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除库存量单位")
    @ApiOperation(value = "根据id删除库存量单位")
    @ApiImplicitParam(value = "库存量单位id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = skuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除库存量单位")
    @ApiOperation(value = "根据ids批量删除库存量单位")
    @ApiImplicitParam(value = "库存量单位id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = skuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除库存量单位")
    @ApiOperation(value = "根据条件批量删除库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Sku sku) {
        QueryWrapper<Sku> wrapper = WrapperUtil.createQueryWrapper(sku);
        Boolean code = skuService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids查询未上架的库存量单位列表")
    @ApiOperation(value = "根据ids查询未上架的库存量单位列表")
    @ApiImplicitParam(value = "SKU ids", name = "skuIds", dataType = "List<Long>", paramType = "body", required = true)
    @PostMapping(value = "/unlisted")
    public ResponseEntity<List<Sku>> unlisted(@RequestBody List<Long> skuIds) {
        List<Sku> skuList = skuService.unlisted(skuIds);
        return ResponseEntity.ok(skuList);
    }
}
