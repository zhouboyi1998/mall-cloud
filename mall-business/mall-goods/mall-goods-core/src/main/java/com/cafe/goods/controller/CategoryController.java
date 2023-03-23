package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.goods.model.Category;
import com.cafe.goods.service.CategoryService;
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

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类接口
 */
@Api(value = "分类接口")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final Clock clock;

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(Clock clock, CategoryService categoryService) {
        this.clock = clock;
        this.categoryService = categoryService;
    }

    @LogPrint(value = "查询分类数量")
    @ApiOperation(value = "查询分类数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = categoryService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询分类数量")
    @ApiOperation(value = "根据条件查询分类数量")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Category category) {
        QueryWrapper<Category> wrapper = WrapperUtil.createQueryWrapper(category);
        Integer count = categoryService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询分类列表")
    @ApiOperation(value = "查询分类列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return ResponseEntity.ok(categoryList);
    }

    @LogPrint(value = "根据条件查询分类列表")
    @ApiOperation(value = "根据条件查询分类列表")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Category>> list(@RequestBody Category category) {
        QueryWrapper<Category> wrapper = WrapperUtil.createQueryWrapper(category);
        List<Category> categoryList = categoryService.list(wrapper);
        return ResponseEntity.ok(categoryList);
    }

    @LogPrint(value = "分页查询分类列表")
    @ApiOperation(value = "分页查询分类列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Category>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Category> page = new Page<Category>().setCurrent(current).setSize(size);
        Page<Category> categoryPage = categoryService.page(page);
        return ResponseEntity.ok(categoryPage);
    }

    @LogPrint(value = "根据条件分页查询分类")
    @ApiOperation(value = "根据条件分页查询分类")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Category>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Category category
    ) {
        Page<Category> page = new Page<Category>().setCurrent(current).setSize(size);
        QueryWrapper<Category> wrapper = WrapperUtil.createQueryWrapper(category);
        Page<Category> categoryPage = categoryService.page(page, wrapper);
        return ResponseEntity.ok(categoryPage);
    }

    @LogPrint(value = "根据id查询单个分类")
    @ApiOperation(value = "根据id查询单个分类")
    @ApiImplicitParam(value = "分类id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Category> one(@PathVariable(value = "id") Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @LogPrint(value = "根据条件查询单个分类")
    @ApiOperation(value = "根据条件查询单个分类")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Category> one(@RequestBody Category category) {
        QueryWrapper<Category> wrapper = WrapperUtil.createQueryWrapper(category);
        Category one = categoryService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增分类")
    @ApiOperation(value = "新增分类")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Category category) {
        LocalDateTime now = LocalDateTime.now(clock);
        category.setCreateTime(now).setUpdateTime(now);
        Boolean code = categoryService.save(category);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增分类")
    @ApiOperation(value = "批量新增分类")
    @ApiImplicitParam(value = "分类列表", name = "categoryList", dataType = "List<Category>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Category> categoryList) {
        LocalDateTime now = LocalDateTime.now(clock);
        categoryList = categoryList.stream()
            .map(category -> category.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = categoryService.saveBatch(categoryList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改分类")
    @ApiOperation(value = "根据id修改分类")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Category category) {
        Boolean code = categoryService.updateById(category);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改分类")
    @ApiOperation(value = "根据ids批量修改分类")
    @ApiImplicitParam(value = "分类列表", name = "categoryList", dataType = "List<Category>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Category> categoryList) {
        Boolean code = categoryService.updateBatchById(categoryList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除分类")
    @ApiOperation(value = "根据id删除分类")
    @ApiImplicitParam(value = "分类id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除分类")
    @ApiOperation(value = "根据ids批量删除分类")
    @ApiImplicitParam(value = "分类id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除分类")
    @ApiOperation(value = "根据条件批量删除分类")
    @ApiImplicitParam(value = "分类Model", name = "category", dataType = "Category", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Category category) {
        QueryWrapper<Category> wrapper = WrapperUtil.createQueryWrapper(category);
        Boolean code = categoryService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
