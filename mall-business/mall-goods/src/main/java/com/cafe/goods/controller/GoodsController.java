package com.cafe.goods.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:35
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

    @ApiLogPrint(value = "查询商品列表")
    @ApiOperation(value = "查询商品列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Goods>> list() {
        List<Goods> goodsList = goodsService.list(Collections.emptyList());
        return ResponseEntity.ok(goodsList);
    }

    @ApiLogPrint(value = "根据库存量单位ids查询商品列表")
    @ApiOperation(value = "根据库存量单位ids查询商品列表")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Goods>> list(@RequestBody List<Long> ids) {
        List<Goods> goodsList = goodsService.list(ids);
        return ResponseEntity.ok(goodsList);
    }

    @ApiLogPrint(value = "批量上下架商品")
    @ApiOperation(value = "批量上下架商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "SKU ids", name = "ids", dataType = "List<Long>", paramType = "body", required = true),
        @ApiImplicitParam(value = "商品状态", name = "status", dataType = "Integer", paramType = "path", required = true)
    })
    @PostMapping(value = "/launch/{status}")
    public ResponseEntity<Integer> launch(@RequestBody List<Long> ids, @PathVariable(value = "status") Integer status) {
        goodsService.launch(ids, status);
        return ResponseEntity.ok(status);
    }
}
