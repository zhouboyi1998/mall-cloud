package com.cafe.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.order.model.Aftersale;
import com.cafe.order.service.AftersaleService;
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

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后接口
 */
@Api(value = "售后接口")
@RestController
@RequestMapping(value = "/aftersale")
public class AftersaleController {

    private final AftersaleService aftersaleService;

    @Autowired
    public AftersaleController(AftersaleService aftersaleService) {
        this.aftersaleService = aftersaleService;
    }

    @ApiLogPrint(value = "查询售后数量")
    @ApiOperation(value = "查询售后数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = aftersaleService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询售后数量")
    @ApiOperation(value = "根据条件查询售后数量")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Aftersale aftersale) {
        QueryWrapper<Aftersale> wrapper = WrapperUtil.createQueryWrapper(aftersale);
        Integer count = aftersaleService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询售后列表")
    @ApiOperation(value = "查询售后列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Aftersale>> list() {
        List<Aftersale> aftersaleList = aftersaleService.list();
        return ResponseEntity.ok(aftersaleList);
    }

    @ApiLogPrint(value = "根据条件查询售后列表")
    @ApiOperation(value = "根据条件查询售后列表")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Aftersale>> list(@RequestBody Aftersale aftersale) {
        QueryWrapper<Aftersale> wrapper = WrapperUtil.createQueryWrapper(aftersale);
        List<Aftersale> aftersaleList = aftersaleService.list(wrapper);
        return ResponseEntity.ok(aftersaleList);
    }

    @ApiLogPrint(value = "分页查询售后列表")
    @ApiOperation(value = "分页查询售后列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Aftersale>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Aftersale> page = new Page<>(current, size);
        Page<Aftersale> aftersalePage = aftersaleService.page(page);
        return ResponseEntity.ok(aftersalePage);
    }

    @ApiLogPrint(value = "根据条件分页查询售后")
    @ApiOperation(value = "根据条件分页查询售后")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Aftersale>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Aftersale aftersale
    ) {
        Page<Aftersale> page = new Page<>(current, size);
        QueryWrapper<Aftersale> wrapper = WrapperUtil.createQueryWrapper(aftersale);
        Page<Aftersale> aftersalePage = aftersaleService.page(page, wrapper);
        return ResponseEntity.ok(aftersalePage);
    }

    @ApiLogPrint(value = "根据id查询单个售后")
    @ApiOperation(value = "根据id查询单个售后")
    @ApiImplicitParam(value = "售后id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Aftersale> one(@PathVariable(value = "id") Long id) {
        Aftersale aftersale = aftersaleService.getById(id);
        return ResponseEntity.ok(aftersale);
    }

    @ApiLogPrint(value = "根据条件查询单个售后")
    @ApiOperation(value = "根据条件查询单个售后")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Aftersale> one(@RequestBody Aftersale aftersale) {
        QueryWrapper<Aftersale> wrapper = WrapperUtil.createQueryWrapper(aftersale);
        Aftersale one = aftersaleService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增售后")
    @ApiOperation(value = "新增售后")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Aftersale aftersale) {
        Boolean code = aftersaleService.save(aftersale);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增售后")
    @ApiOperation(value = "批量新增售后")
    @ApiImplicitParam(value = "售后列表", name = "aftersaleList", dataType = "List<Aftersale>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Aftersale> aftersaleList) {
        Boolean code = aftersaleService.saveBatch(aftersaleList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改售后")
    @ApiOperation(value = "根据id修改售后")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Aftersale aftersale) {
        Boolean code = aftersaleService.updateById(aftersale);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改售后")
    @ApiOperation(value = "根据ids批量修改售后")
    @ApiImplicitParam(value = "售后列表", name = "aftersaleList", dataType = "List<Aftersale>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Aftersale> aftersaleList) {
        Boolean code = aftersaleService.updateBatchById(aftersaleList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除售后")
    @ApiOperation(value = "根据id删除售后")
    @ApiImplicitParam(value = "售后id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = aftersaleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除售后")
    @ApiOperation(value = "根据ids批量删除售后")
    @ApiImplicitParam(value = "售后id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = aftersaleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除售后")
    @ApiOperation(value = "根据条件批量删除售后")
    @ApiImplicitParam(value = "售后Model", name = "aftersale", dataType = "Aftersale", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Aftersale aftersale) {
        QueryWrapper<Aftersale> wrapper = WrapperUtil.createQueryWrapper(aftersale);
        Boolean code = aftersaleService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
