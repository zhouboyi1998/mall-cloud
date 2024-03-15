package com.cafe.clickhouse.controller;

import com.cafe.clickhouse.model.Goods;
import com.cafe.clickhouse.service.GoodsService;
import com.cafe.common.log.annotation.LogPrint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @LogPrint(value = "查询商品列表")
    @ApiOperation(value = "查询商品列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Goods>> list() {
        List<Goods> goodsList = goodsService.list();
        return ResponseEntity.ok(goodsList);
    }

    @LogPrint(value = "新增商品")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Goods goods) {
        Boolean code = goodsService.save(goods);
        return ResponseEntity.ok(code);
    }
}
