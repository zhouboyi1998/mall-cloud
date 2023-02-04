package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.order.model.AfterSale;
import com.cafe.order.service.AfterSaleService;
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
 * @Description: 售后接口
 */
@Api(value = "售后接口")
@RestController
@RequestMapping(value = "/after-sale")
public class AfterSaleController {

    private final AfterSaleService afterSaleService;

    @Autowired
    public AfterSaleController(AfterSaleService afterSaleService) {
        this.afterSaleService = afterSaleService;
    }

    @LogPrint(value = "查询售后数量")
    @ApiOperation(value = "查询售后数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = afterSaleService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询售后数量")
    @ApiOperation(value = "根据条件查询售后数量")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody AfterSale afterSale) {
        QueryWrapper<AfterSale> wrapper = WrapperUtil.createQueryWrapper(afterSale);
        Integer count = afterSaleService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询售后列表")
    @ApiOperation(value = "查询售后列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<AfterSale>> list() {
        List<AfterSale> afterSaleList = afterSaleService.list();
        return ResponseEntity.ok(afterSaleList);
    }

    @LogPrint(value = "根据条件查询售后列表")
    @ApiOperation(value = "根据条件查询售后列表")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<AfterSale>> list(@RequestBody AfterSale afterSale) {
        QueryWrapper<AfterSale> wrapper = WrapperUtil.createQueryWrapper(afterSale);
        List<AfterSale> afterSaleList = afterSaleService.list(wrapper);
        return ResponseEntity.ok(afterSaleList);
    }

    @LogPrint(value = "分页查询售后列表")
    @ApiOperation(value = "分页查询售后列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<AfterSale>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<AfterSale> page = new Page<AfterSale>().setCurrent(current).setSize(size);
        Page<AfterSale> afterSalePage = afterSaleService.page(page);
        return ResponseEntity.ok(afterSalePage);
    }

    @LogPrint(value = "根据条件分页查询售后")
    @ApiOperation(value = "根据条件分页查询售后")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<AfterSale>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody AfterSale afterSale
    ) {
        Page<AfterSale> page = new Page<AfterSale>().setCurrent(current).setSize(size);
        QueryWrapper<AfterSale> wrapper = WrapperUtil.createQueryWrapper(afterSale);
        Page<AfterSale> afterSalePage = afterSaleService.page(page, wrapper);
        return ResponseEntity.ok(afterSalePage);
    }

    @LogPrint(value = "根据id查询单个售后")
    @ApiOperation(value = "根据id查询单个售后")
    @ApiImplicitParam(value = "售后id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AfterSale> one(@PathVariable(value = "id") Long id) {
        AfterSale afterSale = afterSaleService.getById(id);
        return ResponseEntity.ok(afterSale);
    }

    @LogPrint(value = "根据条件查询单个售后")
    @ApiOperation(value = "根据条件查询单个售后")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<AfterSale> one(@RequestBody AfterSale afterSale) {
        QueryWrapper<AfterSale> wrapper = WrapperUtil.createQueryWrapper(afterSale);
        AfterSale one = afterSaleService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增售后")
    @ApiOperation(value = "新增售后")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody AfterSale afterSale) {
        LocalDateTime now = LocalDateTime.now();
        afterSale.setCreateTime(now).setUpdateTime(now);
        Boolean code = afterSaleService.save(afterSale);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增售后")
    @ApiOperation(value = "批量新增售后")
    @ApiImplicitParam(value = "售后列表", name = "afterSaleList", dataType = "List<AfterSale>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<AfterSale> afterSaleList) {
        LocalDateTime now = LocalDateTime.now();
        afterSaleList = afterSaleList.stream()
            .map(afterSale -> afterSale.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = afterSaleService.saveBatch(afterSaleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改售后")
    @ApiOperation(value = "根据id修改售后")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody AfterSale afterSale) {
        Boolean code = afterSaleService.updateById(afterSale);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改售后")
    @ApiOperation(value = "根据ids批量修改售后")
    @ApiImplicitParam(value = "售后列表", name = "afterSaleList", dataType = "List<AfterSale>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<AfterSale> afterSaleList) {
        Boolean code = afterSaleService.updateBatchById(afterSaleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除售后")
    @ApiOperation(value = "根据id删除售后")
    @ApiImplicitParam(value = "售后id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = afterSaleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除售后")
    @ApiOperation(value = "根据ids批量删除售后")
    @ApiImplicitParam(value = "售后id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = afterSaleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除售后")
    @ApiOperation(value = "根据条件批量删除售后")
    @ApiImplicitParam(value = "售后Model", name = "afterSale", dataType = "AfterSale", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody AfterSale afterSale) {
        QueryWrapper<AfterSale> wrapper = WrapperUtil.createQueryWrapper(afterSale);
        Boolean code = afterSaleService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
