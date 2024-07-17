package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.model.Guarantee;
import com.cafe.order.service.GuaranteeService;
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
 * @Description: 保障接口
 */
@Api(value = "保障接口")
@RestController
@RequestMapping(value = "/guarantee")
public class GuaranteeController {

    private final GuaranteeService guaranteeService;

    @Autowired
    public GuaranteeController(GuaranteeService guaranteeService) {
        this.guaranteeService = guaranteeService;
    }

    @ApiLogPrint(value = "查询保障数量")
    @ApiOperation(value = "查询保障数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = guaranteeService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询保障数量")
    @ApiOperation(value = "根据条件查询保障数量")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Guarantee guarantee) {
        QueryWrapper<Guarantee> wrapper = WrapperUtil.createQueryWrapper(guarantee);
        Integer count = guaranteeService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询保障列表")
    @ApiOperation(value = "查询保障列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Guarantee>> list() {
        List<Guarantee> guaranteeList = guaranteeService.list();
        return ResponseEntity.ok(guaranteeList);
    }

    @ApiLogPrint(value = "根据条件查询保障列表")
    @ApiOperation(value = "根据条件查询保障列表")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Guarantee>> list(@RequestBody Guarantee guarantee) {
        QueryWrapper<Guarantee> wrapper = WrapperUtil.createQueryWrapper(guarantee);
        List<Guarantee> guaranteeList = guaranteeService.list(wrapper);
        return ResponseEntity.ok(guaranteeList);
    }

    @ApiLogPrint(value = "分页查询保障列表")
    @ApiOperation(value = "分页查询保障列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Guarantee>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Guarantee> page = new Page<>(current, size);
        Page<Guarantee> guaranteePage = guaranteeService.page(page);
        return ResponseEntity.ok(guaranteePage);
    }

    @ApiLogPrint(value = "根据条件分页查询保障")
    @ApiOperation(value = "根据条件分页查询保障")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Guarantee>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Guarantee guarantee
    ) {
        Page<Guarantee> page = new Page<>(current, size);
        QueryWrapper<Guarantee> wrapper = WrapperUtil.createQueryWrapper(guarantee);
        Page<Guarantee> guaranteePage = guaranteeService.page(page, wrapper);
        return ResponseEntity.ok(guaranteePage);
    }

    @ApiLogPrint(value = "根据id查询单个保障")
    @ApiOperation(value = "根据id查询单个保障")
    @ApiImplicitParam(value = "保障id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Guarantee> one(@PathVariable(value = "id") Long id) {
        Guarantee guarantee = guaranteeService.getById(id);
        return ResponseEntity.ok(guarantee);
    }

    @ApiLogPrint(value = "根据条件查询单个保障")
    @ApiOperation(value = "根据条件查询单个保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Guarantee> one(@RequestBody Guarantee guarantee) {
        QueryWrapper<Guarantee> wrapper = WrapperUtil.createQueryWrapper(guarantee);
        Guarantee one = guaranteeService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增保障")
    @ApiOperation(value = "新增保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Guarantee guarantee) {
        Boolean code = guaranteeService.save(guarantee);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增保障")
    @ApiOperation(value = "批量新增保障")
    @ApiImplicitParam(value = "保障列表", name = "guaranteeList", dataType = "List<Guarantee>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Guarantee> guaranteeList) {
        Boolean code = guaranteeService.saveBatch(guaranteeList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改保障")
    @ApiOperation(value = "根据id修改保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Guarantee guarantee) {
        Boolean code = guaranteeService.updateById(guarantee);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改保障")
    @ApiOperation(value = "根据ids批量修改保障")
    @ApiImplicitParam(value = "保障列表", name = "guaranteeList", dataType = "List<Guarantee>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Guarantee> guaranteeList) {
        Boolean code = guaranteeService.updateBatchById(guaranteeList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除保障")
    @ApiOperation(value = "根据id删除保障")
    @ApiImplicitParam(value = "保障id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = guaranteeService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除保障")
    @ApiOperation(value = "根据ids批量删除保障")
    @ApiImplicitParam(value = "保障id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = guaranteeService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除保障")
    @ApiOperation(value = "根据条件批量删除保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Guarantee guarantee) {
        QueryWrapper<Guarantee> wrapper = WrapperUtil.createQueryWrapper(guarantee);
        Boolean code = guaranteeService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
