package com.cafe.solr.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.solr.model.Goods;
import com.cafe.solr.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.controller
 * @Author: zhouboyi
 * @Date: 2022/10/26 15:17
 * @Description: Solr 商品接口
 */
@Api(value = "Solr 商品接口")
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @LogPrint(value = "获取商品")
    @ApiOperation(value = "获取商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Goods> one(@PathVariable(value = "id") String id) {
        Goods goods = goodsService.one(id);
        return ResponseEntity.ok(goods);
    }

    @LogPrint(value = "插入商品/更新商品")
    @ApiOperation(value = "插入商品/更新商品")
    @ApiImplicitParam(value = "商品Model", name = "goods", dataType = "Goods", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<Void> save(@RequestBody Goods goods) {
        goodsService.save(goods);
        return ResponseEntity.ok().build();
    }

    @LogPrint(value = "批量插入商品/批量更新商品")
    @ApiOperation(value = "批量插入商品/批量更新商品")
    @ApiImplicitParam(value = "商品列表", name = "goodsList", dataType = "List<Goods>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<Void> saveBatch(@RequestBody List<Goods> goodsList) {
        goodsService.saveBatch(goodsList);
        return ResponseEntity.ok().build();
    }

    @LogPrint(value = "删除商品")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(value = "商品id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        goodsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @LogPrint(value = "批量删除商品")
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParam(value = "商品id列表", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<String> ids) {
        goodsService.deleteBatch(ids);
        return ResponseEntity.ok().build();
    }
}
