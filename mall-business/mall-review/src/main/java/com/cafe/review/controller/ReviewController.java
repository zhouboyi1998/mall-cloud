package com.cafe.review.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.review.model.entity.Review;
import com.cafe.review.service.ReviewService;
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
 * @Description: 评论接口
 */
@Api(value = "评论接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiLogPrint(value = "查询评论数量")
    @ApiOperation(value = "查询评论数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = reviewService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询评论数量")
    @ApiOperation(value = "根据条件查询评论数量")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Review review) {
        QueryWrapper<Review> wrapper = WrapperUtil.createQueryWrapper(review);
        Integer count = reviewService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询评论列表")
    @ApiOperation(value = "查询评论列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Review>> list() {
        List<Review> reviewList = reviewService.list();
        return ResponseEntity.ok(reviewList);
    }

    @ApiLogPrint(value = "根据条件查询评论列表")
    @ApiOperation(value = "根据条件查询评论列表")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Review>> list(@RequestBody Review review) {
        QueryWrapper<Review> wrapper = WrapperUtil.createQueryWrapper(review);
        List<Review> reviewList = reviewService.list(wrapper);
        return ResponseEntity.ok(reviewList);
    }

    @ApiLogPrint(value = "分页查询评论列表")
    @ApiOperation(value = "分页查询评论列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Review>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Review> page = new Page<>(current, size);
        Page<Review> reviewPage = reviewService.page(page);
        return ResponseEntity.ok(reviewPage);
    }

    @ApiLogPrint(value = "根据条件分页查询评论")
    @ApiOperation(value = "根据条件分页查询评论")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Review>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Review review
    ) {
        Page<Review> page = new Page<>(current, size);
        QueryWrapper<Review> wrapper = WrapperUtil.createQueryWrapper(review);
        Page<Review> reviewPage = reviewService.page(page, wrapper);
        return ResponseEntity.ok(reviewPage);
    }

    @ApiLogPrint(value = "根据id查询单个评论")
    @ApiOperation(value = "根据id查询单个评论")
    @ApiImplicitParam(value = "评论id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Review> one(@PathVariable(value = "id") Long id) {
        Review review = reviewService.getById(id);
        return ResponseEntity.ok(review);
    }

    @ApiLogPrint(value = "根据条件查询单个评论")
    @ApiOperation(value = "根据条件查询单个评论")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Review> one(@RequestBody Review review) {
        QueryWrapper<Review> wrapper = WrapperUtil.createQueryWrapper(review);
        Review one = reviewService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增评论")
    @ApiOperation(value = "新增评论")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Review review) {
        Boolean code = reviewService.save(review);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增评论")
    @ApiOperation(value = "批量新增评论")
    @ApiImplicitParam(value = "评论列表", name = "reviewList", dataType = "List<Review>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Review> reviewList) {
        Boolean code = reviewService.saveBatch(reviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改评论")
    @ApiOperation(value = "根据id修改评论")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Review review) {
        Boolean code = reviewService.updateById(review);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改评论")
    @ApiOperation(value = "根据ids批量修改评论")
    @ApiImplicitParam(value = "评论列表", name = "reviewList", dataType = "List<Review>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Review> reviewList) {
        Boolean code = reviewService.updateBatchById(reviewList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除评论")
    @ApiOperation(value = "根据id删除评论")
    @ApiImplicitParam(value = "评论id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = reviewService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除评论")
    @ApiOperation(value = "根据ids批量删除评论")
    @ApiImplicitParam(value = "评论id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = reviewService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除评论")
    @ApiOperation(value = "根据条件批量删除评论")
    @ApiImplicitParam(value = "评论Model", name = "review", dataType = "Review", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Review review) {
        QueryWrapper<Review> wrapper = WrapperUtil.createQueryWrapper(review);
        Boolean code = reviewService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
