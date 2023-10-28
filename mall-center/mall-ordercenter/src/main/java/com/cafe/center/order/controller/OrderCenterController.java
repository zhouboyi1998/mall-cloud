package com.cafe.center.order.controller;

import com.cafe.center.order.service.OrderCenterService;
import com.cafe.common.core.result.Result;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.merchant.vo.CartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.center.order.controller
 * @Author: zhouboyi
 * @Date: 2023/10/24 17:02
 * @Description: 订单中心接口
 */
@Api(value = "订单中心接口")
@RestController
@RequestMapping(value = "/order-center")
public class OrderCenterController {

    private final OrderCenterService orderCenterService;

    @Autowired
    public OrderCenterController(OrderCenterService orderCenterService) {
        this.orderCenterService = orderCenterService;
    }

    @LogPrint(value = "提交订单")
    @ApiOperation(value = "提交订单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "地址id", name = "addressId", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "购物车视图模型列表", name = "cartVOList", dataType = "List<CartVO>", paramType = "body", required = true)
    })
    @PostMapping(value = "/submit/{addressId}/{channel}/{invoice}")
    public ResponseEntity<Result<Object>> submit(
        @PathVariable(value = "addressId") Long addressId,
        @PathVariable(value = "channel") Integer channel,
        @PathVariable(value = "invoice") Integer invoice,
        @RequestBody List<CartVO> cartVOList
    ) {
        Result<Object> result = orderCenterService.submit(addressId, channel, invoice, cartVOList);
        return ResponseEntity.ok(result);
    }
}
