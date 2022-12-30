package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.order.model.DetailGuarantee;
import com.cafe.order.service.DetailGuaranteeService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细-保障关联关系接口
 */
@Api(value = "订单明细-保障关联关系接口")
@RestController
@RequestMapping(value = "/detail-guarantee")
public class DetailGuaranteeController {

    private final DetailGuaranteeService detailGuaranteeService;

    @Autowired
    public DetailGuaranteeController(DetailGuaranteeService detailGuaranteeService) {
        this.detailGuaranteeService = detailGuaranteeService;
    }

    @LogPrint(value = "查询订单明细-保障关联关系数量")
    @ApiOperation(value = "查询订单明细-保障关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = detailGuaranteeService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询订单明细-保障关联关系数量")
    @ApiOperation(value = "根据条件查询订单明细-保障关联关系数量")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody DetailGuarantee detailGuarantee) {
        QueryWrapper<DetailGuarantee> wrapper = WrapperUtil.createQueryWrapper(detailGuarantee);
        Integer count = detailGuaranteeService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询订单明细-保障关联关系列表")
    @ApiOperation(value = "查询订单明细-保障关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<DetailGuarantee>> list() {
        List<DetailGuarantee> detailGuaranteeList = detailGuaranteeService.list();
        return ResponseEntity.ok(detailGuaranteeList);
    }

    @LogPrint(value = "根据条件查询订单明细-保障关联关系列表")
    @ApiOperation(value = "根据条件查询订单明细-保障关联关系列表")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<DetailGuarantee>> list(@RequestBody DetailGuarantee detailGuarantee) {
        QueryWrapper<DetailGuarantee> wrapper = WrapperUtil.createQueryWrapper(detailGuarantee);
        List<DetailGuarantee> detailGuaranteeList = detailGuaranteeService.list(wrapper);
        return ResponseEntity.ok(detailGuaranteeList);
    }

    @LogPrint(value = "分页查询订单明细-保障关联关系列表")
    @ApiOperation(value = "分页查询订单明细-保障关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<DetailGuarantee>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<DetailGuarantee> page = new Page<DetailGuarantee>().setCurrent(current).setSize(size);
        Page<DetailGuarantee> detailGuaranteePage = detailGuaranteeService.page(page);
        return ResponseEntity.ok(detailGuaranteePage);
    }

    @LogPrint(value = "根据条件分页查询订单明细-保障关联关系")
    @ApiOperation(value = "根据条件分页查询订单明细-保障关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<DetailGuarantee>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody DetailGuarantee detailGuarantee
    ) {
        Page<DetailGuarantee> page = new Page<DetailGuarantee>().setCurrent(current).setSize(size);
        QueryWrapper<DetailGuarantee> wrapper = WrapperUtil.createQueryWrapper(detailGuarantee);
        Page<DetailGuarantee> detailGuaranteePage = detailGuaranteeService.page(page, wrapper);
        return ResponseEntity.ok(detailGuaranteePage);
    }

    @LogPrint(value = "根据id查询单个订单明细-保障关联关系")
    @ApiOperation(value = "根据id查询单个订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<DetailGuarantee> one(@PathVariable(value = "id") Long id) {
        DetailGuarantee detailGuarantee = detailGuaranteeService.getById(id);
        return ResponseEntity.ok(detailGuarantee);
    }

    @LogPrint(value = "根据条件查询单个订单明细-保障关联关系")
    @ApiOperation(value = "根据条件查询单个订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<DetailGuarantee> one(@RequestBody DetailGuarantee detailGuarantee) {
        QueryWrapper<DetailGuarantee> wrapper = WrapperUtil.createQueryWrapper(detailGuarantee);
        DetailGuarantee one = detailGuaranteeService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增订单明细-保障关联关系")
    @ApiOperation(value = "新增订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody DetailGuarantee detailGuarantee) {
        LocalDateTime now = LocalDateTime.now();
        detailGuarantee.setCreateTime(now).setUpdateTime(now);
        Boolean code = detailGuaranteeService.save(detailGuarantee);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增订单明细-保障关联关系")
    @ApiOperation(value = "批量新增订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系列表", name = "detailGuaranteeList", dataType = "List<DetailGuarantee>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<DetailGuarantee> detailGuaranteeList) {
        LocalDateTime now = LocalDateTime.now();
        detailGuaranteeList = detailGuaranteeList.stream()
            .map(detailGuarantee -> detailGuarantee.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = detailGuaranteeService.saveBatch(detailGuaranteeList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改订单明细-保障关联关系")
    @ApiOperation(value = "根据id修改订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody DetailGuarantee detailGuarantee) {
        Boolean code = detailGuaranteeService.updateById(detailGuarantee);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改订单明细-保障关联关系")
    @ApiOperation(value = "根据ids批量修改订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系列表", name = "detailGuaranteeList", dataType = "List<DetailGuarantee>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DetailGuarantee> detailGuaranteeList) {
        Boolean code = detailGuaranteeService.updateBatchById(detailGuaranteeList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除订单明细-保障关联关系")
    @ApiOperation(value = "根据id删除订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = detailGuaranteeService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除订单明细-保障关联关系")
    @ApiOperation(value = "根据ids批量删除订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = detailGuaranteeService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除订单明细-保障关联关系")
    @ApiOperation(value = "根据条件批量删除订单明细-保障关联关系")
    @ApiImplicitParam(value = "订单明细-保障关联关系Model", name = "detailGuarantee", dataType = "DetailGuarantee", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody DetailGuarantee detailGuarantee) {
        QueryWrapper<DetailGuarantee> wrapper = WrapperUtil.createQueryWrapper(detailGuarantee);
        Boolean code = detailGuaranteeService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
