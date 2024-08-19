package com.cafe.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.merchant.model.MerchantShop;
import com.cafe.merchant.service.MerchantShopService;
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
 * @Package: com.cafe.merchant.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家-店铺关联关系接口
 */
@Api(value = "商家-店铺关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/merchant-shop")
public class MerchantShopController {

    private final MerchantShopService merchantShopService;

    @ApiLogPrint(value = "查询商家-店铺关联关系数量")
    @ApiOperation(value = "查询商家-店铺关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = merchantShopService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询商家-店铺关联关系数量")
    @ApiOperation(value = "根据条件查询商家-店铺关联关系数量")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody MerchantShop merchantShop) {
        QueryWrapper<MerchantShop> wrapper = WrapperUtil.createQueryWrapper(merchantShop);
        Integer count = merchantShopService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询商家-店铺关联关系列表")
    @ApiOperation(value = "查询商家-店铺关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<MerchantShop>> list() {
        List<MerchantShop> merchantShopList = merchantShopService.list();
        return ResponseEntity.ok(merchantShopList);
    }

    @ApiLogPrint(value = "根据条件查询商家-店铺关联关系列表")
    @ApiOperation(value = "根据条件查询商家-店铺关联关系列表")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<MerchantShop>> list(@RequestBody MerchantShop merchantShop) {
        QueryWrapper<MerchantShop> wrapper = WrapperUtil.createQueryWrapper(merchantShop);
        List<MerchantShop> merchantShopList = merchantShopService.list(wrapper);
        return ResponseEntity.ok(merchantShopList);
    }

    @ApiLogPrint(value = "分页查询商家-店铺关联关系列表")
    @ApiOperation(value = "分页查询商家-店铺关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<MerchantShop>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<MerchantShop> page = new Page<>(current, size);
        Page<MerchantShop> merchantShopPage = merchantShopService.page(page);
        return ResponseEntity.ok(merchantShopPage);
    }

    @ApiLogPrint(value = "根据条件分页查询商家-店铺关联关系")
    @ApiOperation(value = "根据条件分页查询商家-店铺关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<MerchantShop>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody MerchantShop merchantShop
    ) {
        Page<MerchantShop> page = new Page<>(current, size);
        QueryWrapper<MerchantShop> wrapper = WrapperUtil.createQueryWrapper(merchantShop);
        Page<MerchantShop> merchantShopPage = merchantShopService.page(page, wrapper);
        return ResponseEntity.ok(merchantShopPage);
    }

    @ApiLogPrint(value = "根据id查询单个商家-店铺关联关系")
    @ApiOperation(value = "根据id查询单个商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<MerchantShop> one(@PathVariable(value = "id") Long id) {
        MerchantShop merchantShop = merchantShopService.getById(id);
        return ResponseEntity.ok(merchantShop);
    }

    @ApiLogPrint(value = "根据条件查询单个商家-店铺关联关系")
    @ApiOperation(value = "根据条件查询单个商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<MerchantShop> one(@RequestBody MerchantShop merchantShop) {
        QueryWrapper<MerchantShop> wrapper = WrapperUtil.createQueryWrapper(merchantShop);
        MerchantShop one = merchantShopService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增商家-店铺关联关系")
    @ApiOperation(value = "新增商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody MerchantShop merchantShop) {
        Boolean code = merchantShopService.save(merchantShop);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增商家-店铺关联关系")
    @ApiOperation(value = "批量新增商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系列表", name = "merchantShopList", dataType = "List<MerchantShop>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<MerchantShop> merchantShopList) {
        Boolean code = merchantShopService.saveBatch(merchantShopList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改商家-店铺关联关系")
    @ApiOperation(value = "根据id修改商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody MerchantShop merchantShop) {
        Boolean code = merchantShopService.updateById(merchantShop);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改商家-店铺关联关系")
    @ApiOperation(value = "根据ids批量修改商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系列表", name = "merchantShopList", dataType = "List<MerchantShop>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<MerchantShop> merchantShopList) {
        Boolean code = merchantShopService.updateBatchById(merchantShopList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除商家-店铺关联关系")
    @ApiOperation(value = "根据id删除商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = merchantShopService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除商家-店铺关联关系")
    @ApiOperation(value = "根据ids批量删除商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = merchantShopService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除商家-店铺关联关系")
    @ApiOperation(value = "根据条件批量删除商家-店铺关联关系")
    @ApiImplicitParam(value = "商家-店铺关联关系Model", name = "merchantShop", dataType = "MerchantShop", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody MerchantShop merchantShop) {
        QueryWrapper<MerchantShop> wrapper = WrapperUtil.createQueryWrapper(merchantShop);
        Boolean code = merchantShopService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
