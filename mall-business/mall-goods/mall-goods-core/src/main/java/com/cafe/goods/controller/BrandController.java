package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.goods.model.Brand;
import com.cafe.goods.service.BrandService;
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
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 品牌接口
 */
@Api(value = "品牌接口")
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @LogPrint(value = "查询品牌数量")
    @ApiOperation(value = "查询品牌数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = brandService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询品牌数量")
    @ApiOperation(value = "根据条件查询品牌数量")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Brand brand) {
        QueryWrapper<Brand> wrapper = WrapperUtil.createQueryWrapper(brand);
        Integer count = brandService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询品牌列表")
    @ApiOperation(value = "查询品牌列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Brand>> list() {
        List<Brand> brandList = brandService.list();
        return ResponseEntity.ok(brandList);
    }

    @LogPrint(value = "根据条件查询品牌列表")
    @ApiOperation(value = "根据条件查询品牌列表")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Brand>> list(@RequestBody Brand brand) {
        QueryWrapper<Brand> wrapper = WrapperUtil.createQueryWrapper(brand);
        List<Brand> brandList = brandService.list(wrapper);
        return ResponseEntity.ok(brandList);
    }

    @LogPrint(value = "分页查询品牌列表")
    @ApiOperation(value = "分页查询品牌列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Brand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Brand> page = new Page<Brand>().setCurrent(current).setSize(size);
        Page<Brand> brandPage = brandService.page(page);
        return ResponseEntity.ok(brandPage);
    }

    @LogPrint(value = "根据条件分页查询品牌")
    @ApiOperation(value = "根据条件分页查询品牌")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Brand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Brand brand
    ) {
        Page<Brand> page = new Page<Brand>().setCurrent(current).setSize(size);
        QueryWrapper<Brand> wrapper = WrapperUtil.createQueryWrapper(brand);
        Page<Brand> brandPage = brandService.page(page, wrapper);
        return ResponseEntity.ok(brandPage);
    }

    @LogPrint(value = "根据id查询单个品牌")
    @ApiOperation(value = "根据id查询单个品牌")
    @ApiImplicitParam(value = "品牌id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Brand> one(@PathVariable(value = "id") Long id) {
        Brand brand = brandService.getById(id);
        return ResponseEntity.ok(brand);
    }

    @LogPrint(value = "根据条件查询单个品牌")
    @ApiOperation(value = "根据条件查询单个品牌")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Brand> one(@RequestBody Brand brand) {
        QueryWrapper<Brand> wrapper = WrapperUtil.createQueryWrapper(brand);
        Brand one = brandService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增品牌")
    @ApiOperation(value = "新增品牌")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Brand brand) {
        Boolean code = brandService.save(brand);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增品牌")
    @ApiOperation(value = "批量新增品牌")
    @ApiImplicitParam(value = "品牌列表", name = "brandList", dataType = "List<Brand>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Brand> brandList) {
        Boolean code = brandService.saveBatch(brandList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改品牌")
    @ApiOperation(value = "根据id修改品牌")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Brand brand) {
        Boolean code = brandService.updateById(brand);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改品牌")
    @ApiOperation(value = "根据ids批量修改品牌")
    @ApiImplicitParam(value = "品牌列表", name = "brandList", dataType = "List<Brand>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Brand> brandList) {
        Boolean code = brandService.updateBatchById(brandList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除品牌")
    @ApiOperation(value = "根据id删除品牌")
    @ApiImplicitParam(value = "品牌id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = brandService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除品牌")
    @ApiOperation(value = "根据ids批量删除品牌")
    @ApiImplicitParam(value = "品牌id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = brandService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除品牌")
    @ApiOperation(value = "根据条件批量删除品牌")
    @ApiImplicitParam(value = "品牌Model", name = "brand", dataType = "Brand", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Brand brand) {
        QueryWrapper<Brand> wrapper = WrapperUtil.createQueryWrapper(brand);
        Boolean code = brandService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
