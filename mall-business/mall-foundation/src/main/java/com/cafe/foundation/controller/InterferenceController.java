package com.cafe.foundation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.foundation.model.entity.Interference;
import com.cafe.foundation.service.InterferenceService;
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
 * @Package: com.cafe.foundation.controller
 * @Author: zhouboyi
 * @Date: 2025-07-28
 * @Description: 干扰符接口
 */
@Api(value = "干扰符接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/interference")
public class InterferenceController {

    private final InterferenceService interferenceService;

    @ApiLogPrint(value = "查询干扰符数量")
    @ApiOperation(value = "查询干扰符数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = interferenceService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询干扰符数量")
    @ApiOperation(value = "根据条件查询干扰符数量")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Interference interference) {
        QueryWrapper<Interference> wrapper = WrapperUtil.createQueryWrapper(interference);
        Integer count = interferenceService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询干扰符列表")
    @ApiOperation(value = "查询干扰符列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Interference>> list() {
        List<Interference> interferenceList = interferenceService.list();
        return ResponseEntity.ok(interferenceList);
    }

    @ApiLogPrint(value = "根据条件查询干扰符列表")
    @ApiOperation(value = "根据条件查询干扰符列表")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Interference>> list(@RequestBody Interference interference) {
        QueryWrapper<Interference> wrapper = WrapperUtil.createQueryWrapper(interference);
        List<Interference> interferenceList = interferenceService.list(wrapper);
        return ResponseEntity.ok(interferenceList);
    }

    @ApiLogPrint(value = "分页查询干扰符列表")
    @ApiOperation(value = "分页查询干扰符列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Interference>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Interference> page = new Page<>(current, size);
        Page<Interference> interferencePage = interferenceService.page(page);
        return ResponseEntity.ok(interferencePage);
    }

    @ApiLogPrint(value = "根据条件分页查询干扰符")
    @ApiOperation(value = "根据条件分页查询干扰符")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Interference>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Interference interference
    ) {
        Page<Interference> page = new Page<>(current, size);
        QueryWrapper<Interference> wrapper = WrapperUtil.createQueryWrapper(interference);
        Page<Interference> interferencePage = interferenceService.page(page, wrapper);
        return ResponseEntity.ok(interferencePage);
    }

    @ApiLogPrint(value = "根据id查询单个干扰符")
    @ApiOperation(value = "根据id查询单个干扰符")
    @ApiImplicitParam(value = "干扰符id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Interference> one(@PathVariable(value = "id") Long id) {
        Interference interference = interferenceService.getById(id);
        return ResponseEntity.ok(interference);
    }

    @ApiLogPrint(value = "根据条件查询单个干扰符")
    @ApiOperation(value = "根据条件查询单个干扰符")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Interference> one(@RequestBody Interference interference) {
        QueryWrapper<Interference> wrapper = WrapperUtil.createQueryWrapper(interference);
        Interference one = interferenceService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增干扰符")
    @ApiOperation(value = "新增干扰符")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Interference interference) {
        Boolean code = interferenceService.save(interference);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增干扰符")
    @ApiOperation(value = "批量新增干扰符")
    @ApiImplicitParam(value = "干扰符列表", name = "interferenceList", dataType = "List<Interference>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Interference> interferenceList) {
        Boolean code = interferenceService.saveBatch(interferenceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改干扰符")
    @ApiOperation(value = "根据id修改干扰符")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Interference interference) {
        Boolean code = interferenceService.updateById(interference);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改干扰符")
    @ApiOperation(value = "根据ids批量修改干扰符")
    @ApiImplicitParam(value = "干扰符列表", name = "interferenceList", dataType = "List<Interference>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Interference> interferenceList) {
        Boolean code = interferenceService.updateBatchById(interferenceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除干扰符")
    @ApiOperation(value = "根据id删除干扰符")
    @ApiImplicitParam(value = "干扰符id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = interferenceService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除干扰符")
    @ApiOperation(value = "根据ids批量删除干扰符")
    @ApiImplicitParam(value = "干扰符id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = interferenceService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除干扰符")
    @ApiOperation(value = "根据条件批量删除干扰符")
    @ApiImplicitParam(value = "干扰符Model", name = "interference", dataType = "Interference", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Interference interference) {
        QueryWrapper<Interference> wrapper = WrapperUtil.createQueryWrapper(interference);
        Boolean code = interferenceService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
