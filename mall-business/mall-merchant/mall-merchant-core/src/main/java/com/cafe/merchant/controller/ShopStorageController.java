package com.cafe.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.merchant.model.ShopStorage;
import com.cafe.merchant.service.ShopStorageService;
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
 * @Package: com.cafe.merchant.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺-仓库关联关系接口
 */
@Api(value = "店铺-仓库关联关系接口")
@RestController
@RequestMapping(value = "/shop-storage")
public class ShopStorageController {

    private final ShopStorageService shopStorageService;

    @Autowired
    public ShopStorageController(ShopStorageService shopStorageService) {
        this.shopStorageService = shopStorageService;
    }

    @LogPrint(value = "查询店铺-仓库关联关系数量")
    @ApiOperation(value = "查询店铺-仓库关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = shopStorageService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询店铺-仓库关联关系数量")
    @ApiOperation(value = "根据条件查询店铺-仓库关联关系数量")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody ShopStorage shopStorage) {
        QueryWrapper<ShopStorage> wrapper = WrapperUtil.createQueryWrapper(shopStorage);
        Integer count = shopStorageService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询店铺-仓库关联关系列表")
    @ApiOperation(value = "查询店铺-仓库关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<ShopStorage>> list() {
        List<ShopStorage> shopStorageList = shopStorageService.list();
        return ResponseEntity.ok(shopStorageList);
    }

    @LogPrint(value = "根据条件查询店铺-仓库关联关系列表")
    @ApiOperation(value = "根据条件查询店铺-仓库关联关系列表")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<ShopStorage>> list(@RequestBody ShopStorage shopStorage) {
        QueryWrapper<ShopStorage> wrapper = WrapperUtil.createQueryWrapper(shopStorage);
        List<ShopStorage> shopStorageList = shopStorageService.list(wrapper);
        return ResponseEntity.ok(shopStorageList);
    }

    @LogPrint(value = "分页查询店铺-仓库关联关系列表")
    @ApiOperation(value = "分页查询店铺-仓库关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<ShopStorage>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<ShopStorage> page = new Page<ShopStorage>().setCurrent(current).setSize(size);
        Page<ShopStorage> shopStoragePage = shopStorageService.page(page);
        return ResponseEntity.ok(shopStoragePage);
    }

    @LogPrint(value = "根据条件分页查询店铺-仓库关联关系")
    @ApiOperation(value = "根据条件分页查询店铺-仓库关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<ShopStorage>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody ShopStorage shopStorage
    ) {
        Page<ShopStorage> page = new Page<ShopStorage>().setCurrent(current).setSize(size);
        QueryWrapper<ShopStorage> wrapper = WrapperUtil.createQueryWrapper(shopStorage);
        Page<ShopStorage> shopStoragePage = shopStorageService.page(page, wrapper);
        return ResponseEntity.ok(shopStoragePage);
    }

    @LogPrint(value = "根据id查询单个店铺-仓库关联关系")
    @ApiOperation(value = "根据id查询单个店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ShopStorage> one(@PathVariable(value = "id") Long id) {
        ShopStorage shopStorage = shopStorageService.getById(id);
        return ResponseEntity.ok(shopStorage);
    }

    @LogPrint(value = "根据条件查询单个店铺-仓库关联关系")
    @ApiOperation(value = "根据条件查询单个店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<ShopStorage> one(@RequestBody ShopStorage shopStorage) {
        QueryWrapper<ShopStorage> wrapper = WrapperUtil.createQueryWrapper(shopStorage);
        ShopStorage one = shopStorageService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增店铺-仓库关联关系")
    @ApiOperation(value = "新增店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody ShopStorage shopStorage) {
        LocalDateTime now = LocalDateTime.now();
        shopStorage.setCreateTime(now).setUpdateTime(now);
        Boolean code = shopStorageService.save(shopStorage);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增店铺-仓库关联关系")
    @ApiOperation(value = "批量新增店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系列表", name = "shopStorageList", dataType = "List<ShopStorage>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<ShopStorage> shopStorageList) {
        LocalDateTime now = LocalDateTime.now();
        shopStorageList = shopStorageList.stream()
            .map(shopStorage -> shopStorage.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = shopStorageService.saveBatch(shopStorageList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改店铺-仓库关联关系")
    @ApiOperation(value = "根据id修改店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody ShopStorage shopStorage) {
        Boolean code = shopStorageService.updateById(shopStorage);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改店铺-仓库关联关系")
    @ApiOperation(value = "根据ids批量修改店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系列表", name = "shopStorageList", dataType = "List<ShopStorage>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ShopStorage> shopStorageList) {
        Boolean code = shopStorageService.updateBatchById(shopStorageList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除店铺-仓库关联关系")
    @ApiOperation(value = "根据id删除店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = shopStorageService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除店铺-仓库关联关系")
    @ApiOperation(value = "根据ids批量删除店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = shopStorageService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除店铺-仓库关联关系")
    @ApiOperation(value = "根据条件批量删除店铺-仓库关联关系")
    @ApiImplicitParam(value = "店铺-仓库关联关系Model", name = "shopStorage", dataType = "ShopStorage", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody ShopStorage shopStorage) {
        QueryWrapper<ShopStorage> wrapper = WrapperUtil.createQueryWrapper(shopStorage);
        Boolean code = shopStorageService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
