package com.cafe.search.elasticsearch.controller;

import com.cafe.search.elasticsearch.constant.ElasticSearchConstant;
import com.cafe.search.elasticsearch.service.GoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:17
 * @Description:
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "size", value = "每页数据数量", required = true, paramType = "path", dataType = "Integer"),
        @ApiImplicitParam(name = "keyword", value = "关键词", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sort", value = "排序属性", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "rule", value = "排序规则", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping("/search/{current}/{size}")
    public ResponseEntity<SearchResponse> search(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT_NAME) String sort,
        @RequestParam(required = false, defaultValue = ElasticSearchConstant.GOODS_DEFAULT_SORT_RULE) String rule
    ) throws IOException {
        return ResponseEntity.ok(goodsService.search(current, size, keyword, sort, rule));
    }

    @ApiOperation(value = "批量导入商品数据")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页数据数量", required = true, paramType = "path", dataType = "Long")
    })
    @PostMapping("/import/{current}/{size}")
    public ResponseEntity<BulkResponse> importBatch(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) throws IOException {
        return ResponseEntity.ok(goodsService.importBatch(current, size));
    }
}
