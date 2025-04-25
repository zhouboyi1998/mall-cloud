package com.cafe.review.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.review.model.entity.ReviewTag;
import com.cafe.review.service.ReviewTagService;
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
 * @Description: 评论-标签关联关系接口
 */
@Api(value = "评论-标签关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/review-tag")
public class ReviewTagController {

    private final ReviewTagService reviewTagService;

    @ApiLogPrint(value = "查询评论-标签关联关系数量")
    @ApiOperation(value = "查询评论-标签关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = reviewTagService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询评论-标签关联关系数量")
    @ApiOperation(value = "根据条件查询评论-标签关联关系数量")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody ReviewTag reviewTag) {
        QueryWrapper<ReviewTag> wrapper = WrapperUtil.createQueryWrapper(reviewTag);
        Integer count = reviewTagService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询评论-标签关联关系列表")
    @ApiOperation(value = "查询评论-标签关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<ReviewTag>> list() {
        List<ReviewTag> reviewTagList = reviewTagService.list();
        return ResponseEntity.ok(reviewTagList);
    }

    @ApiLogPrint(value = "根据条件查询评论-标签关联关系列表")
    @ApiOperation(value = "根据条件查询评论-标签关联关系列表")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<ReviewTag>> list(@RequestBody ReviewTag reviewTag) {
        QueryWrapper<ReviewTag> wrapper = WrapperUtil.createQueryWrapper(reviewTag);
        List<ReviewTag> reviewTagList = reviewTagService.list(wrapper);
        return ResponseEntity.ok(reviewTagList);
    }

    @ApiLogPrint(value = "分页查询评论-标签关联关系列表")
    @ApiOperation(value = "分页查询评论-标签关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<ReviewTag>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<ReviewTag> page = new Page<>(current, size);
        Page<ReviewTag> reviewTagPage = reviewTagService.page(page);
        return ResponseEntity.ok(reviewTagPage);
    }

    @ApiLogPrint(value = "根据条件分页查询评论-标签关联关系")
    @ApiOperation(value = "根据条件分页查询评论-标签关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<ReviewTag>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody ReviewTag reviewTag
    ) {
        Page<ReviewTag> page = new Page<>(current, size);
        QueryWrapper<ReviewTag> wrapper = WrapperUtil.createQueryWrapper(reviewTag);
        Page<ReviewTag> reviewTagPage = reviewTagService.page(page, wrapper);
        return ResponseEntity.ok(reviewTagPage);
    }

    @ApiLogPrint(value = "根据id查询单个评论-标签关联关系")
    @ApiOperation(value = "根据id查询单个评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<ReviewTag> one(@PathVariable(value = "id") Long id) {
        ReviewTag reviewTag = reviewTagService.getById(id);
        return ResponseEntity.ok(reviewTag);
    }

    @ApiLogPrint(value = "根据条件查询单个评论-标签关联关系")
    @ApiOperation(value = "根据条件查询单个评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<ReviewTag> one(@RequestBody ReviewTag reviewTag) {
        QueryWrapper<ReviewTag> wrapper = WrapperUtil.createQueryWrapper(reviewTag);
        ReviewTag one = reviewTagService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增评论-标签关联关系")
    @ApiOperation(value = "新增评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody ReviewTag reviewTag) {
        Boolean code = reviewTagService.save(reviewTag);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增评论-标签关联关系")
    @ApiOperation(value = "批量新增评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系列表", name = "reviewTagList", dataType = "List<ReviewTag>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<ReviewTag> reviewTagList) {
        Boolean code = reviewTagService.saveBatch(reviewTagList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改评论-标签关联关系")
    @ApiOperation(value = "根据id修改评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody ReviewTag reviewTag) {
        Boolean code = reviewTagService.updateById(reviewTag);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改评论-标签关联关系")
    @ApiOperation(value = "根据ids批量修改评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系列表", name = "reviewTagList", dataType = "List<ReviewTag>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ReviewTag> reviewTagList) {
        Boolean code = reviewTagService.updateBatchById(reviewTagList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除评论-标签关联关系")
    @ApiOperation(value = "根据id删除评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = reviewTagService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除评论-标签关联关系")
    @ApiOperation(value = "根据ids批量删除评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = reviewTagService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除评论-标签关联关系")
    @ApiOperation(value = "根据条件批量删除评论-标签关联关系")
    @ApiImplicitParam(value = "评论-标签关联关系Model", name = "reviewTag", dataType = "ReviewTag", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody ReviewTag reviewTag) {
        QueryWrapper<ReviewTag> wrapper = WrapperUtil.createQueryWrapper(reviewTag);
        Boolean code = reviewTagService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
