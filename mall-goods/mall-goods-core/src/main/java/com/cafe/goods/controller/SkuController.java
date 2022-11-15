package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
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
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位接口
 */
@Api(value = "库存量单位接口")
@RestController
@RequestMapping(value = "/sku")
public class SkuController {

    private final SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @LogPrint(value = "查询库存量单位列表")
    @ApiOperation(value = "查询库存量单位列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Sku>> list() {
        List<Sku> skuList = skuService.list();
        return ResponseEntity.ok(skuList);
    }

    @LogPrint(value = "根据条件查询库存量单位列表")
    @ApiOperation(value = "根据条件查询库存量单位列表")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Sku>> list(@RequestBody Sku sku) {
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        List<Sku> skuList = skuService.list(wrapper);
        return ResponseEntity.ok(skuList);
    }

    @LogPrint(value = "分页查询库存量单位列表")
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
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Page<Sku> skuPage = skuService.page(page);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(value = "根据条件分页查询库存量单位")
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
        Page<Sku> page = new Page<Sku>().setCurrent(current).setSize(size);
        Wrapper<Sku> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(sku);
        Page<Sku> skuPage = skuService.page(page, wrapper);
        return ResponseEntity.ok(skuPage);
    }

    @LogPrint(value = "根据id查询单个库存量单位")
    @ApiOperation(value = "根据id查询单个库存量单位")
    @ApiImplicitParam(value = "库存量单位id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Sku> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<Sku>().eq(Sku::getId, id);
        Sku sku = skuService.getOne(wrapper);
        return ResponseEntity.ok(sku);
    }

    @LogPrint(value = "新增库存量单位")
    @ApiOperation(value = "新增库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Sku sku) {
        sku.setCreateTime(LocalDateTime.now());
        sku.setUpdateTime(LocalDateTime.now());
        Boolean code = skuService.save(sku);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改库存量单位")
    @ApiOperation(value = "根据id修改库存量单位")
    @ApiImplicitParam(value = "库存量单位Model", name = "sku", dataType = "Sku", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Sku sku) {
        Boolean code = skuService.updateById(sku);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改库存量单位")
    @ApiOperation(value = "根据ids批量修改库存量单位")
    @ApiImplicitParam(value = "库存量单位列表", name = "skuList", dataType = "List<Sku>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Sku> skuList) {
        Boolean code = skuService.updateBatchById(skuList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除库存量单位")
    @ApiOperation(value = "根据id删除库存量单位")
    @ApiImplicitParam(value = "库存量单位id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = skuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除库存量单位")
    @ApiOperation(value = "根据ids批量删除库存量单位")
    @ApiImplicitParam(value = "库存量单位id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = skuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
