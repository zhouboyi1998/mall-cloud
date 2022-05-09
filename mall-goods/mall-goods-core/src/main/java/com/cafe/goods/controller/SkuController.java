package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Sku;
import com.cafe.goods.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/sku")
public class SkuController {

    private SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @ApiOperation(value = "查询Stock Keeping Unit 库存量单位列表")
    @GetMapping("/list")
    public ResponseEntity<List<Sku>> list() {
        List<Sku> skuList = skuService.list();
        return ResponseEntity.ok(skuList);
    }

    @ApiOperation(value = "根据条件查询Stock Keeping Unit 库存量单位列表")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PostMapping("/list")
    public ResponseEntity<List<Sku>> listByWrapper(@RequestBody Sku sku) {
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        List<Sku> skuList = skuService.list(wrapper);
        return ResponseEntity.ok(skuList);
    }

    @ApiOperation("分页查询Stock Keeping Unit 库存量单位列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Sku>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Page<Sku> skuPage = skuService.page(page);
        return ResponseEntity.ok(skuPage);
    }

    @ApiOperation(value = "根据条件分页查询Stock Keeping Unit 库存量单位")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    })
    @PostMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Sku>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Sku sku
    ) {
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        Page<Sku> skuPage = skuService.page(page, wrapper);
        return ResponseEntity.ok(skuPage);
    }

    @ApiOperation(value = "根据id查询单个Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/one/{id}")
    public ResponseEntity<Sku> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<Sku>().eq(Sku::getId, id);
        Sku sku = skuService.getOne(wrapper);
        return ResponseEntity.ok(sku);
    }

    @ApiOperation(value = "新增Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Sku sku) {
        sku.setCreateTime(new Date());
        sku.setUpdateTime(new Date());
        Boolean code = skuService.save(sku);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "sku", value = "Stock Keeping Unit 库存量单位Model", required = true, paramType = "body", dataType = "Sku")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Sku sku) {
        Boolean code = skuService.updateById(sku);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "skuList", value = "Stock Keeping Unit 库存量单位列表", required = true, paramType = "body", dataType = "List<Sku>")
    @PutMapping("/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Sku> skuList) {
        Boolean code = skuService.updateBatchById(skuList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "id", value = "Stock Keeping Unit 库存量单位id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = skuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除Stock Keeping Unit 库存量单位")
    @ApiImplicitParam(name = "ids", value = "Stock Keeping Unit 库存量单位id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping("/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = skuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
