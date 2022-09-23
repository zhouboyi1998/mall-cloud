package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.goods.model.CategoryBrand;
import com.cafe.goods.service.CategoryBrandService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类-品牌关联 (控制器)
 */
@Api(value = "分类-品牌关联接口")
@RestController
@RequestMapping(value = "/categoryBrand")
public class CategoryBrandController {

    private CategoryBrandService categoryBrandService;

    @Autowired
    public CategoryBrandController(CategoryBrandService categoryBrandService) {
        this.categoryBrandService = categoryBrandService;
    }

    @LogPrint(description = "查询分类-品牌关联列表")
    @ApiOperation(value = "查询分类-品牌关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryBrand>> list() {
        List<CategoryBrand> categoryBrandList = categoryBrandService.list();
        return ResponseEntity.ok(categoryBrandList);
    }

    @LogPrint(description = "根据条件查询分类-品牌关联列表")
    @ApiOperation(value = "根据条件查询分类-品牌关联列表")
    @ApiImplicitParam(name = "categoryBrand", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrand")
    @PostMapping(value = "/list")
    public ResponseEntity<List<CategoryBrand>> list(@RequestBody CategoryBrand categoryBrand) {
        Wrapper<CategoryBrand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(categoryBrand);
        List<CategoryBrand> categoryBrandList = categoryBrandService.list(wrapper);
        return ResponseEntity.ok(categoryBrandList);
    }

    @LogPrint(description = "分页查询分类-品牌关联列表")
    @ApiOperation(value = "分页查询分类-品牌关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<CategoryBrand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<CategoryBrand> page = new Page<CategoryBrand>().setCurrent(current).setSize(size);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @LogPrint(description = "根据条件分页查询分类-品牌关联")
    @ApiOperation(value = "根据条件分页查询分类-品牌关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "categoryBrand", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrand")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<CategoryBrand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody CategoryBrand categoryBrand
    ) {
        Page<CategoryBrand> page = new Page<CategoryBrand>().setCurrent(current).setSize(size);
        Wrapper<CategoryBrand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(categoryBrand);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page, wrapper);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @LogPrint(description = "根据id查询单个分类-品牌关联")
    @ApiOperation(value = "根据id查询单个分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<CategoryBrand> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<CategoryBrand> wrapper = new LambdaQueryWrapper<CategoryBrand>().eq(CategoryBrand::getId, id);
        CategoryBrand categoryBrand = categoryBrandService.getOne(wrapper);
        return ResponseEntity.ok(categoryBrand);
    }

    @LogPrint(description = "新增分类-品牌关联")
    @ApiOperation(value = "新增分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrand", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrand")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody CategoryBrand categoryBrand) {
        categoryBrand.setCreateTime(LocalDateTime.now());
        categoryBrand.setUpdateTime(LocalDateTime.now());
        Boolean code = categoryBrandService.save(categoryBrand);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据id修改分类-品牌关联")
    @ApiOperation(value = "根据id修改分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrand", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrand")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody CategoryBrand categoryBrand) {
        Boolean code = categoryBrandService.updateById(categoryBrand);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据ids批量修改分类-品牌关联")
    @ApiOperation(value = "根据ids批量修改分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrandList", value = "分类-品牌关联列表", required = true, paramType = "body", dataType = "List<CategoryBrand>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<CategoryBrand> categoryBrandList) {
        Boolean code = categoryBrandService.updateBatchById(categoryBrandList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据id删除分类-品牌关联")
    @ApiOperation(value = "根据id删除分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryBrandService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "根据ids批量删除分类-品牌关联")
    @ApiOperation(value = "根据ids批量删除分类-品牌关联")
    @ApiImplicitParam(name = "ids", value = "分类-品牌关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryBrandService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(description = "分页查询分类-品牌关联")
    @ApiOperation(value = "分页查询分类-品牌关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryBrand>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<CategoryBrand> page = new Page<CategoryBrand>().setCurrent(current).setSize(size);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @LogPrint(description = "根据条件分页查询分类-品牌关联")
    @ApiOperation(value = "根据条件分页查询分类-品牌关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "categoryBrand", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrand")
    })
    @PostMapping(value = "/page")
    public ResponseEntity<Page<CategoryBrand>> pageByParam(
        @RequestParam Long current,
        @RequestParam Long size,
        @RequestBody CategoryBrand categoryBrand
    ) {
        Page<CategoryBrand> page = new Page<CategoryBrand>().setCurrent(current).setSize(size);
        Wrapper<CategoryBrand> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(categoryBrand);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page, wrapper);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @LogPrint(description = "根据id查询单个分类-品牌关联")
    @ApiOperation(value = "根据id查询单个分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<CategoryBrand> oneByParam(@RequestParam Long id) {
        LambdaQueryWrapper<CategoryBrand> wrapper = new LambdaQueryWrapper<CategoryBrand>().eq(CategoryBrand::getId, id);
        CategoryBrand categoryBrand = categoryBrandService.getOne(wrapper);
        return ResponseEntity.ok(categoryBrand);
    }

    @LogPrint(description = "根据id删除分类-品牌关联")
    @ApiOperation(value = "根据id删除分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> deleteByParam(@RequestParam Long id) {
        Boolean code = categoryBrandService.removeById(id);
        return ResponseEntity.ok(code);
    }
}
