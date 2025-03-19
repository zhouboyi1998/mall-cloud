package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goods.model.entity.CategoryBrand;
import com.cafe.goods.service.CategoryBrandService;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * @Description: 分类-品牌关联关系接口
 */
@Api(value = "分类-品牌关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/category-brand")
public class CategoryBrandController {

    private final CategoryBrandService categoryBrandService;

    @ApiLogPrint(value = "查询分类-品牌关联关系数量")
    @ApiOperation(value = "查询分类-品牌关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = categoryBrandService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询分类-品牌关联关系数量")
    @ApiOperation(value = "根据条件查询分类-品牌关联关系数量")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody CategoryBrand categoryBrand) {
        QueryWrapper<CategoryBrand> wrapper = WrapperUtil.createQueryWrapper(categoryBrand);
        Integer count = categoryBrandService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询分类-品牌关联关系列表")
    @ApiOperation(value = "查询分类-品牌关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<CategoryBrand>> list() {
        List<CategoryBrand> categoryBrandList = categoryBrandService.list();
        return ResponseEntity.ok(categoryBrandList);
    }

    @ApiLogPrint(value = "根据条件查询分类-品牌关联关系列表")
    @ApiOperation(value = "根据条件查询分类-品牌关联关系列表")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<CategoryBrand>> list(@RequestBody CategoryBrand categoryBrand) {
        QueryWrapper<CategoryBrand> wrapper = WrapperUtil.createQueryWrapper(categoryBrand);
        List<CategoryBrand> categoryBrandList = categoryBrandService.list(wrapper);
        return ResponseEntity.ok(categoryBrandList);
    }

    @ApiLogPrint(value = "分页查询分类-品牌关联关系列表")
    @ApiOperation(value = "分页查询分类-品牌关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<CategoryBrand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<CategoryBrand> page = new Page<>(current, size);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @ApiLogPrint(value = "根据条件分页查询分类-品牌关联关系")
    @ApiOperation(value = "根据条件分页查询分类-品牌关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<CategoryBrand>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody CategoryBrand categoryBrand
    ) {
        Page<CategoryBrand> page = new Page<>(current, size);
        QueryWrapper<CategoryBrand> wrapper = WrapperUtil.createQueryWrapper(categoryBrand);
        Page<CategoryBrand> categoryBrandPage = categoryBrandService.page(page, wrapper);
        return ResponseEntity.ok(categoryBrandPage);
    }

    @ApiLogPrint(value = "根据id查询单个分类-品牌关联关系")
    @ApiOperation(value = "根据id查询单个分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<CategoryBrand> one(@PathVariable(value = "id") Long id) {
        CategoryBrand categoryBrand = categoryBrandService.getById(id);
        return ResponseEntity.ok(categoryBrand);
    }

    @ApiLogPrint(value = "根据条件查询单个分类-品牌关联关系")
    @ApiOperation(value = "根据条件查询单个分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<CategoryBrand> one(@RequestBody CategoryBrand categoryBrand) {
        QueryWrapper<CategoryBrand> wrapper = WrapperUtil.createQueryWrapper(categoryBrand);
        CategoryBrand one = categoryBrandService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增分类-品牌关联关系")
    @ApiOperation(value = "新增分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody CategoryBrand categoryBrand) {
        Boolean code = categoryBrandService.save(categoryBrand);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增分类-品牌关联关系")
    @ApiOperation(value = "批量新增分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系列表", name = "categoryBrandList", dataType = "List<CategoryBrand>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<CategoryBrand> categoryBrandList) {
        Boolean code = categoryBrandService.saveBatch(categoryBrandList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改分类-品牌关联关系")
    @ApiOperation(value = "根据id修改分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody CategoryBrand categoryBrand) {
        Boolean code = categoryBrandService.updateById(categoryBrand);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改分类-品牌关联关系")
    @ApiOperation(value = "根据ids批量修改分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系列表", name = "categoryBrandList", dataType = "List<CategoryBrand>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<CategoryBrand> categoryBrandList) {
        Boolean code = categoryBrandService.updateBatchById(categoryBrandList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除分类-品牌关联关系")
    @ApiOperation(value = "根据id删除分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = categoryBrandService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除分类-品牌关联关系")
    @ApiOperation(value = "根据ids批量删除分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = categoryBrandService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除分类-品牌关联关系")
    @ApiOperation(value = "根据条件批量删除分类-品牌关联关系")
    @ApiImplicitParam(value = "分类-品牌关联关系Model", name = "categoryBrand", dataType = "CategoryBrand", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody CategoryBrand categoryBrand) {
        QueryWrapper<CategoryBrand> wrapper = WrapperUtil.createQueryWrapper(categoryBrand);
        Boolean code = categoryBrandService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
