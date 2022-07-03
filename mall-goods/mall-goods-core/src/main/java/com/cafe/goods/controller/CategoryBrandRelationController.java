package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import com.cafe.goods.model.Brand;
import com.cafe.goods.model.CategoryBrandRelation;
import com.cafe.goods.service.CategoryBrandRelationService;
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
 * @Description: 分类-品牌关联 (控制器)
 */
@Api(value = "分类-品牌关联接口")
@RestController
@RequestMapping(value = "/categoryBrandRelation")
public class CategoryBrandRelationController {

    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    public CategoryBrandRelationController(CategoryBrandRelationService categoryBrandRelationService) {
        this.categoryBrandRelationService = categoryBrandRelationService;
    }

    @ApiOperation(value = "查询分类-品牌关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryBrandRelation>> list() {
        List<CategoryBrandRelation> categoryBrandRelationList = categoryBrandRelationService.list();
        return ResponseEntity.ok(categoryBrandRelationList);
    }

    @ApiOperation(value = "根据条件查询分类-品牌关联列表")
    @ApiImplicitParam(name = "categoryBrandRelation", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrandRelation")
    @PostMapping(value = "/list")
    public ResponseEntity<List<CategoryBrandRelation>> listByWrapper(@RequestBody CategoryBrandRelation categoryBrandRelation) {
        Wrapper<CategoryBrandRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(categoryBrandRelation);
        List<CategoryBrandRelation> categoryBrandRelationList = categoryBrandRelationService.list(wrapper);
        return ResponseEntity.ok(categoryBrandRelationList);
    }

    @ApiOperation(value = "分页查询分类-品牌关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<CategoryBrandRelation>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<CategoryBrandRelation> page = new Page<CategoryBrandRelation>().setCurrent(current).setSize(size);
        Page<CategoryBrandRelation> categoryBrandRelationPage = categoryBrandRelationService.page(page);
        return ResponseEntity.ok(categoryBrandRelationPage);
    }

    @ApiOperation(value = "根据条件分页查询分类-品牌关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "categoryBrandRelation", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrandRelation")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<CategoryBrandRelation>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody CategoryBrandRelation categoryBrandRelation
    ) {
        Page<CategoryBrandRelation> page = new Page<CategoryBrandRelation>().setCurrent(current).setSize(size);
        Wrapper<CategoryBrandRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(categoryBrandRelation);
        Page<CategoryBrandRelation> categoryBrandRelationPage = categoryBrandRelationService.page(page, wrapper);
        return ResponseEntity.ok(categoryBrandRelationPage);
    }

    @ApiOperation(value = "分页查询分类-品牌关联")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<CategoryBrandRelation>")
    @GetMapping(value = "/page")
    public ResponseEntity<IPage<CategoryBrandRelation>> page(@RequestBody Page<CategoryBrandRelation> page) {
        Page<CategoryBrandRelation> categoryBrandRelationPage = categoryBrandRelationService.page(page);
        return ResponseEntity.ok(categoryBrandRelationPage);
    }

    @ApiOperation(value = "根据条件分页查询分类-品牌关联")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<CategoryBrandRelation>")
    @PostMapping(value = "/page")
    public ResponseEntity<IPage<CategoryBrandRelation>> pageByWrapper(@RequestBody Page<CategoryBrandRelation> page) {
        CategoryBrandRelation brand = page.getRecords().get(0);
        Wrapper<CategoryBrandRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(brand);
        Page<CategoryBrandRelation> categoryBrandRelationPage = categoryBrandRelationService.page(page, wrapper);
        return ResponseEntity.ok(categoryBrandRelationPage);
    }

    @ApiOperation(value = "根据id查询单个分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<CategoryBrandRelation> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<CategoryBrandRelation> wrapper = new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getId, id);
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getOne(wrapper);
        return ResponseEntity.ok(categoryBrandRelation);
    }

    @ApiOperation(value = "根据id查询单个分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<CategoryBrandRelation> one2(@RequestParam Long id) {
        LambdaQueryWrapper<CategoryBrandRelation> wrapper = new LambdaQueryWrapper<CategoryBrandRelation>().eq(CategoryBrandRelation::getId, id);
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getOne(wrapper);
        return ResponseEntity.ok(categoryBrandRelation);
    }

    @ApiOperation(value = "新增分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrandRelation", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrandRelation")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody CategoryBrandRelation categoryBrandRelation) {
        categoryBrandRelation.setCreateTime(LocalDateTime.now());
        categoryBrandRelation.setUpdateTime(LocalDateTime.now());
        Boolean code = categoryBrandRelationService.save(categoryBrandRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrandRelation", value = "分类-品牌关联Model", required = true, paramType = "body", dataType = "CategoryBrandRelation")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody CategoryBrandRelation categoryBrandRelation) {
        Boolean code = categoryBrandRelationService.updateById(categoryBrandRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改分类-品牌关联")
    @ApiImplicitParam(name = "categoryBrandRelationList", value = "分类-品牌关联列表", required = true, paramType = "body", dataType = "List<CategoryBrandRelation>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<CategoryBrandRelation> categoryBrandRelationList) {
        Boolean code = categoryBrandRelationService.updateBatchById(categoryBrandRelationList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryBrandRelationService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除分类-品牌关联")
    @ApiImplicitParam(name = "id", value = "分类-品牌关联id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = categoryBrandRelationService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除分类-品牌关联")
    @ApiImplicitParam(name = "ids", value = "分类-品牌关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryBrandRelationService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
