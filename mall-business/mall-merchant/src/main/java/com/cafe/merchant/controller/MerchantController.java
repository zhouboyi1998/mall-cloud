package com.cafe.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.merchant.model.entity.Merchant;
import com.cafe.merchant.service.MerchantService;
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
 * @Description: 商家接口
 */
@Api(value = "商家接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @ApiLogPrint(value = "查询商家数量")
    @ApiOperation(value = "查询商家数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = merchantService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询商家数量")
    @ApiOperation(value = "根据条件查询商家数量")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Merchant merchant) {
        QueryWrapper<Merchant> wrapper = WrapperUtil.createQueryWrapper(merchant);
        Integer count = merchantService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询商家列表")
    @ApiOperation(value = "查询商家列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Merchant>> list() {
        List<Merchant> merchantList = merchantService.list();
        return ResponseEntity.ok(merchantList);
    }

    @ApiLogPrint(value = "根据条件查询商家列表")
    @ApiOperation(value = "根据条件查询商家列表")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Merchant>> list(@RequestBody Merchant merchant) {
        QueryWrapper<Merchant> wrapper = WrapperUtil.createQueryWrapper(merchant);
        List<Merchant> merchantList = merchantService.list(wrapper);
        return ResponseEntity.ok(merchantList);
    }

    @ApiLogPrint(value = "分页查询商家列表")
    @ApiOperation(value = "分页查询商家列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Merchant>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Merchant> page = new Page<>(current, size);
        Page<Merchant> merchantPage = merchantService.page(page);
        return ResponseEntity.ok(merchantPage);
    }

    @ApiLogPrint(value = "根据条件分页查询商家")
    @ApiOperation(value = "根据条件分页查询商家")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Merchant>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Merchant merchant
    ) {
        Page<Merchant> page = new Page<>(current, size);
        QueryWrapper<Merchant> wrapper = WrapperUtil.createQueryWrapper(merchant);
        Page<Merchant> merchantPage = merchantService.page(page, wrapper);
        return ResponseEntity.ok(merchantPage);
    }

    @ApiLogPrint(value = "根据id查询单个商家")
    @ApiOperation(value = "根据id查询单个商家")
    @ApiImplicitParam(value = "商家id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Merchant> one(@PathVariable(value = "id") Long id) {
        Merchant merchant = merchantService.getById(id);
        return ResponseEntity.ok(merchant);
    }

    @ApiLogPrint(value = "根据条件查询单个商家")
    @ApiOperation(value = "根据条件查询单个商家")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Merchant> one(@RequestBody Merchant merchant) {
        QueryWrapper<Merchant> wrapper = WrapperUtil.createQueryWrapper(merchant);
        Merchant one = merchantService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增商家")
    @ApiOperation(value = "新增商家")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Merchant merchant) {
        Boolean code = merchantService.save(merchant);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增商家")
    @ApiOperation(value = "批量新增商家")
    @ApiImplicitParam(value = "商家列表", name = "merchantList", dataType = "List<Merchant>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Merchant> merchantList) {
        Boolean code = merchantService.saveBatch(merchantList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改商家")
    @ApiOperation(value = "根据id修改商家")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Merchant merchant) {
        Boolean code = merchantService.updateById(merchant);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改商家")
    @ApiOperation(value = "根据ids批量修改商家")
    @ApiImplicitParam(value = "商家列表", name = "merchantList", dataType = "List<Merchant>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Merchant> merchantList) {
        Boolean code = merchantService.updateBatchById(merchantList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除商家")
    @ApiOperation(value = "根据id删除商家")
    @ApiImplicitParam(value = "商家id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = merchantService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除商家")
    @ApiOperation(value = "根据ids批量删除商家")
    @ApiImplicitParam(value = "商家id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = merchantService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除商家")
    @ApiOperation(value = "根据条件批量删除商家")
    @ApiImplicitParam(value = "商家Model", name = "merchant", dataType = "Merchant", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Merchant merchant) {
        QueryWrapper<Merchant> wrapper = WrapperUtil.createQueryWrapper(merchant);
        Boolean code = merchantService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
