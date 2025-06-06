package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.service.OrderItemService;
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
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细接口
 */
@Api(value = "订单明细接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @ApiLogPrint(value = "查询订单明细数量")
    @ApiOperation(value = "查询订单明细数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = orderItemService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询订单明细数量")
    @ApiOperation(value = "根据条件查询订单明细数量")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody OrderItem orderItem) {
        QueryWrapper<OrderItem> wrapper = WrapperUtil.createQueryWrapper(orderItem);
        Integer count = orderItemService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询订单明细列表")
    @ApiOperation(value = "查询订单明细列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderItem>> list() {
        List<OrderItem> orderItemList = orderItemService.list();
        return ResponseEntity.ok(orderItemList);
    }

    @ApiLogPrint(value = "根据条件查询订单明细列表")
    @ApiOperation(value = "根据条件查询订单明细列表")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<OrderItem>> list(@RequestBody OrderItem orderItem) {
        QueryWrapper<OrderItem> wrapper = WrapperUtil.createQueryWrapper(orderItem);
        List<OrderItem> orderItemList = orderItemService.list(wrapper);
        return ResponseEntity.ok(orderItemList);
    }

    @ApiLogPrint(value = "分页查询订单明细列表")
    @ApiOperation(value = "分页查询订单明细列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderItem>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<OrderItem> page = new Page<>(current, size);
        Page<OrderItem> orderItemPage = orderItemService.page(page);
        return ResponseEntity.ok(orderItemPage);
    }

    @ApiLogPrint(value = "根据条件分页查询订单明细")
    @ApiOperation(value = "根据条件分页查询订单明细")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderItem>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody OrderItem orderItem
    ) {
        Page<OrderItem> page = new Page<>(current, size);
        QueryWrapper<OrderItem> wrapper = WrapperUtil.createQueryWrapper(orderItem);
        Page<OrderItem> orderItemPage = orderItemService.page(page, wrapper);
        return ResponseEntity.ok(orderItemPage);
    }

    @ApiLogPrint(value = "根据id查询单个订单明细")
    @ApiOperation(value = "根据id查询单个订单明细")
    @ApiImplicitParam(value = "订单明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<OrderItem> one(@PathVariable(value = "id") Long id) {
        OrderItem orderItem = orderItemService.getById(id);
        return ResponseEntity.ok(orderItem);
    }

    @ApiLogPrint(value = "根据条件查询单个订单明细")
    @ApiOperation(value = "根据条件查询单个订单明细")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<OrderItem> one(@RequestBody OrderItem orderItem) {
        QueryWrapper<OrderItem> wrapper = WrapperUtil.createQueryWrapper(orderItem);
        OrderItem one = orderItemService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增订单明细")
    @ApiOperation(value = "新增订单明细")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody OrderItem orderItem) {
        Boolean code = orderItemService.save(orderItem);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增订单明细")
    @ApiOperation(value = "批量新增订单明细")
    @ApiImplicitParam(value = "订单明细列表", name = "orderItemList", dataType = "List<OrderItem>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<OrderItem> orderItemList) {
        Boolean code = orderItemService.saveBatch(orderItemList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改订单明细")
    @ApiOperation(value = "根据id修改订单明细")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody OrderItem orderItem) {
        Boolean code = orderItemService.updateById(orderItem);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改订单明细")
    @ApiOperation(value = "根据ids批量修改订单明细")
    @ApiImplicitParam(value = "订单明细列表", name = "orderItemList", dataType = "List<OrderItem>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<OrderItem> orderItemList) {
        Boolean code = orderItemService.updateBatchById(orderItemList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除订单明细")
    @ApiOperation(value = "根据id删除订单明细")
    @ApiImplicitParam(value = "订单明细id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = orderItemService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除订单明细")
    @ApiOperation(value = "根据ids批量删除订单明细")
    @ApiImplicitParam(value = "订单明细id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = orderItemService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除订单明细")
    @ApiOperation(value = "根据条件批量删除订单明细")
    @ApiImplicitParam(value = "订单明细Model", name = "orderItem", dataType = "OrderItem", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody OrderItem orderItem) {
        QueryWrapper<OrderItem> wrapper = WrapperUtil.createQueryWrapper(orderItem);
        Boolean code = orderItemService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "评价订单明细")
    @ApiOperation(value = "评价订单明细")
    @ApiImplicitParam(value = "订单明细id", name = "orderItemId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/review/{orderItemId}")
    public ResponseEntity<Boolean> review(@PathVariable(value = "orderItemId") Long orderItemId) {
        Boolean code = orderItemService.review(orderItemId);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量评价订单明细")
    @ApiOperation(value = "批量评价订单明细")
    @ApiImplicitParam(value = "订单明细id列表", name = "orderItemIds", dataType = "List<Long>", paramType = "body", required = true)
    @PutMapping(value = "/review/batch")
    public ResponseEntity<Boolean> reviewBatch(@RequestBody List<Long> orderItemIds) {
        Boolean code = orderItemService.reviewBatch(orderItemIds);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "统计 SKU 销量")
    @ApiOperation(value = "统计 SKU 销量")
    @ApiImplicitParam(value = "SKU ID", name = "skuId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/sale/{skuId}")
    public ResponseEntity<Integer> sale(@PathVariable(value = "skuId") Long skuId) {
        Integer sale = orderItemService.sale(skuId);
        return ResponseEntity.ok(sale);
    }

    @ApiLogPrint(value = "批量统计 SKU 销量")
    @ApiOperation(value = "批量统计 SKU 销量")
    @ApiImplicitParam(value = "SKU ID 列表", name = "skuIds", dataType = "List<Long>", paramType = "body", required = true)
    @PostMapping(value = "/sale/batch")
    public ResponseEntity<Map<Long, Integer>> saleBatch(@RequestBody List<Long> skuIds) {
        Map<Long, Integer> saleMap = orderItemService.saleBatch(skuIds);
        return ResponseEntity.ok(saleMap);
    }
}
