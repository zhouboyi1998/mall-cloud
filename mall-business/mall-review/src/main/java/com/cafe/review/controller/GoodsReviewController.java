package com.cafe.review.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.review.model.entity.GoodsReview;
import com.cafe.review.service.GoodsReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * @Package: com.cafe.review.controller
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 商品-评论关联关系接口
 */
@Api(value = "商品-评论关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/goods-review")
public class GoodsReviewController {

    private final GoodsReviewService goodsReviewService;

    @ApiLogPrint(value = "查询商品-评论关联关系数量")
    @ApiOperation(value = "查询商品-评论关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = goodsReviewService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询商品-评论关联关系数量")
    @ApiOperation(value = "根据条件查询商品-评论关联关系数量")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody GoodsReview goodsReview) {
        QueryWrapper<GoodsReview> wrapper = WrapperUtil.createQueryWrapper(goodsReview);
        Integer count = goodsReviewService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询商品-评论关联关系列表")
    @ApiOperation(value = "查询商品-评论关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<GoodsReview>> list() {
        List<GoodsReview> goodsReviewList = goodsReviewService.list();
        return ResponseEntity.ok(goodsReviewList);
    }

    @ApiLogPrint(value = "根据条件查询商品-评论关联关系列表")
    @ApiOperation(value = "根据条件查询商品-评论关联关系列表")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<GoodsReview>> list(@RequestBody GoodsReview goodsReview) {
        QueryWrapper<GoodsReview> wrapper = WrapperUtil.createQueryWrapper(goodsReview);
        List<GoodsReview> goodsReviewList = goodsReviewService.list(wrapper);
        return ResponseEntity.ok(goodsReviewList);
    }

    @ApiLogPrint(value = "分页查询商品-评论关联关系列表")
    @ApiOperation(value = "分页查询商品-评论关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<GoodsReview>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<GoodsReview> page = new Page<>(current, size);
        Page<GoodsReview> goodsReviewPage = goodsReviewService.page(page);
        return ResponseEntity.ok(goodsReviewPage);
    }

    @ApiLogPrint(value = "根据条件分页查询商品-评论关联关系")
    @ApiOperation(value = "根据条件分页查询商品-评论关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<GoodsReview>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody GoodsReview goodsReview
    ) {
        Page<GoodsReview> page = new Page<>(current, size);
        QueryWrapper<GoodsReview> wrapper = WrapperUtil.createQueryWrapper(goodsReview);
        Page<GoodsReview> goodsReviewPage = goodsReviewService.page(page, wrapper);
        return ResponseEntity.ok(goodsReviewPage);
    }

    @ApiLogPrint(value = "根据id查询单个商品-评论关联关系")
    @ApiOperation(value = "根据id查询单个商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<GoodsReview> one(@PathVariable(value = "id") Long id) {
        GoodsReview goodsReview = goodsReviewService.getById(id);
        return ResponseEntity.ok(goodsReview);
    }

    @ApiLogPrint(value = "根据条件查询单个商品-评论关联关系")
    @ApiOperation(value = "根据条件查询单个商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<GoodsReview> one(@RequestBody GoodsReview goodsReview) {
        QueryWrapper<GoodsReview> wrapper = WrapperUtil.createQueryWrapper(goodsReview);
        GoodsReview one = goodsReviewService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增商品-评论关联关系")
    @ApiOperation(value = "新增商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody GoodsReview goodsReview) {
        Boolean code = goodsReviewService.save(goodsReview);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增商品-评论关联关系")
    @ApiOperation(value = "批量新增商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系列表", name = "goodsReviewList", dataType = "List<GoodsReview>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<GoodsReview> goodsReviewList) {
        Boolean code = goodsReviewService.saveBatch(goodsReviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改商品-评论关联关系")
    @ApiOperation(value = "根据id修改商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody GoodsReview goodsReview) {
        Boolean code = goodsReviewService.updateById(goodsReview);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改商品-评论关联关系")
    @ApiOperation(value = "根据ids批量修改商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系列表", name = "goodsReviewList", dataType = "List<GoodsReview>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<GoodsReview> goodsReviewList) {
        Boolean code = goodsReviewService.updateBatchById(goodsReviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除商品-评论关联关系")
    @ApiOperation(value = "根据id删除商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = goodsReviewService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除商品-评论关联关系")
    @ApiOperation(value = "根据ids批量删除商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = goodsReviewService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除商品-评论关联关系")
    @ApiOperation(value = "根据条件批量删除商品-评论关联关系")
    @ApiImplicitParam(value = "商品-评论关联关系Model", name = "goodsReview", dataType = "GoodsReview", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody GoodsReview goodsReview) {
        QueryWrapper<GoodsReview> wrapper = WrapperUtil.createQueryWrapper(goodsReview);
        Boolean code = goodsReviewService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
