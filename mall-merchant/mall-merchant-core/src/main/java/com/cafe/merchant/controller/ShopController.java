package com.cafe.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.merchant.model.Shop;
import com.cafe.merchant.service.ShopService;
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
 * @Description: 店铺接口
 */
@Api(value = "店铺接口")
@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @LogPrint(value = "查询店铺列表")
    @ApiOperation(value = "查询店铺列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Shop>> list() {
        List<Shop> shopList = shopService.list();
        return ResponseEntity.ok(shopList);
    }

    @LogPrint(value = "根据条件查询店铺列表")
    @ApiOperation(value = "根据条件查询店铺列表")
    @ApiImplicitParam(value = "店铺Model", name = "shop", dataType = "Shop", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Shop>> list(@RequestBody Shop shop) {
        Wrapper<Shop> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(shop);
        List<Shop> shopList = shopService.list(wrapper);
        return ResponseEntity.ok(shopList);
    }

    @LogPrint(value = "分页查询店铺列表")
    @ApiOperation(value = "分页查询店铺列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Shop>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Shop> page = new Page<Shop>().setCurrent(current).setSize(size);
        Page<Shop> shopPage = shopService.page(page);
        return ResponseEntity.ok(shopPage);
    }

    @LogPrint(value = "根据条件分页查询店铺")
    @ApiOperation(value = "根据条件分页查询店铺")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "店铺Model", name = "shop", dataType = "Shop", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Shop>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Shop shop
    ) {
        Page<Shop> page = new Page<Shop>().setCurrent(current).setSize(size);
        Wrapper<Shop> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(shop);
        Page<Shop> shopPage = shopService.page(page, wrapper);
        return ResponseEntity.ok(shopPage);
    }

    @LogPrint(value = "根据id查询单个店铺")
    @ApiOperation(value = "根据id查询单个店铺")
    @ApiImplicitParam(value = "店铺id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Shop> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<Shop>().eq(Shop::getId, id);
        Shop shop = shopService.getOne(wrapper);
        return ResponseEntity.ok(shop);
    }

    @LogPrint(value = "新增店铺")
    @ApiOperation(value = "新增店铺")
    @ApiImplicitParam(value = "店铺Model", name = "shop", dataType = "Shop", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Shop shop) {
        LocalDateTime now = LocalDateTime.now();
        shop.setCreateTime(now).setUpdateTime(now);
        Boolean code = shopService.save(shop);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增店铺")
    @ApiOperation(value = "批量新增店铺")
    @ApiImplicitParam(value = "店铺列表", name = "shopList", dataType = "List<Shop>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Shop> shopList) {
        LocalDateTime now = LocalDateTime.now();
        shopList = shopList.stream()
            .map(shop -> shop.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = shopService.saveBatch(shopList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改店铺")
    @ApiOperation(value = "根据id修改店铺")
    @ApiImplicitParam(value = "店铺Model", name = "shop", dataType = "Shop", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Shop shop) {
        Boolean code = shopService.updateById(shop);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改店铺")
    @ApiOperation(value = "根据ids批量修改店铺")
    @ApiImplicitParam(value = "店铺列表", name = "shopList", dataType = "List<Shop>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Shop> shopList) {
        Boolean code = shopService.updateBatchById(shopList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除店铺")
    @ApiOperation(value = "根据id删除店铺")
    @ApiImplicitParam(value = "店铺id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = shopService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除店铺")
    @ApiOperation(value = "根据ids批量删除店铺")
    @ApiImplicitParam(value = "店铺id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = shopService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
