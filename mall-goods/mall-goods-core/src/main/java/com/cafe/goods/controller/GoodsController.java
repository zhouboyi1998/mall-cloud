package com.cafe.goods.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @LogPrint(value = "批量上下架商品")
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
