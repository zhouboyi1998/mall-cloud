package com.cafe.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.bo.GoodsBO;
import com.cafe.goods.service.GoodsBOService;
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
@RequestMapping(value = "/goods/bo")
public class GoodsBOController {

    private GoodsBOService goodsBOService;

    @Autowired
    public GoodsBOController(GoodsBOService goodsBOService) {
        this.goodsBOService = goodsBOService;
    }

    @LogPrint(value = "根据 SKU ids 查询商品列表")
    @ApiOperation(value = "根据 SKU ids 查询商品列表")
    @ApiImplicitParam(name = "ids", value = "SKU ids", required = true, paramType = "body", dataType = "List<Long>")
    @PostMapping(value = "/list")
    public ResponseEntity<List<GoodsBO>> list(@RequestBody List<Long> ids) {
        List<GoodsBO> goodsBOList = goodsBOService.list(ids);
        return ResponseEntity.ok(goodsBOList);
    }

    @LogPrint(value = "分页查询商品列表")
    @ApiOperation(value = "分页查询商品列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<GoodsBO>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<GoodsBO> page = new Page<GoodsBO>().setCurrent(current).setSize(size);
        Page<GoodsBO> goodsBOPage = goodsBOService.page(page);
        return ResponseEntity.ok(goodsBOPage);
    }
}
