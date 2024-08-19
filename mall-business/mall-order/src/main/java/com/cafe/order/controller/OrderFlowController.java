package com.cafe.order.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.order.model.OrderItem;
import com.cafe.order.service.OrderFlowService;
import com.cafe.order.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2023/10/27 14:55
 * @Description: 订单流转接口
 */
@Api(value = "订单流转接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order-flow")
public class OrderFlowController {

    private final OrderFlowService orderFlowService;

    @ApiLogPrint(value = "保存订单")
    @ApiOperation(value = "保存订单")
    @ApiImplicitParam(value = "订单VO", name = "orderVO", dataType = "OrderVO", paramType = "body", required = true)
    @PostMapping(value = "/save")
    public ResponseEntity<OrderVO> save(@RequestBody OrderVO orderVO) {
        OrderVO newOrderVO = orderFlowService.save(orderVO);
        return ResponseEntity.ok(newOrderVO);
    }

    @ApiLogPrint(value = "取消超时未支付的订单")
    @ApiOperation(value = "取消超时未支付的订单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "当前时间", name = "now", dataType = "LocalDateTime", paramType = "path", required = true),
        @ApiImplicitParam(value = "下单未支付时长 (单位: 分钟)", name = "duration", dataType = "Integer", paramType = "path", required = true)
    })
    @PutMapping(value = "/cancel/{now}/{duration}")
    public ResponseEntity<List<OrderItem>> cancel(
        @PathVariable(value = "now") LocalDateTime now,
        @PathVariable(value = "duration") Integer duration
    ) {
        List<OrderItem> orderItemList = orderFlowService.cancel(now, duration);
        return ResponseEntity.ok(orderItemList);
    }
}
