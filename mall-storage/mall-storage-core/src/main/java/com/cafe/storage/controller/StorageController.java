package com.cafe.storage.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.storage.model.Storage;
import com.cafe.storage.service.StorageService;
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
 * @Package: com.cafe.storage.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库接口
 */
@Api(value = "仓库接口")
@RestController
@RequestMapping(value = "/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @LogPrint(value = "查询仓库列表")
    @ApiOperation(value = "查询仓库列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Storage>> list() {
        List<Storage> storageList = storageService.list();
        return ResponseEntity.ok(storageList);
    }

    @LogPrint(value = "根据条件查询仓库列表")
    @ApiOperation(value = "根据条件查询仓库列表")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Storage>> list(@RequestBody Storage storage) {
        Wrapper<Storage> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(storage);
        List<Storage> storageList = storageService.list(wrapper);
        return ResponseEntity.ok(storageList);
    }

    @LogPrint(value = "分页查询仓库列表")
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
        Page<Storage> page = new Page<Storage>().setCurrent(current).setSize(size);
        Page<Storage> storagePage = storageService.page(page);
        return ResponseEntity.ok(storagePage);
    }

    @LogPrint(value = "根据条件分页查询仓库")
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
        Page<Storage> page = new Page<Storage>().setCurrent(current).setSize(size);
        Wrapper<Storage> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(storage);
        Page<Storage> storagePage = storageService.page(page, wrapper);
        return ResponseEntity.ok(storagePage);
    }

    @LogPrint(value = "根据id查询单个仓库")
    @ApiOperation(value = "根据id查询单个仓库")
    @ApiImplicitParam(value = "仓库id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Storage> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Storage> wrapper = new LambdaQueryWrapper<Storage>().eq(Storage::getId, id);
        Storage storage = storageService.getOne(wrapper);
        return ResponseEntity.ok(storage);
    }

    @LogPrint(value = "新增仓库")
    @ApiOperation(value = "新增仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Storage storage) {
        LocalDateTime now = LocalDateTime.now();
        storage.setCreateTime(now).setUpdateTime(now);
        Boolean code = storageService.save(storage);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增仓库")
    @ApiOperation(value = "批量新增仓库")
    @ApiImplicitParam(value = "仓库列表", name = "storageList", dataType = "List<Storage>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Storage> storageList) {
        LocalDateTime now = LocalDateTime.now();
        storageList = storageList.stream()
            .map(storage -> storage.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = storageService.saveBatch(storageList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改仓库")
    @ApiOperation(value = "根据id修改仓库")
    @ApiImplicitParam(value = "仓库Model", name = "storage", dataType = "Storage", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Storage storage) {
        Boolean code = storageService.updateById(storage);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改仓库")
    @ApiOperation(value = "根据ids批量修改仓库")
    @ApiImplicitParam(value = "仓库列表", name = "storageList", dataType = "List<Storage>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Storage> storageList) {
        Boolean code = storageService.updateBatchById(storageList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除仓库")
    @ApiOperation(value = "根据id删除仓库")
    @ApiImplicitParam(value = "仓库id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = storageService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除仓库")
    @ApiOperation(value = "根据ids批量删除仓库")
    @ApiImplicitParam(value = "仓库id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = storageService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
