package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Spu;
import com.cafe.goods.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Standard Product Unit 标准化产品单元 (控制器)
 */
@Api(value = "Standard Product Unit 标准化产品单元接口")
@RestController
@RequestMapping(value = "/spu")
public class SpuController {

    private SpuService spuService;

    @Autowired
    public SpuController(SpuService spuService) {
        this.spuService = spuService;
    }

    @ApiOperation(value = "查询Standard Product Unit 标准化产品单元列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Spu>> list() {
        List<Spu> spuList = spuService.list();
        return ResponseEntity.ok(spuList);
    }

    @ApiOperation(value = "根据条件查询Standard Product Unit 标准化产品单元列表")
    @ApiImplicitParam(name = "spu", value = "Standard Product Unit 标准化产品单元Model", required = true, paramType = "body", dataType = "Spu")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Spu>> listByWrapper(@RequestBody Spu spu) {
        Wrapper<Spu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(spu);
        List<Spu> spuList = spuService.list(wrapper);
        return ResponseEntity.ok(spuList);
    }

    @ApiOperation(value = "分页查询Standard Product Unit 标准化产品单元列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Spu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Spu> page = new Page<Spu>().setCurrent(current).setSize(size);
        Page<Spu> spuPage = spuService.page(page);
        return ResponseEntity.ok(spuPage);
    }

    @ApiOperation(value = "根据条件分页查询Standard Product Unit 标准化产品单元")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "spu", value = "Standard Product Unit 标准化产品单元Model", required = true, paramType = "body", dataType = "Spu")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Spu>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Spu spu
    ) {
        Page<Spu> page = new Page<Spu>().setCurrent(current).setSize(size);
        Wrapper<Spu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(spu);
        Page<Spu> spuPage = spuService.page(page, wrapper);
        return ResponseEntity.ok(spuPage);
    }

    @ApiOperation(value = "分页查询Standard Product Unit 标准化产品单元列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Spu>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<Spu> page = new Page<Spu>().setCurrent(current).setSize(size);
        Page<Spu> spuPage = spuService.page(page);
        return ResponseEntity.ok(spuPage);
    }

    @ApiOperation(value = "根据条件分页查询Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Spu>")
    @PostMapping(value = "/page")
    public ResponseEntity<Page<Spu>> pageByWrapper(@RequestBody Page<Spu> page) {
        Spu spu = page.getRecords().get(0);
        Wrapper<Spu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(spu);
        Page<Spu> spuPage = spuService.page(page, wrapper);
        return ResponseEntity.ok(spuPage);
    }

    @ApiOperation(value = "根据id查询单个Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "id", value = "Standard Product Unit 标准化产品单元id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Spu> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Spu> wrapper = new LambdaQueryWrapper<Spu>().eq(Spu::getId, id);
        Spu spu = spuService.getOne(wrapper);
        return ResponseEntity.ok(spu);
    }

    @ApiOperation(value = "根据id查询单个Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "id", value = "Standard Product Unit 标准化产品单元id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<Spu> one2(@RequestParam Long id) {
        LambdaQueryWrapper<Spu> wrapper = new LambdaQueryWrapper<Spu>().eq(Spu::getId, id);
        Spu spu = spuService.getOne(wrapper);
        return ResponseEntity.ok(spu);
    }

    @ApiOperation(value = "新增Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "spu", value = "Standard Product Unit 标准化产品单元Model", required = true, paramType = "body", dataType = "Spu")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Spu spu) {
        spu.setCreateTime(LocalDateTime.now());
        spu.setUpdateTime(LocalDateTime.now());
        Boolean code = spuService.save(spu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "spu", value = "Standard Product Unit 标准化产品单元Model", required = true, paramType = "body", dataType = "Spu")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Spu spu) {
        Boolean code = spuService.updateById(spu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "spuList", value = "Standard Product Unit 标准化产品单元列表", required = true, paramType = "body", dataType = "List<Spu>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Spu> spuList) {
        Boolean code = spuService.updateBatchById(spuList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "id", value = "Standard Product Unit 标准化产品单元id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = spuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "id", value = "Standard Product Unit 标准化产品单元id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = spuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除Standard Product Unit 标准化产品单元")
    @ApiImplicitParam(name = "ids", value = "Standard Product Unit 标准化产品单元id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = spuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
