package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Brand;
import com.cafe.goods.model.Category;
import com.cafe.goods.service.CategoryService;
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
 * @Description: 分类 (控制器)
 */
@Api(value = "分类接口")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "查询分类列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return ResponseEntity.ok(categoryList);
    }

    @ApiOperation(value = "根据条件查询分类列表")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Category>> listByWrapper(@RequestBody Category category) {
        Wrapper<Category> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(category);
        List<Category> categoryList = categoryService.list(wrapper);
        return ResponseEntity.ok(categoryList);
    }

    @ApiOperation(value = "分页查询分类列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<Category>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Category> page = new Page<Category>().setCurrent(current).setSize(size);
        Page<Category> categoryPage = categoryService.page(page);
        return ResponseEntity.ok(categoryPage);
    }

    @ApiOperation(value = "根据条件分页查询分类")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<Category>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Category category
    ) {
        Page<Category> page = new Page<Category>().setCurrent(current).setSize(size);
        Wrapper<Category> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(category);
        Page<Category> categoryPage = categoryService.page(page, wrapper);
        return ResponseEntity.ok(categoryPage);
    }

    @ApiOperation(value = "分页查询分类列表")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Category>")
    @GetMapping(value = "/page")
    public ResponseEntity<IPage<Category>> page(@RequestBody Page<Category> page) {
        Page<Category> categoryPage = categoryService.page(page);
        return ResponseEntity.ok(categoryPage);
    }

    @ApiOperation(value = "根据条件分页查询分类")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Category>")
    @PostMapping(value = "/page")
    public ResponseEntity<IPage<Category>> pageByWrapper(@RequestBody Page<Category> page) {
        Category category = page.getRecords().get(0);
        Wrapper<Category> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(category);
        Page<Category> categoryPage = categoryService.page(page, wrapper);
        return ResponseEntity.ok(categoryPage);
    }

    @ApiOperation(value = "根据id查询单个分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Category> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>().eq(Category::getId, id);
        Category category = categoryService.getOne(wrapper);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "根据id查询单个分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<Category> one2(@RequestParam Long id) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>().eq(Category::getId, id);
        Category category = categoryService.getOne(wrapper);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "新增分类")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Boolean code = categoryService.save(category);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改分类")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Category category) {
        Boolean code = categoryService.updateById(category);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改分类")
    @ApiImplicitParam(name = "categoryList", value = "分类列表", required = true, paramType = "body", dataType = "List<Category>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Category> categoryList) {
        Boolean code = categoryService.updateBatchById(categoryList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = categoryService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除分类")
    @ApiImplicitParam(name = "ids", value = "分类id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
