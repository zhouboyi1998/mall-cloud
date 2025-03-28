package com.cafe.elasticsearch.controller;

import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.elasticsearch.model.index.OrderIndex;
import com.cafe.elasticsearch.service.OrderIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(value = "订单ID", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderIndex> one(@PathVariable(value = "id") Long id) {
        OrderIndex orderIndex = orderIndexService.one(id);
        return ResponseEntity.ok(orderIndex);
    }

    @ApiLogPrint(value = "插入订单索引")
    @ApiOperation(value = "插入订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PostMapping(value = "")
    public ResponseEntity<OrderIndex> insert(@RequestBody OrderIndex orderIndex) {
        OrderIndex savedOrderIndex = orderIndexService.insert(orderIndex);
        return ResponseEntity.ok(savedOrderIndex);
    }

    @ApiLogPrint(value = "更新订单索引")
    @ApiOperation(value = "更新订单索引")
    @ApiImplicitParam(value = "订单索引", name = "orderIndex", dataType = "OrderIndex", paramType = "body", required = true)
    @PutMapping(value = "")
    public ResponseEntity<OrderIndex> update(@RequestBody OrderIndex orderIndex) {
        OrderIndex updatedOrderIndex = orderIndexService.update(orderIndex);
        return ResponseEntity.ok(updatedOrderIndex);
    }

    @ApiLogPrint(value = "删除订单索引")
    @ApiOperation(value = "删除订单索引")
    @ApiImplicitParam(value = "订单ID", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        orderIndexService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "批量插入订单索引")
    @ApiOperation(value = "批量插入订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PostMapping(value = "/batch")
    public ResponseEntity<List<OrderIndex>> insertBatch(@RequestBody List<OrderIndex> orderIndexList) {
        List<OrderIndex> savedOrderIndexList = orderIndexService.insertBatch(orderIndexList);
        return ResponseEntity.ok(savedOrderIndexList);
    }

    @ApiLogPrint(value = "批量更新订单索引")
    @ApiOperation(value = "批量更新订单索引")
    @ApiImplicitParam(value = "订单索引列表", name = "orderIndexList", dataType = "List<OrderIndex>", paramType = "body", required = true)
    @PutMapping(value = "/batch")
    public ResponseEntity<List<OrderIndex>> updateBatch(@RequestBody List<OrderIndex> orderIndexList) {
        List<OrderIndex> updatedOrderIndexList = orderIndexService.updateBatch(orderIndexList);
        return ResponseEntity.ok(updatedOrderIndexList);
    }

    @ApiLogPrint(value = "批量删除订单索引")
    @ApiOperation(value = "批量删除订单索引")
    @ApiImplicitParam(value = "订单ID列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/batch")
    public ResponseEntity<Void> deleteBatch(@RequestBody List<Long> ids) {
        orderIndexService.deleteBatch(ids);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "分页查询订单索引")
    @ApiOperation(value = "分页查询订单索引")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页数量", name = "size", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序字段", name = "sort", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序规则", name = "rule", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<OrderIndex>> page(
        @RequestParam(value = "current") Integer current,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "sort") String sort,
        @RequestParam(value = "rule") String rule
    ) {
        PageRequest pageRequest = PageRequest.of(current - 1, size, Sort.by(Sort.Direction.fromString(rule), sort));
        Page<OrderIndex> page = orderIndexService.page(pageRequest);
        return ResponseEntity.ok(page);
    }

    @ApiLogPrint(value = "搜索订单索引")
    @ApiOperation(value = "搜索订单索引")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "关键词", name = "keyword", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页数量", name = "size", dataType = "Integer", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序字段", name = "sort", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "排序规则", name = "rule", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/search")
    public ResponseEntity<Page<OrderIndex>> search(
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "current") Integer current,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "sort") String sort,
        @RequestParam(value = "rule") String rule
    ) {
        PageRequest pageRequest = PageRequest.of(current - 1, size, Sort.by(Sort.Direction.fromString(rule), sort));
        Page<OrderIndex> page = orderIndexService.search(keyword, pageRequest);
        return ResponseEntity.ok(page);
    }
}
