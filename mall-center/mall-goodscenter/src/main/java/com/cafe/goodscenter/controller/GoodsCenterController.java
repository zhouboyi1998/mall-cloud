package com.cafe.goodscenter.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.model.vo.SpuDetail;
import com.cafe.goodscenter.service.GoodsCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.controller
 * @Author: zhouboyi
 * @Date: 2024/7/31 23:16
 * @Description: 商品中心接口
 */
@Api(value = "商品中心接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/goods-center")
public class GoodsCenterController {

    private final GoodsCenterService goodsCenterService;

    @ApiLogPrint(value = "搜索商品")
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/summary/{keyword}")
    public ResponseEntity<List<GoodsSummary>> summary(@PathVariable(value = "keyword") String keyword) {
        List<GoodsSummary> goodsSummaryList = goodsCenterService.summary(keyword);
        return ResponseEntity.ok(goodsSummaryList);
    }

    @ApiLogPrint(value = "获取商品详情")
    @ApiOperation(value = "获取商品详情")
    @ApiImplicitParam(value = "SKU id", name = "skuId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/detail/{skuId}")
    public ResponseEntity<SpuDetail> detail(@PathVariable(value = "skuId") Long skuId) {
        SpuDetail goodsDetail = goodsCenterService.detail(skuId);
        return ResponseEntity.ok(goodsDetail);
    }
}
