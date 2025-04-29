package com.cafe.ordercenter.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.ordercenter.facade.OrderCenterFacade;
import com.cafe.review.model.query.OrderReviewAndGoodsReviewSaveQuery;
import com.cafe.storage.model.dto.CartDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order-center")
public class OrderCenterController {

    private final OrderCenterFacade orderCenterFacade;

    @ApiLogPrint(value = "提交订单")
    @ApiOperation(value = "提交订单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "地址id", name = "addressId", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "购物车商品列表", name = "cartDTOList", dataType = "List<CartDTO>", paramType = "body", required = true)
    })
    @PostMapping(value = "/submit/{addressId}")
    public ResponseEntity<OrderVO> submit(
        @PathVariable(value = "addressId") Long addressId,
        @RequestBody List<CartDTO> cartDTOList
    ) {
        OrderVO orderVO = orderCenterFacade.submit(addressId, cartDTOList);
        return ResponseEntity.ok(orderVO);
    }

    @ApiLogPrint(value = "评价订单")
    @ApiOperation(value = "评价订单")
    @ApiImplicitParam(value = "保存订单评论和商品评论请求条件", name = "query", dataType = "OrderReviewAndGoodsReviewSaveQuery", paramType = "body", required = true)
    @PostMapping(value = "/review")
    public ResponseEntity<Void> review(@RequestBody OrderReviewAndGoodsReviewSaveQuery query) {
        orderCenterFacade.review(query);
        return ResponseEntity.ok().build();
    }
}
