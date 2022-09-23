package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import com.cafe.goods.model.Sku;
import com.cafe.goods.service.SkuService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Stock Keeping Unit 库存量单位 (控制器)
 */
@Api(value = "Stock Keeping Unit 库存量单位接口")
@RestController
@RequestMapping(value = "/sku")
public class SkuController {

    private SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @LogPrint(description = "查询Stock Keeping Unit 库存量单位列表")
    @ApiOperation(value = "查询Stock Keeping Unit 库存量单位列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Sku>> list() {
        List<Sku> skuList = skuService.list();
        return ResponseEntity.ok(skuList);
    }

    @LogPrint(description = "根据条件查询Stock Keeping Unit 库存量单位列表")
    @ApiOperation(value = "根据条件查询Stock Keeping Unit 库存量单位列表")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Sku>> list(@RequestBody Sku sku) {
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        List<Sku> skuList = skuService.list(wrapper);
        return ResponseEntity.ok(skuList);
    }

    @LogPrint(description = "分页查询Stock Keeping Unit 库存量单位列表")
    @ApiOperation(value = "分页查询Stock Keeping Unit 库存量单位列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sku>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Page<Sku> skuPage = skuService.page(page);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(description = "根据条件分页查询Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据条件分页查询Stock Keeping Unit 库存量单位")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sku>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Sku sku
    ) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        Page<Sku> skuPage = skuService.page(page, wrapper);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(description = "根据id查询单个Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据id查询单个Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Sku> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<Sku>().eq(Sku::getId, id);
        Sku sku = skuService.getOne(wrapper);
        return ResponseEntity.ok(sku);
    }

    @LogPrint(description = "新增Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "新增Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Sku sku) {
        sku.setCreateTime(LocalDateTime.now());
        sku.setUpdateTime(LocalDateTime.now());
        Boolean code = skuService.save(sku);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据id修改Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据id修改Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Sku sku) {
        Boolean code = skuService.updateById(sku);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据ids批量修改Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据ids批量修改Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "skuList", value = "Stock Keeping Unit 库存量单位列表", required = true, paramType = "body", dataType = "List<Sku>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Sku> skuList) {
        Boolean code = skuService.updateBatchById(skuList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据id删除Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据id删除Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = skuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据ids批量删除Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据ids批量删除Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "ids", value = "Stock Keeping Unit 库存量单位id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = skuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "分页查询Stock Keeping Unit 库存量单位列表")
    @ApiOperation(value = "分页查询Stock Keeping Unit 库存量单位列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Sku>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Page<Sku> skuPage = skuService.page(page);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(description = "根据条件分页查询Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据条件分页查询Stock Keeping Unit 库存量单位")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    })
    @PostMapping(value = "/page")
    public ResponseEntity<Page<Sku>> pageByParam(
        @RequestParam Long current,
        @RequestParam Long size,
        @RequestBody Sku sku
    ) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        Page<Sku> skuPage = skuService.page(page, wrapper);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(description = "根据id查询单个Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据id查询单个Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<Sku> oneByParam(@RequestParam Long id) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<Sku>().eq(Sku::getId, id);
        Sku sku = skuService.getOne(wrapper);
        return ResponseEntity.ok(sku);
    }

    @LogPrint(description = "根据id删除Stock Keeping Unit 库存量单位")
    @ApiOperation(value = "根据id删除Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> deleteByParam(@RequestParam Long id) {
        Boolean code = skuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "分页查询 SkuElasticSearchDTO 列表")
    @ApiOperation(value = "分页查询 SkuElasticSearchDTO 列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/es/{current}/{size}")
    public ResponseEntity<Page<SkuElasticSearchDTO>> pageSkuElasticSearchDTO(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<SkuElasticSearchDTO> page = new Page<SkuElasticSearchDTO>().setCurrent(current).setSize(size);
        Page<SkuElasticSearchDTO> dtoPage = skuService.pageSkuElasticSearchDTO(page);
        return ResponseEntity.ok(dtoPage);
    }

    @LogPrint(description = "根据 SKU ids 查询 SkuElasticSearchDTO 列表")
    @ApiOperation(value = "根据 SKU ids 查询 SkuElasticSearchDTO 列表")
    @ApiImplicitParam(name = "ids", value = "SKU ids", required = true, paramType = "body", dataType = "List<Long>")
    @PostMapping(value = "/list/es")
    public ResponseEntity<List<SkuElasticSearchDTO>> listSkuElasticSearchDTO(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(skuService.listSkuElasticSearchDTO(ids));
    }
}
