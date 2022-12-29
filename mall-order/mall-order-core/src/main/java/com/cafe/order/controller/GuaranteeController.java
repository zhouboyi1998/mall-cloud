package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @LogPrint(value = "查询保障列表")
    @ApiOperation(value = "查询保障列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Guarantee>> list() {
        List<Guarantee> guaranteeList = guaranteeService.list();
        return ResponseEntity.ok(guaranteeList);
    }

    @LogPrint(value = "根据条件查询保障列表")
    @ApiOperation(value = "根据条件查询保障列表")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Guarantee>> list(@RequestBody Guarantee guarantee) {
        Wrapper<Guarantee> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(guarantee);
        List<Guarantee> guaranteeList = guaranteeService.list(wrapper);
        return ResponseEntity.ok(guaranteeList);
    }

    @LogPrint(value = "分页查询保障列表")
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
        Page<Guarantee> page = new Page<Guarantee>().setCurrent(current).setSize(size);
        Page<Guarantee> guaranteePage = guaranteeService.page(page);
        return ResponseEntity.ok(guaranteePage);
    }

    @LogPrint(value = "根据条件分页查询保障")
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
        Page<Guarantee> page = new Page<Guarantee>().setCurrent(current).setSize(size);
        Wrapper<Guarantee> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(guarantee);
        Page<Guarantee> guaranteePage = guaranteeService.page(page, wrapper);
        return ResponseEntity.ok(guaranteePage);
    }

    @LogPrint(value = "根据id查询单个保障")
    @ApiOperation(value = "根据id查询单个保障")
    @ApiImplicitParam(value = "保障id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Guarantee> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Guarantee> wrapper = new LambdaQueryWrapper<Guarantee>().eq(Guarantee::getId, id);
        Guarantee guarantee = guaranteeService.getOne(wrapper);
        return ResponseEntity.ok(guarantee);
    }

    @LogPrint(value = "新增保障")
    @ApiOperation(value = "新增保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Guarantee guarantee) {
        LocalDateTime now = LocalDateTime.now();
        guarantee.setCreateTime(now).setUpdateTime(now);
        Boolean code = guaranteeService.save(guarantee);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增保障")
    @ApiOperation(value = "批量新增保障")
    @ApiImplicitParam(value = "保障列表", name = "guaranteeList", dataType = "List<Guarantee>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Guarantee> guaranteeList) {
        LocalDateTime now = LocalDateTime.now();
        guaranteeList = guaranteeList.stream()
            .map(guarantee -> guarantee.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = guaranteeService.saveBatch(guaranteeList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改保障")
    @ApiOperation(value = "根据id修改保障")
    @ApiImplicitParam(value = "保障Model", name = "guarantee", dataType = "Guarantee", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Guarantee guarantee) {
        Boolean code = guaranteeService.updateById(guarantee);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改保障")
    @ApiOperation(value = "根据ids批量修改保障")
    @ApiImplicitParam(value = "保障列表", name = "guaranteeList", dataType = "List<Guarantee>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Guarantee> guaranteeList) {
        Boolean code = guaranteeService.updateBatchById(guaranteeList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除保障")
    @ApiOperation(value = "根据id删除保障")
    @ApiImplicitParam(value = "保障id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = guaranteeService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除保障")
    @ApiOperation(value = "根据ids批量删除保障")
    @ApiImplicitParam(value = "保障id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = guaranteeService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
