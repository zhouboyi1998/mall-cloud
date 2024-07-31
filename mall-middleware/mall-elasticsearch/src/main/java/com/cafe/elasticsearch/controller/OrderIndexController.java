package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.elasticsearch.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
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
 * @Description: 订单全文索引接口
 */
@Api(value = "订单全文索引接口")
@RestController
@RequestMapping(value = "/order-index")
public class OrderIndexController {

    private final OrderIndexService orderIndexService;

    @Autowired
    public OrderIndexController(OrderIndexService orderIndexService) {
        this.orderIndexService = orderIndexService;
    }

    @ApiLogPrint(value = "获取订单索引")
    @ApiOperation(value = "获取订单索引")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<GetResponse> one(@PathVariable(value = "id") String id) throws IOException {
        GetResponse getResponse = orderIndexService.one(id);
        return ResponseEntity.ok(getResponse);
    }

    @ApiLogPrint(value = "插入订单索引")
    @ApiOperation(value = "插入订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<IndexResponse> insert(@RequestBody OrderIndex orderIndex) throws IOException {
        IndexResponse indexResponse = orderIndexService.insert(orderIndex);
        return ResponseEntity.ok(indexResponse);
    }

    @ApiLogPrint(value = "更新订单索引")
    @ApiOperation(value = "更新订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<UpdateResponse> update(@RequestBody OrderIndex orderIndex) throws IOException {
        UpdateResponse updateResponse = orderIndexService.update(orderIndex);
        return ResponseEntity.ok(updateResponse);
    }

    @ApiLogPrint(value = "删除订单索引")
    @ApiOperation(value = "删除订单索引")
    @ApiImplicitParam(value = "ElasticSearch id", name = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable(value = "id") String id) throws IOException {
        DeleteResponse deleteResponse = orderIndexService.delete(id);
        return ResponseEntity.ok(deleteResponse);
    }

    @ApiLogPrint(value = "批量插入订单索引")
    @ApiOperation(value = "批量插入订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<BulkResponse> insertBatch(@RequestBody List<OrderIndex> orderIndexList) throws IOException {
        BulkResponse bulkResponse = orderIndexService.insertBatch(orderIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "批量更新订单索引")
    @ApiOperation(value = "批量更新订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<BulkResponse> updateBatch(@RequestBody List<OrderIndex> orderIndexList) throws IOException {
        BulkResponse bulkResponse = orderIndexService.updateBatch(orderIndexList);
        return ResponseEntity.ok(bulkResponse);
    }

    @ApiLogPrint(value = "批量删除订单索引")
    @ApiOperation(value = "批量删除订单索引")
    @ApiImplicitParam(value = "ElasticSearch ids", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<BulkResponse> deleteBatch(@RequestBody List<String> ids) throws IOException {
        BulkResponse bulkResponse = orderIndexService.deleteBatch(ids);
        return ResponseEntity.ok(bulkResponse);
    }
}
