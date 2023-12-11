package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.model.SaleService;
import com.cafe.order.service.SaleServiceService;
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
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后服务接口
 */
@Api(value = "售后服务接口")
@RestController
@RequestMapping(value = "/sale-service")
public class SaleServiceController {

    private final SaleServiceService saleServiceService;

    @Autowired
    public SaleServiceController(SaleServiceService saleServiceService) {
        this.saleServiceService = saleServiceService;
    }

    @LogPrint(value = "查询售后服务数量")
    @ApiOperation(value = "查询售后服务数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = saleServiceService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询售后服务数量")
    @ApiOperation(value = "根据条件查询售后服务数量")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody SaleService saleService) {
        QueryWrapper<SaleService> wrapper = WrapperUtil.createQueryWrapper(saleService);
        Integer count = saleServiceService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询售后服务列表")
    @ApiOperation(value = "查询售后服务列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<SaleService>> list() {
        List<SaleService> saleServiceList = saleServiceService.list();
        return ResponseEntity.ok(saleServiceList);
    }

    @LogPrint(value = "根据条件查询售后服务列表")
    @ApiOperation(value = "根据条件查询售后服务列表")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<SaleService>> list(@RequestBody SaleService saleService) {
        QueryWrapper<SaleService> wrapper = WrapperUtil.createQueryWrapper(saleService);
        List<SaleService> saleServiceList = saleServiceService.list(wrapper);
        return ResponseEntity.ok(saleServiceList);
    }

    @LogPrint(value = "分页查询售后服务列表")
    @ApiOperation(value = "分页查询售后服务列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<SaleService>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<SaleService> page = new Page<>(current, size);
        Page<SaleService> saleServicePage = saleServiceService.page(page);
        return ResponseEntity.ok(saleServicePage);
    }

    @LogPrint(value = "根据条件分页查询售后服务")
    @ApiOperation(value = "根据条件分页查询售后服务")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<SaleService>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody SaleService saleService
    ) {
        Page<SaleService> page = new Page<>(current, size);
        QueryWrapper<SaleService> wrapper = WrapperUtil.createQueryWrapper(saleService);
        Page<SaleService> saleServicePage = saleServiceService.page(page, wrapper);
        return ResponseEntity.ok(saleServicePage);
    }

    @LogPrint(value = "根据id查询单个售后服务")
    @ApiOperation(value = "根据id查询单个售后服务")
    @ApiImplicitParam(value = "售后服务id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<SaleService> one(@PathVariable(value = "id") Long id) {
        SaleService saleService = saleServiceService.getById(id);
        return ResponseEntity.ok(saleService);
    }

    @LogPrint(value = "根据条件查询单个售后服务")
    @ApiOperation(value = "根据条件查询单个售后服务")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<SaleService> one(@RequestBody SaleService saleService) {
        QueryWrapper<SaleService> wrapper = WrapperUtil.createQueryWrapper(saleService);
        SaleService one = saleServiceService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增售后服务")
    @ApiOperation(value = "新增售后服务")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody SaleService saleService) {
        Boolean code = saleServiceService.save(saleService);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增售后服务")
    @ApiOperation(value = "批量新增售后服务")
    @ApiImplicitParam(value = "售后服务列表", name = "saleServiceList", dataType = "List<SaleService>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<SaleService> saleServiceList) {
        Boolean code = saleServiceService.saveBatch(saleServiceList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改售后服务")
    @ApiOperation(value = "根据id修改售后服务")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody SaleService saleService) {
        Boolean code = saleServiceService.updateById(saleService);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改售后服务")
    @ApiOperation(value = "根据ids批量修改售后服务")
    @ApiImplicitParam(value = "售后服务列表", name = "saleServiceList", dataType = "List<SaleService>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<SaleService> saleServiceList) {
        Boolean code = saleServiceService.updateBatchById(saleServiceList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除售后服务")
    @ApiOperation(value = "根据id删除售后服务")
    @ApiImplicitParam(value = "售后服务id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = saleServiceService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除售后服务")
    @ApiOperation(value = "根据ids批量删除售后服务")
    @ApiImplicitParam(value = "售后服务id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = saleServiceService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除售后服务")
    @ApiOperation(value = "根据条件批量删除售后服务")
    @ApiImplicitParam(value = "售后服务Model", name = "saleService", dataType = "SaleService", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody SaleService saleService) {
        QueryWrapper<SaleService> wrapper = WrapperUtil.createQueryWrapper(saleService);
        Boolean code = saleServiceService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
