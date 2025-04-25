package com.cafe.review.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.review.model.entity.Tag;
import com.cafe.review.service.TagService;
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
 * @Package: com.cafe.review.controller
 * @Author: zhouboyi
 * @Date: 2025-04-29
 * @Description: 标签接口
 */
@Api(value = "标签接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tag")
public class TagController {

    private final TagService tagService;

    @ApiLogPrint(value = "查询标签数量")
    @ApiOperation(value = "查询标签数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = tagService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询标签数量")
    @ApiOperation(value = "根据条件查询标签数量")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Tag tag) {
        QueryWrapper<Tag> wrapper = WrapperUtil.createQueryWrapper(tag);
        Integer count = tagService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询标签列表")
    @ApiOperation(value = "查询标签列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Tag>> list() {
        List<Tag> tagList = tagService.list();
        return ResponseEntity.ok(tagList);
    }

    @ApiLogPrint(value = "根据条件查询标签列表")
    @ApiOperation(value = "根据条件查询标签列表")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Tag>> list(@RequestBody Tag tag) {
        QueryWrapper<Tag> wrapper = WrapperUtil.createQueryWrapper(tag);
        List<Tag> tagList = tagService.list(wrapper);
        return ResponseEntity.ok(tagList);
    }

    @ApiLogPrint(value = "分页查询标签列表")
    @ApiOperation(value = "分页查询标签列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Tag>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Tag> page = new Page<>(current, size);
        Page<Tag> tagPage = tagService.page(page);
        return ResponseEntity.ok(tagPage);
    }

    @ApiLogPrint(value = "根据条件分页查询标签")
    @ApiOperation(value = "根据条件分页查询标签")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Tag>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Tag tag
    ) {
        Page<Tag> page = new Page<>(current, size);
        QueryWrapper<Tag> wrapper = WrapperUtil.createQueryWrapper(tag);
        Page<Tag> tagPage = tagService.page(page, wrapper);
        return ResponseEntity.ok(tagPage);
    }

    @ApiLogPrint(value = "根据id查询单个标签")
    @ApiOperation(value = "根据id查询单个标签")
    @ApiImplicitParam(value = "标签id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Tag> one(@PathVariable(value = "id") Long id) {
        Tag tag = tagService.getById(id);
        return ResponseEntity.ok(tag);
    }

    @ApiLogPrint(value = "根据条件查询单个标签")
    @ApiOperation(value = "根据条件查询单个标签")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Tag> one(@RequestBody Tag tag) {
        QueryWrapper<Tag> wrapper = WrapperUtil.createQueryWrapper(tag);
        Tag one = tagService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增标签")
    @ApiOperation(value = "新增标签")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Tag tag) {
        Boolean code = tagService.save(tag);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增标签")
    @ApiOperation(value = "批量新增标签")
    @ApiImplicitParam(value = "标签列表", name = "tagList", dataType = "List<Tag>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Tag> tagList) {
        Boolean code = tagService.saveBatch(tagList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改标签")
    @ApiOperation(value = "根据id修改标签")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Tag tag) {
        Boolean code = tagService.updateById(tag);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改标签")
    @ApiOperation(value = "根据ids批量修改标签")
    @ApiImplicitParam(value = "标签列表", name = "tagList", dataType = "List<Tag>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Tag> tagList) {
        Boolean code = tagService.updateBatchById(tagList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除标签")
    @ApiOperation(value = "根据id删除标签")
    @ApiImplicitParam(value = "标签id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = tagService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除标签")
    @ApiOperation(value = "根据ids批量删除标签")
    @ApiImplicitParam(value = "标签id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = tagService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除标签")
    @ApiOperation(value = "根据条件批量删除标签")
    @ApiImplicitParam(value = "标签Model", name = "tag", dataType = "Tag", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Tag tag) {
        QueryWrapper<Tag> wrapper = WrapperUtil.createQueryWrapper(tag);
        Boolean code = tagService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
