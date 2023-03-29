package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.order.model.OrderDetail;
import com.cafe.order.service.OrderDetailService;
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
 * @Description: 订单明细表接口
 */
@Api(value = "订单明细表接口")
@RestController
@RequestMapping(value = "/order-detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @LogPrint(value = "查询订单明细表数量")
    @ApiOperation(value = "查询订单明细表数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = orderDetailService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询订单明细表数量")
    @ApiOperation(value = "根据条件查询订单明细表数量")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> wrapper = WrapperUtil.createQueryWrapper(orderDetail);
        Integer count = orderDetailService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询订单明细表列表")
    @ApiOperation(value = "查询订单明细表列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<OrderDetail>> list() {
        List<OrderDetail> orderDetailList = orderDetailService.list();
        return ResponseEntity.ok(orderDetailList);
    }

    @LogPrint(value = "根据条件查询订单明细表列表")
    @ApiOperation(value = "根据条件查询订单明细表列表")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<OrderDetail>> list(@RequestBody OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> wrapper = WrapperUtil.createQueryWrapper(orderDetail);
        List<OrderDetail> orderDetailList = orderDetailService.list(wrapper);
        return ResponseEntity.ok(orderDetailList);
    }

    @LogPrint(value = "分页查询订单明细表列表")
    @ApiOperation(value = "分页查询订单明细表列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderDetail>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<OrderDetail> page = new Page<OrderDetail>().setCurrent(current).setSize(size);
        Page<OrderDetail> orderDetailPage = orderDetailService.page(page);
        return ResponseEntity.ok(orderDetailPage);
    }

    @LogPrint(value = "根据条件分页查询订单明细表")
    @ApiOperation(value = "根据条件分页查询订单明细表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<OrderDetail>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody OrderDetail orderDetail
    ) {
        Page<OrderDetail> page = new Page<OrderDetail>().setCurrent(current).setSize(size);
        QueryWrapper<OrderDetail> wrapper = WrapperUtil.createQueryWrapper(orderDetail);
        Page<OrderDetail> orderDetailPage = orderDetailService.page(page, wrapper);
        return ResponseEntity.ok(orderDetailPage);
    }

    @LogPrint(value = "根据id查询单个订单明细表")
    @ApiOperation(value = "根据id查询单个订单明细表")
    @ApiImplicitParam(value = "订单明细表id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<OrderDetail> one(@PathVariable(value = "id") Long id) {
        OrderDetail orderDetail = orderDetailService.getById(id);
        return ResponseEntity.ok(orderDetail);
    }

    @LogPrint(value = "根据条件查询单个订单明细表")
    @ApiOperation(value = "根据条件查询单个订单明细表")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<OrderDetail> one(@RequestBody OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> wrapper = WrapperUtil.createQueryWrapper(orderDetail);
        OrderDetail one = orderDetailService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增订单明细表")
    @ApiOperation(value = "新增订单明细表")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody OrderDetail orderDetail) {
        Boolean code = orderDetailService.save(orderDetail);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增订单明细表")
    @ApiOperation(value = "批量新增订单明细表")
    @ApiImplicitParam(value = "订单明细表列表", name = "orderDetailList", dataType = "List<OrderDetail>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<OrderDetail> orderDetailList) {
        Boolean code = orderDetailService.saveBatch(orderDetailList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改订单明细表")
    @ApiOperation(value = "根据id修改订单明细表")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody OrderDetail orderDetail) {
        Boolean code = orderDetailService.updateById(orderDetail);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改订单明细表")
    @ApiOperation(value = "根据ids批量修改订单明细表")
    @ApiImplicitParam(value = "订单明细表列表", name = "orderDetailList", dataType = "List<OrderDetail>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<OrderDetail> orderDetailList) {
        Boolean code = orderDetailService.updateBatchById(orderDetailList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除订单明细表")
    @ApiOperation(value = "根据id删除订单明细表")
    @ApiImplicitParam(value = "订单明细表id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = orderDetailService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除订单明细表")
    @ApiOperation(value = "根据ids批量删除订单明细表")
    @ApiImplicitParam(value = "订单明细表id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = orderDetailService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除订单明细表")
    @ApiOperation(value = "根据条件批量删除订单明细表")
    @ApiImplicitParam(value = "订单明细表Model", name = "orderDetail", dataType = "OrderDetail", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> wrapper = WrapperUtil.createQueryWrapper(orderDetail);
        Boolean code = orderDetailService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
