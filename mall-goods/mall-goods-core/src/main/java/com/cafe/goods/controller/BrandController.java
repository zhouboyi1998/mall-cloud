package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Brand;
import com.cafe.goods.service.BrandService;
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
 * @Description: 品牌 (控制器)
 */
@Api(value = "品牌接口")
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @ApiOperation(value = "查询品牌列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Brand>> list() {
        List<Brand> brandList = brandService.list();
        return ResponseEntity.ok(brandList);
    }

    @ApiOperation(value = "根据条件查询品牌列表")
    @ApiImplicitParam(name = "brand", value = "品牌Model", required = true, paramType = "body", dataType = "Brand")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Brand>> listByWrapper(@RequestBody Brand brand) {
        Wrapper<Brand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(brand);
        List<Brand> brandList = brandService.list(wrapper);
        return ResponseEntity.ok(brandList);
    }

    @ApiOperation(value = "分页查询品牌列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<Brand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Brand> page = new Page<Brand>().setCurrent(current).setSize(size);
        Page<Brand> brandPage = brandService.page(page);
        return ResponseEntity.ok(brandPage);
    }

    @ApiOperation(value = "根据条件分页查询品牌")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "brand", value = "品牌Model", required = true, paramType = "body", dataType = "Brand")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<Brand>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Brand brand
    ) {
        Page<Brand> page = new Page<Brand>().setCurrent(current).setSize(size);
        Wrapper<Brand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(brand);
        Page<Brand> brandPage = brandService.page(page, wrapper);
        return ResponseEntity.ok(brandPage);
    }

    @ApiOperation(value = "分页查询品牌列表")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Brand>")
    @GetMapping(value = "/page")
    public ResponseEntity<IPage<Brand>> page(@RequestBody Page<Brand> page) {
        Page<Brand> brandPage = brandService.page(page);
        return ResponseEntity.ok(brandPage);
    }

    @ApiOperation(value = "根据条件分页查询品牌")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Brand>")
    @PostMapping(value = "/page")
    public ResponseEntity<IPage<Brand>> pageByWrapper(@RequestBody Page<Brand> page) {
        Brand brand = page.getRecords().get(0);
        Wrapper<Brand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(brand);
        Page<Brand> brandPage = brandService.page(page, wrapper);
        return ResponseEntity.ok(brandPage);
    }

    @ApiOperation(value = "根据id查询单个品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Brand> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Brand> wrapper = new LambdaQueryWrapper<Brand>().eq(Brand::getId, id);
        Brand brand = brandService.getOne(wrapper);
        return ResponseEntity.ok(brand);
    }

    @ApiOperation(value = "根据id查询单个品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<Brand> one2(@RequestParam Long id) {
        LambdaQueryWrapper<Brand> wrapper = new LambdaQueryWrapper<Brand>().eq(Brand::getId, id);
        Brand brand = brandService.getOne(wrapper);
        return ResponseEntity.ok(brand);
    }

    @ApiOperation(value = "新增品牌")
    @ApiImplicitParam(name = "brand", value = "品牌Model", required = true, paramType = "body", dataType = "Brand")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Brand brand) {
        brand.setCreateTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        Boolean code = brandService.save(brand);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改品牌")
    @ApiImplicitParam(name = "brand", value = "品牌Model", required = true, paramType = "body", dataType = "Brand")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Brand brand) {
        Boolean code = brandService.updateById(brand);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改品牌")
    @ApiImplicitParam(name = "brandList", value = "品牌列表", required = true, paramType = "body", dataType = "List<Brand>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Brand> brandList) {
        Boolean code = brandService.updateBatchById(brandList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = brandService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = brandService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除品牌")
    @ApiImplicitParam(name = "ids", value = "品牌id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = brandService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
