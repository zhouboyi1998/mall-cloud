package com.cafe.goodscenter.controller;

import com.cafe.common.constant.database.DatabaseConstant;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goodscenter.model.vo.GoodsDetail;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.service.GoodsCenterService;
import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiLogPrint(value = "批量上下架商品")
    @ApiOperation(value = "批量上下架商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "商品状态", name = "status", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "库存量单位id列表", name = "skuIds", dataType = "List<Long>", paramType = "body", required = true)
    })
    @PostMapping(value = "/shelve/{status}")
    public ResponseEntity<Void> shelve(
        @PathVariable(value = "status") Integer status,
        @RequestBody List<Long> skuIds
    ) {
        goodsCenterService.shelve(status, skuIds);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "搜索商品")
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Integer", paramType = "path", required = true),
        @ApiImplicitParam(value = "排序属性", name = "sort", dataType = "String", paramType = "query"),
        @ApiImplicitParam(value = "排序规则", name = "rule", dataType = "String", paramType = "query"),
        @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/summary/{current}/{size}")
    public ResponseEntity<AggregatedPageVO<GoodsSummary>> summary(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "sortField", required = false, defaultValue = ElasticSearchConstant.Goods.DEFAULT_SORT_FIELD) String sortField,
        @RequestParam(value = "sortRule", required = false, defaultValue = DatabaseConstant.Rule.DESC) String sortRule,
        @RequestParam(value = "keyword", required = false) String keyword
    ) {
        AggregatedPageVO<GoodsSummary> goodsSummaryPage = goodsCenterService.summary(current, size, sortField, sortRule, keyword);
        return ResponseEntity.ok(goodsSummaryPage);
    }

    @ApiLogPrint(value = "获取商品详情")
    @ApiOperation(value = "获取商品详情")
    @ApiImplicitParam(value = "库存量单位id", name = "skuId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/detail/{skuId}")
    public ResponseEntity<GoodsDetail> detail(@PathVariable(value = "skuId") Long skuId) {
        GoodsDetail goodsDetail = goodsCenterService.detail(skuId);
        return ResponseEntity.ok(goodsDetail);
    }
}
