package com.cafe.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.manager.model.entity.Theme;
import com.cafe.manager.service.ThemeService;
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
 * @Package: com.cafe.manager.controller
 * @Author: zhouboyi
 * @Date: 2023-05-16
 * @Description: 主题接口
 */
@Api(value = "主题接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/theme")
public class ThemeController {

    private final ThemeService themeService;

    @ApiLogPrint(value = "查询主题数量")
    @ApiOperation(value = "查询主题数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = themeService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询主题数量")
    @ApiOperation(value = "根据条件查询主题数量")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Theme theme) {
        QueryWrapper<Theme> wrapper = WrapperUtil.createQueryWrapper(theme);
        Integer count = themeService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询主题列表")
    @ApiOperation(value = "查询主题列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Theme>> list() {
        List<Theme> themeList = themeService.list();
        return ResponseEntity.ok(themeList);
    }

    @ApiLogPrint(value = "根据条件查询主题列表")
    @ApiOperation(value = "根据条件查询主题列表")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Theme>> list(@RequestBody Theme theme) {
        QueryWrapper<Theme> wrapper = WrapperUtil.createQueryWrapper(theme);
        List<Theme> themeList = themeService.list(wrapper);
        return ResponseEntity.ok(themeList);
    }

    @ApiLogPrint(value = "分页查询主题列表")
    @ApiOperation(value = "分页查询主题列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Theme>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Theme> page = new Page<>(current, size);
        Page<Theme> themePage = themeService.page(page);
        return ResponseEntity.ok(themePage);
    }

    @ApiLogPrint(value = "根据条件分页查询主题")
    @ApiOperation(value = "根据条件分页查询主题")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Theme>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Theme theme
    ) {
        Page<Theme> page = new Page<>(current, size);
        QueryWrapper<Theme> wrapper = WrapperUtil.createQueryWrapper(theme);
        Page<Theme> themePage = themeService.page(page, wrapper);
        return ResponseEntity.ok(themePage);
    }

    @ApiLogPrint(value = "根据id查询单个主题")
    @ApiOperation(value = "根据id查询单个主题")
    @ApiImplicitParam(value = "主题id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Theme> one(@PathVariable(value = "id") Long id) {
        Theme theme = themeService.getById(id);
        return ResponseEntity.ok(theme);
    }

    @ApiLogPrint(value = "根据条件查询单个主题")
    @ApiOperation(value = "根据条件查询单个主题")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Theme> one(@RequestBody Theme theme) {
        QueryWrapper<Theme> wrapper = WrapperUtil.createQueryWrapper(theme);
        Theme one = themeService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增主题")
    @ApiOperation(value = "新增主题")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Theme theme) {
        Boolean code = themeService.save(theme);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增主题")
    @ApiOperation(value = "批量新增主题")
    @ApiImplicitParam(value = "主题列表", name = "themeList", dataType = "List<Theme>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Theme> themeList) {
        Boolean code = themeService.saveBatch(themeList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改主题")
    @ApiOperation(value = "根据id修改主题")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Theme theme) {
        Boolean code = themeService.updateById(theme);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改主题")
    @ApiOperation(value = "根据ids批量修改主题")
    @ApiImplicitParam(value = "主题列表", name = "themeList", dataType = "List<Theme>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Theme> themeList) {
        Boolean code = themeService.updateBatchById(themeList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除主题")
    @ApiOperation(value = "根据id删除主题")
    @ApiImplicitParam(value = "主题id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = themeService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除主题")
    @ApiOperation(value = "根据ids批量删除主题")
    @ApiImplicitParam(value = "主题id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = themeService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除主题")
    @ApiOperation(value = "根据条件批量删除主题")
    @ApiImplicitParam(value = "主题Model", name = "theme", dataType = "Theme", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Theme theme) {
        QueryWrapper<Theme> wrapper = WrapperUtil.createQueryWrapper(theme);
        Boolean code = themeService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
