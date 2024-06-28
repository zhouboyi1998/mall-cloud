package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.elasticsearch.service.OrderService;
import com.cafe.order.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
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

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description: ElasticSearch 订单接口
 */
@Api(value = "ElasticSearch 订单接口")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @LogPrint(value = "获取订单")
    @ApiOperation(value = "获取订单")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "id") String id) throws IOException {
        GetResponse getResponse = orderService.one(id);
        return ResponseEntity.ok(getResponse);
    }

    @LogPrint(value = "插入订单")
    @ApiOperation(value = "插入订单")
    @ApiImplicitParam(value = "订单Model", name = "orderVO", dataType = "OrderVO", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody OrderVO orderVO) throws IOException {
        IndexResponse indexResponse = orderService.insert(orderVO);
        return ResponseEntity.ok(indexResponse);
    }

    @LogPrint(value = "更新订单")
    @ApiOperation(value = "更新订单")
    @ApiImplicitParam(value = "订单Model", name = "orderVO", dataType = "OrderVO", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody OrderVO orderVO) throws IOException {
        UpdateResponse updateResponse = orderService.update(orderVO);
        return ResponseEntity.ok(updateResponse);
    }

    @LogPrint(value = "删除订单")
    @ApiOperation(value = "删除订单")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "id") String id) throws IOException {
        DeleteResponse deleteResponse = orderService.delete(id);
        return ResponseEntity.ok(deleteResponse);
    }

    @LogPrint(value = "批量插入订单")
    @ApiOperation(value = "批量插入订单")
    @ApiImplicitParam(value = "订单列表", name = "orderVOList", dataType = "List<OrderVO>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<OrderVO> orderVOList) throws IOException {
        BulkResponse bulkResponse = orderService.insertBatch(orderVOList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量更新订单")
    @ApiOperation(value = "批量更新订单")
    @ApiImplicitParam(value = "订单列表", name = "orderVOList", dataType = "List<OrderVO>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<OrderVO> orderVOList) throws IOException {
        BulkResponse bulkResponse = orderService.updateBatch(orderVOList);
        return ResponseEntity.ok(bulkResponse);
    }

    @LogPrint(value = "批量删除订单")
    @ApiOperation(value = "批量删除订单")
    @ApiImplicitParam(value = "ElasticSearch ids", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> ids) throws IOException {
        BulkResponse bulkResponse = orderService.deleteBatch(ids);
        return ResponseEntity.ok(bulkResponse);
    }
}
