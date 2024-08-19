package com.cafe.clickhouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.clickhouse.model.GoodsWide;
import com.cafe.clickhouse.service.GoodsWideService;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
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
 * @Package: com.cafe.clickhouse.controller
 * @Author: zhouboyi
 * @Date: 2024/3/15 17:12
 * @Description: 商品宽表接口
 */
@Api(value = "商品宽表接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/goods-wide")
public class GoodsWideController {

    private final GoodsWideService goodsWideService;

    @ApiLogPrint(value = "查询商品数量")
    @ApiOperation(value = "查询商品数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = goodsWideService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询商品数量")
    @ApiOperation(value = "根据条件查询商品数量")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody GoodsWide goodsWide) {
        QueryWrapper<GoodsWide> wrapper = WrapperUtil.createQueryWrapper(goodsWide);
        Integer count = goodsWideService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询商品列表")
    @ApiOperation(value = "查询商品列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<GoodsWide>> list() {
        List<GoodsWide> goodsWideList = goodsWideService.list();
        return ResponseEntity.ok(goodsWideList);
    }

    @ApiLogPrint(value = "根据条件查询商品列表")
    @ApiOperation(value = "根据条件查询商品列表")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<GoodsWide>> list(@RequestBody GoodsWide goodsWide) {
        QueryWrapper<GoodsWide> wrapper = WrapperUtil.createQueryWrapper(goodsWide);
        List<GoodsWide> goodsWideList = goodsWideService.list(wrapper);
        return ResponseEntity.ok(goodsWideList);
    }

    @ApiLogPrint(value = "分页查询商品列表")
    @ApiOperation(value = "分页查询商品列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<GoodsWide>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<GoodsWide> page = new Page<>(current, size);
        Page<GoodsWide> goodsWidePage = goodsWideService.page(page);
        return ResponseEntity.ok(goodsWidePage);
    }

    @ApiLogPrint(value = "根据条件分页查询商品")
    @ApiOperation(value = "根据条件分页查询商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<GoodsWide>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody GoodsWide goodsWide
    ) {
        Page<GoodsWide> page = new Page<>(current, size);
        QueryWrapper<GoodsWide> wrapper = WrapperUtil.createQueryWrapper(goodsWide);
        Page<GoodsWide> goodsWidePage = goodsWideService.page(page, wrapper);
        return ResponseEntity.ok(goodsWidePage);
    }

    @ApiLogPrint(value = "根据id查询单个商品")
    @ApiOperation(value = "根据id查询单个商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<GoodsWide> one(@PathVariable(value = "id") Long id) {
        GoodsWide goodsWide = goodsWideService.getById(id);
        return ResponseEntity.ok(goodsWide);
    }

    @ApiLogPrint(value = "根据条件查询单个商品")
    @ApiOperation(value = "根据条件查询单个商品")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<GoodsWide> one(@RequestBody GoodsWide goodsWide) {
        QueryWrapper<GoodsWide> wrapper = WrapperUtil.createQueryWrapper(goodsWide);
        GoodsWide one = goodsWideService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增商品")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody GoodsWide goodsWide) {
        Boolean code = goodsWideService.save(goodsWide);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增商品")
    @ApiOperation(value = "批量新增商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsWideList", dataType = "List<GoodsWide>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<GoodsWide> goodsWideList) {
        Boolean code = goodsWideService.saveBatch(goodsWideList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改商品")
    @ApiOperation(value = "根据id修改商品")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody GoodsWide goodsWide) {
        Boolean code = goodsWideService.updateById(goodsWide);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改商品")
    @ApiOperation(value = "根据ids批量修改商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsWideList", dataType = "List<GoodsWide>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<GoodsWide> goodsWideList) {
        Boolean code = goodsWideService.updateBatchById(goodsWideList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除商品")
    @ApiOperation(value = "根据id删除商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = goodsWideService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除商品")
    @ApiOperation(value = "根据ids批量删除商品")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = goodsWideService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除商品")
    @ApiOperation(value = "根据条件批量删除商品")
    @ApiImplicitParam(value = "商品Model", name = "goodsWide", dataType = "GoodsWide", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody GoodsWide goodsWide) {
        QueryWrapper<GoodsWide> wrapper = WrapperUtil.createQueryWrapper(goodsWide);
        Boolean code = goodsWideService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
