package com.cafe.ordercenter.controller;

import com.cafe.common.constant.kafka.KafkaConstant;
import com.cafe.common.kafka.producer.KafkaProducer;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.order.vo.OrderVO;
import com.cafe.ordercenter.service.OrderCenterService;
import com.cafe.storage.vo.CartVO;
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
 * @Package: com.cafe.ordercenter.controller
 * @Author: zhouboyi
 * @Date: 2023/10/24 17:02
 * @Description: 订单中心接口
 */
@Api(value = "订单中心接口")
@RestController
@RequestMapping(value = "/order-center")
public class OrderCenterController {

    private final OrderCenterService orderCenterService;

    private final KafkaProducer kafkaProducer;

    @Autowired
    public OrderCenterController(OrderCenterService orderCenterService, KafkaProducer kafkaProducer) {
        this.orderCenterService = orderCenterService;
        this.kafkaProducer = kafkaProducer;
    }

    @ApiLogPrint(value = "提交订单")
    @ApiOperation(value = "提交订单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "地址id", name = "addressId", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "购物车视图模型列表", name = "cartVOList", dataType = "List<CartVO>", paramType = "body", required = true)
    })
    @PostMapping(value = "/submit/{addressId}/{channel}/{invoice}")
    public ResponseEntity<OrderVO> submit(
        @PathVariable(value = "addressId") Long addressId,
        @PathVariable(value = "channel") Integer channel,
        @PathVariable(value = "invoice") Integer invoice,
        @RequestBody List<CartVO> cartVOList
    ) {
        OrderVO orderVO = orderCenterService.submit(addressId, channel, invoice, cartVOList);
        // 发送消息到 Kafka
        kafkaProducer.send(KafkaConstant.Topic.ORDER_INDEX, orderVO);
        return ResponseEntity.ok(orderVO);
    }
}
