package com.cafe.review.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.review.facade.OrderReviewFacade;
import com.cafe.review.model.entity.OrderReview;
import com.cafe.review.model.query.OrderReviewSaveQuery;
import com.cafe.review.service.OrderReviewService;
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
 * @Description: 订单-评论关联关系接口
 */
@Api(value = "订单-评论关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order-review")
public class OrderReviewController {

    private final OrderReviewService orderReviewService;

    private final OrderReviewFacade orderReviewFacade;

    @ApiLogPrint(value = "查询订单-评论关联关系数量")
    @ApiOperation(value = "查询订单-评论关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = orderReviewService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询订单-评论关联关系数量")
    @ApiOperation(value = "根据条件查询订单-评论关联关系数量")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody OrderReview orderReview) {
        QueryWrapper<OrderReview> wrapper = WrapperUtil.createQueryWrapper(orderReview);
        Integer count = orderReviewService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询订单-评论关联关系列表")
    @ApiOperation(value = "查询订单-评论关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderReview>> list() {
        List<OrderReview> orderReviewList = orderReviewService.list();
        return ResponseEntity.ok(orderReviewList);
    }

    @ApiLogPrint(value = "根据条件查询订单-评论关联关系列表")
    @ApiOperation(value = "根据条件查询订单-评论关联关系列表")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<OrderReview>> list(@RequestBody OrderReview orderReview) {
        QueryWrapper<OrderReview> wrapper = WrapperUtil.createQueryWrapper(orderReview);
        List<OrderReview> orderReviewList = orderReviewService.list(wrapper);
        return ResponseEntity.ok(orderReviewList);
    }

    @ApiLogPrint(value = "分页查询订单-评论关联关系列表")
    @ApiOperation(value = "分页查询订单-评论关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderReview>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<OrderReview> page = new Page<>(current, size);
        Page<OrderReview> orderReviewPage = orderReviewService.page(page);
        return ResponseEntity.ok(orderReviewPage);
    }

    @ApiLogPrint(value = "根据条件分页查询订单-评论关联关系")
    @ApiOperation(value = "根据条件分页查询订单-评论关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderReview>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody OrderReview orderReview
    ) {
        Page<OrderReview> page = new Page<>(current, size);
        QueryWrapper<OrderReview> wrapper = WrapperUtil.createQueryWrapper(orderReview);
        Page<OrderReview> orderReviewPage = orderReviewService.page(page, wrapper);
        return ResponseEntity.ok(orderReviewPage);
    }

    @ApiLogPrint(value = "根据id查询单个订单-评论关联关系")
    @ApiOperation(value = "根据id查询单个订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<OrderReview> one(@PathVariable(value = "id") Long id) {
        OrderReview orderReview = orderReviewService.getById(id);
        return ResponseEntity.ok(orderReview);
    }

    @ApiLogPrint(value = "根据条件查询单个订单-评论关联关系")
    @ApiOperation(value = "根据条件查询单个订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<OrderReview> one(@RequestBody OrderReview orderReview) {
        QueryWrapper<OrderReview> wrapper = WrapperUtil.createQueryWrapper(orderReview);
        OrderReview one = orderReviewService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增订单-评论关联关系")
    @ApiOperation(value = "新增订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody OrderReview orderReview) {
        Boolean code = orderReviewService.save(orderReview);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增订单-评论关联关系")
    @ApiOperation(value = "批量新增订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系列表", name = "orderReviewList", dataType = "List<OrderReview>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<OrderReview> orderReviewList) {
        Boolean code = orderReviewService.saveBatch(orderReviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改订单-评论关联关系")
    @ApiOperation(value = "根据id修改订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody OrderReview orderReview) {
        Boolean code = orderReviewService.updateById(orderReview);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改订单-评论关联关系")
    @ApiOperation(value = "根据ids批量修改订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系列表", name = "orderReviewList", dataType = "List<OrderReview>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<OrderReview> orderReviewList) {
        Boolean code = orderReviewService.updateBatchById(orderReviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除订单-评论关联关系")
    @ApiOperation(value = "根据id删除订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = orderReviewService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除订单-评论关联关系")
    @ApiOperation(value = "根据ids批量删除订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = orderReviewService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除订单-评论关联关系")
    @ApiOperation(value = "根据条件批量删除订单-评论关联关系")
    @ApiImplicitParam(value = "订单-评论关联关系Model", name = "orderReview", dataType = "OrderReview", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody OrderReview orderReview) {
        QueryWrapper<OrderReview> wrapper = WrapperUtil.createQueryWrapper(orderReview);
        Boolean code = orderReviewService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "保存订单评论")
    @ApiOperation(value = "保存订单评论")
    @ApiImplicitParam(value = "保存订单评论请求", name = "query", dataType = "OrderReviewSaveQuery", paramType = "body", required = true)
    @PostMapping(value = "/review")
    public ResponseEntity<Boolean> review(@RequestBody OrderReviewSaveQuery query) {
        Boolean code = orderReviewFacade.review(query);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量保存订单评论")
    @ApiOperation(value = "批量保存订单评论")
    @ApiImplicitParam(value = "保存订单评论请求列表", name = "queryList", dataType = "List<OrderReviewSaveQuery>", paramType = "body", required = true)
    @PostMapping(value = "/review/batch")
    public ResponseEntity<Boolean> reviewBatch(@RequestBody List<OrderReviewSaveQuery> queryList) {
        Boolean code = orderReviewFacade.reviewBatch(queryList);
        return ResponseEntity.ok(code);
    }
}
