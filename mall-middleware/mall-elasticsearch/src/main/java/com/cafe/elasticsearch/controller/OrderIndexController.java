package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * @Package: com.cafe.elasticsearch.controller
 * @Author: zhouboyi
 * @Date: 2024/6/28 16:18
 * @Description: 订单全文索引接口
 */
@Api(value = "订单全文索引接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order-index")
public class OrderIndexController {

    private final OrderIndexService orderIndexService;

    @ApiLogPrint(value = "获取订单索引")
    @ApiOperation(value = "获取订单索引")
    @ApiImplicitParam(value = "订单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderIndex> one(@PathVariable(value = "id") Long id) {
        OrderIndex orderIndex = orderIndexService.one(id);
        return ResponseEntity.ok(orderIndex);
    }

    @ApiLogPrint(value = "保存订单索引")
    @ApiOperation(value = "保存订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<OrderIndex> save(@RequestBody OrderIndex orderIndex) {
        OrderIndex save = orderIndexService.save(orderIndex);
        return ResponseEntity.ok(save);
    }

    @ApiLogPrint(value = "批量保存订单索引")
    @ApiOperation(value = "批量保存订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<List<OrderIndex>> saveBatch(@RequestBody List<OrderIndex> orderIndexList) {
        List<OrderIndex> saveList = orderIndexService.saveBatch(orderIndexList);
        return ResponseEntity.ok(saveList);
    }

    @ApiLogPrint(value = "修改订单索引")
    @ApiOperation(value = "修改订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<OrderIndex> update(@RequestBody OrderIndex orderIndex) {
        OrderIndex update = orderIndexService.update(orderIndex);
        return ResponseEntity.ok(update);
    }

    @ApiLogPrint(value = "批量修改订单索引")
    @ApiOperation(value = "批量修改订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<List<OrderIndex>> updateBatch(@RequestBody List<OrderIndex> orderIndexList) {
        List<OrderIndex> updateList = orderIndexService.updateBatch(orderIndexList);
        return ResponseEntity.ok(updateList);
    }

    @ApiLogPrint(value = "删除订单索引")
    @ApiOperation(value = "删除订单索引")
    @ApiImplicitParam(value = "订单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        orderIndexService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "批量删除订单索引")
    @ApiOperation(value = "批量删除订单索引")
    @ApiImplicitParam(value = "订单id列表", name = "ids", dataType = "List<String>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<Long> ids) {
        orderIndexService.deleteBatch(ids);
        return ResponseEntity.ok().build();
    }
}
