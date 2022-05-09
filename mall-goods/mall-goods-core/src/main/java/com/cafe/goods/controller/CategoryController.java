package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Category;
import com.cafe.goods.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "查询分类列表")
    @GetMapping("/list")
    public ResponseEntity<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return ResponseEntity.ok(categoryList);
    }

    @ApiOperation(value = "根据条件查询分类列表")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PostMapping("/list")
    public ResponseEntity<List<Category>> listByWrapper(@RequestBody Category category) {
        Wrapper<Category> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(category);
        List<Category> categoryList = categoryService.list(wrapper);
        return ResponseEntity.ok(categoryList);
    }

    @ApiOperation("分页查询分类列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Category>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Category> page = new Page<Category>().setCurrent(current).setSize(size);
        Page<Category> categoryPage = categoryService.page(page);
        return ResponseEntity.ok(categoryPage);
    }

    @ApiOperation(value = "根据条件分页查询分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    })
    @PostMapping("/page/{current}/{size}")
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

    @ApiOperation(value = "根据id查询单个分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/one/{id}")
    public ResponseEntity<Category> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>().eq(Category::getId, id);
        Category category = categoryService.getOne(wrapper);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "新增分类")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Category category) {
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        Boolean code = categoryService.save(category);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改分类")
    @ApiImplicitParam(name = "category", value = "分类Model", required = true, paramType = "body", dataType = "Category")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Category category) {
        Boolean code = categoryService.updateById(category);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改分类")
    @ApiImplicitParam(name = "categoryList", value = "分类列表", required = true, paramType = "body", dataType = "List<Category>")
    @PutMapping("/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Category> categoryList) {
        Boolean code = categoryService.updateBatchById(categoryList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除分类")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除分类")
    @ApiImplicitParam(name = "ids", value = "分类id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping("/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
