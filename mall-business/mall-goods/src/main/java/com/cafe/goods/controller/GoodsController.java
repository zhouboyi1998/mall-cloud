package com.cafe.goods.controller;

import com.cafe.common.constant.model.GoodsConstant;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @ApiLogPrint(value = "查询商品列表")
    @ApiOperation(value = "查询商品列表")
    @ApiImplicitParam(value = "查询类型", name = "queryType", dataType = "String", paramType = "query", required = true)
    @GetMapping(value = "/list")
    public ResponseEntity<List<Goods>> list(@RequestParam(value = "queryType", required = false, defaultValue = GoodsConstant.QueryType.FULL) String queryType) {
        List<Goods> goodsList = goodsService.list(queryType, Collections.emptyList());
        return ResponseEntity.ok(goodsList);
    }

    @ApiLogPrint(value = "根据库存量单位ids查询商品列表")
    @ApiOperation(value = "根据库存量单位ids查询商品列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "查询类型", name = "queryType", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "库存量单位id列表", name = "skuIds", dataType = "List<Long>", paramType = "body", required = true)
    })
    @PostMapping(value = "/list")
    public ResponseEntity<List<Goods>> list(
        @RequestParam(value = "queryType", required = false, defaultValue = GoodsConstant.QueryType.FULL) String queryType,
        @RequestBody List<Long> skuIds
    ) {
        List<Goods> goodsList = goodsService.list(queryType, skuIds);
        return ResponseEntity.ok(goodsList);
    }
}
