package com.cafe.clickhouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.clickhouse.model.Goods;
import com.cafe.clickhouse.service.GoodsService;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
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
 * @Package: com.cafe.clickhouse.controller
 * @Author: zhouboyi
 * @Date: 2024/3/15 17:12
 * @Description: 商品接口
 */
@Api(value = "商品接口")
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @LogPrint(value = "查询商品数量")
    @ApiOperation(value = "查询商品数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = goodsService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询商品数量")
    @ApiOperation(value = "根据条件查询商品数量")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Goods goods) {
        QueryWrapper<Goods> wrapper = WrapperUtil.createQueryWrapper(goods);
        Integer count = goodsService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询商品列表")
    @ApiOperation(value = "查询商品列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Goods>> list() {
        List<Goods> goodsList = goodsService.list();
        return ResponseEntity.ok(goodsList);
    }

    @LogPrint(value = "根据条件查询商品列表")
    @ApiOperation(value = "根据条件查询商品列表")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Goods>> list(@RequestBody Goods goods) {
        QueryWrapper<Goods> wrapper = WrapperUtil.createQueryWrapper(goods);
        List<Goods> goodsList = goodsService.list(wrapper);
        return ResponseEntity.ok(goodsList);
    }

    @LogPrint(value = "分页查询商品列表")
    @ApiOperation(value = "分页查询商品列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Goods>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Goods> page = new Page<>(current, size);
        Page<Goods> goodsPage = goodsService.page(page);
        return ResponseEntity.ok(goodsPage);
    }

    @LogPrint(value = "根据条件分页查询商品")
    @ApiOperation(value = "根据条件分页查询商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Goods>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Goods goods
    ) {
        Page<Goods> page = new Page<>(current, size);
        QueryWrapper<Goods> wrapper = WrapperUtil.createQueryWrapper(goods);
        Page<Goods> goodsPage = goodsService.page(page, wrapper);
        return ResponseEntity.ok(goodsPage);
    }

    @LogPrint(value = "根据id查询单个商品")
    @ApiOperation(value = "根据id查询单个商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Goods> one(@PathVariable(value = "id") Long id) {
        Goods goods = goodsService.getById(id);
        return ResponseEntity.ok(goods);
    }

    @LogPrint(value = "根据条件查询单个商品")
    @ApiOperation(value = "根据条件查询单个商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Goods> one(@RequestBody Goods goods) {
        QueryWrapper<Goods> wrapper = WrapperUtil.createQueryWrapper(goods);
        Goods one = goodsService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增商品")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Goods goods) {
        Boolean code = goodsService.save(goods);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增商品")
    @ApiOperation(value = "批量新增商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Goods> goodsList) {
        Boolean code = goodsService.saveBatch(goodsList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改商品")
    @ApiOperation(value = "根据id修改商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Goods goods) {
        Boolean code = goodsService.updateById(goods);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改商品")
    @ApiOperation(value = "根据ids批量修改商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Goods> goodsList) {
        Boolean code = goodsService.updateBatchById(goodsList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除商品")
    @ApiOperation(value = "根据id删除商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = goodsService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除商品")
    @ApiOperation(value = "根据ids批量删除商品")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = goodsService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除商品")
    @ApiOperation(value = "根据条件批量删除商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Goods goods) {
        QueryWrapper<Goods> wrapper = WrapperUtil.createQueryWrapper(goods);
        Boolean code = goodsService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
