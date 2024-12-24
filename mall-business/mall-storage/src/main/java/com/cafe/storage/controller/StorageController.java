package com.cafe.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.storage.model.entity.Storage;
import com.cafe.storage.service.StorageService;
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
 * @Description: 仓库接口
 */
@Api(value = "仓库接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/storage")
public class StorageController {

    private final StorageService storageService;

    @ApiLogPrint(value = "查询仓库数量")
    @ApiOperation(value = "查询仓库数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = storageService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询仓库数量")
    @ApiOperation(value = "根据条件查询仓库数量")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Storage storage) {
        QueryWrapper<Storage> wrapper = WrapperUtil.createQueryWrapper(storage);
        Integer count = storageService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询仓库列表")
    @ApiOperation(value = "查询仓库列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Storage>> list() {
        List<Storage> storageList = storageService.list();
        return ResponseEntity.ok(storageList);
    }

    @ApiLogPrint(value = "根据条件查询仓库列表")
    @ApiOperation(value = "根据条件查询仓库列表")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Storage>> list(@RequestBody Storage storage) {
        QueryWrapper<Storage> wrapper = WrapperUtil.createQueryWrapper(storage);
        List<Storage> storageList = storageService.list(wrapper);
        return ResponseEntity.ok(storageList);
    }

    @ApiLogPrint(value = "分页查询仓库列表")
    @ApiOperation(value = "分页查询仓库列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Storage>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Storage> page = new Page<>(current, size);
        Page<Storage> storagePage = storageService.page(page);
        return ResponseEntity.ok(storagePage);
    }

    @ApiLogPrint(value = "根据条件分页查询仓库")
    @ApiOperation(value = "根据条件分页查询仓库")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Storage>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Storage storage
    ) {
        Page<Storage> page = new Page<>(current, size);
        QueryWrapper<Storage> wrapper = WrapperUtil.createQueryWrapper(storage);
        Page<Storage> storagePage = storageService.page(page, wrapper);
        return ResponseEntity.ok(storagePage);
    }

    @ApiLogPrint(value = "根据id查询单个仓库")
    @ApiOperation(value = "根据id查询单个仓库")
    @ApiImplicitParam(value = "仓库id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Storage> one(@PathVariable(value = "id") Long id) {
        Storage storage = storageService.getById(id);
        return ResponseEntity.ok(storage);
    }

    @ApiLogPrint(value = "根据条件查询单个仓库")
    @ApiOperation(value = "根据条件查询单个仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Storage> one(@RequestBody Storage storage) {
        QueryWrapper<Storage> wrapper = WrapperUtil.createQueryWrapper(storage);
        Storage one = storageService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增仓库")
    @ApiOperation(value = "新增仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Storage storage) {
        Boolean code = storageService.save(storage);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增仓库")
    @ApiOperation(value = "批量新增仓库")
    @ApiImplicitParam(value = "仓库列表", name = "storageList", dataType = "List<Storage>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Storage> storageList) {
        Boolean code = storageService.saveBatch(storageList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改仓库")
    @ApiOperation(value = "根据id修改仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Storage storage) {
        Boolean code = storageService.updateById(storage);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改仓库")
    @ApiOperation(value = "根据ids批量修改仓库")
    @ApiImplicitParam(value = "仓库列表", name = "storageList", dataType = "List<Storage>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Storage> storageList) {
        Boolean code = storageService.updateBatchById(storageList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除仓库")
    @ApiOperation(value = "根据id删除仓库")
    @ApiImplicitParam(value = "仓库id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = storageService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除仓库")
    @ApiOperation(value = "根据ids批量删除仓库")
    @ApiImplicitParam(value = "仓库id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = storageService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除仓库")
    @ApiOperation(value = "根据条件批量删除仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Storage storage) {
        QueryWrapper<Storage> wrapper = WrapperUtil.createQueryWrapper(storage);
        Boolean code = storageService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
