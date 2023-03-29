package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.order.model.Order;
import com.cafe.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单接口
 */
@Api(value = "订单接口")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @LogPrint(value = "查询订单数量")
    @ApiOperation(value = "查询订单数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = orderService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询订单数量")
    @ApiOperation(value = "根据条件查询订单数量")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Order order) {
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(order);
        Integer count = orderService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询订单列表")
    @ApiOperation(value = "查询订单列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Order>> list() {
        List<Order> orderList = orderService.list();
        return ResponseEntity.ok(orderList);
    }

    @LogPrint(value = "根据条件查询订单列表")
    @ApiOperation(value = "根据条件查询订单列表")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Order>> list(@RequestBody Order order) {
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(order);
        List<Order> orderList = orderService.list(wrapper);
        return ResponseEntity.ok(orderList);
    }

    @LogPrint(value = "分页查询订单列表")
    @ApiOperation(value = "分页查询订单列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Order>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Order> page = new Page<Order>().setCurrent(current).setSize(size);
        Page<Order> orderPage = orderService.page(page);
        return ResponseEntity.ok(orderPage);
    }

    @LogPrint(value = "根据条件分页查询订单")
    @ApiOperation(value = "根据条件分页查询订单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Order>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Order order
    ) {
        Page<Order> page = new Page<Order>().setCurrent(current).setSize(size);
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(order);
        Page<Order> orderPage = orderService.page(page, wrapper);
        return ResponseEntity.ok(orderPage);
    }

    @LogPrint(value = "根据id查询单个订单")
    @ApiOperation(value = "根据id查询单个订单")
    @ApiImplicitParam(value = "订单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Order> one(@PathVariable(value = "id") Long id) {
        Order order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @LogPrint(value = "根据条件查询单个订单")
    @ApiOperation(value = "根据条件查询单个订单")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Order> one(@RequestBody Order order) {
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(order);
        Order one = orderService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增订单")
    @ApiOperation(value = "新增订单")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Order order) {
        Boolean code = orderService.save(order);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增订单")
    @ApiOperation(value = "批量新增订单")
    @ApiImplicitParam(value = "订单列表", name = "orderList", dataType = "List<Order>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Order> orderList) {
        Boolean code = orderService.saveBatch(orderList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改订单")
    @ApiOperation(value = "根据id修改订单")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Order order) {
        Boolean code = orderService.updateById(order);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改订单")
    @ApiOperation(value = "根据ids批量修改订单")
    @ApiImplicitParam(value = "订单列表", name = "orderList", dataType = "List<Order>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Order> orderList) {
        Boolean code = orderService.updateBatchById(orderList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除订单")
    @ApiOperation(value = "根据id删除订单")
    @ApiImplicitParam(value = "订单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = orderService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除订单")
    @ApiOperation(value = "根据ids批量删除订单")
    @ApiImplicitParam(value = "订单id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = orderService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除订单")
    @ApiOperation(value = "根据条件批量删除订单")
    @ApiImplicitParam(value = "订单Model", name = "order", dataType = "Order", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Order order) {
        QueryWrapper<Order> wrapper = WrapperUtil.createQueryWrapper(order);
        Boolean code = orderService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
