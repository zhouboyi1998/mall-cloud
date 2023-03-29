package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.user.model.Platform;
import com.cafe.user.service.PlatformService;
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

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.controller
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 平台接口
 */
@Api(value = "平台接口")
@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    private final PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @LogPrint(value = "查询平台数量")
    @ApiOperation(value = "查询平台数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = platformService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询平台数量")
    @ApiOperation(value = "根据条件查询平台数量")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Platform platform) {
        QueryWrapper<Platform> wrapper = WrapperUtil.createQueryWrapper(platform);
        Integer count = platformService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询平台列表")
    @ApiOperation(value = "查询平台列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Platform>> list() {
        List<Platform> platformList = platformService.list();
        return ResponseEntity.ok(platformList);
    }

    @LogPrint(value = "根据条件查询平台列表")
    @ApiOperation(value = "根据条件查询平台列表")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Platform>> list(@RequestBody Platform platform) {
        QueryWrapper<Platform> wrapper = WrapperUtil.createQueryWrapper(platform);
        List<Platform> platformList = platformService.list(wrapper);
        return ResponseEntity.ok(platformList);
    }

    @LogPrint(value = "分页查询平台列表")
    @ApiOperation(value = "分页查询平台列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Platform>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Platform> page = new Page<Platform>().setCurrent(current).setSize(size);
        Page<Platform> platformPage = platformService.page(page);
        return ResponseEntity.ok(platformPage);
    }

    @LogPrint(value = "根据条件分页查询平台")
    @ApiOperation(value = "根据条件分页查询平台")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Platform>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Platform platform
    ) {
        Page<Platform> page = new Page<Platform>().setCurrent(current).setSize(size);
        QueryWrapper<Platform> wrapper = WrapperUtil.createQueryWrapper(platform);
        Page<Platform> platformPage = platformService.page(page, wrapper);
        return ResponseEntity.ok(platformPage);
    }

    @LogPrint(value = "根据id查询单个平台")
    @ApiOperation(value = "根据id查询单个平台")
    @ApiImplicitParam(value = "平台id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Platform> one(@PathVariable(value = "id") Long id) {
        Platform platform = platformService.getById(id);
        return ResponseEntity.ok(platform);
    }

    @LogPrint(value = "根据条件查询单个平台")
    @ApiOperation(value = "根据条件查询单个平台")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Platform> one(@RequestBody Platform platform) {
        QueryWrapper<Platform> wrapper = WrapperUtil.createQueryWrapper(platform);
        Platform one = platformService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增平台")
    @ApiOperation(value = "新增平台")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Platform platform) {
        Boolean code = platformService.save(platform);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增平台")
    @ApiOperation(value = "批量新增平台")
    @ApiImplicitParam(value = "平台列表", name = "platformList", dataType = "List<Platform>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Platform> platformList) {
        Boolean code = platformService.saveBatch(platformList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改平台")
    @ApiOperation(value = "根据id修改平台")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Platform platform) {
        Boolean code = platformService.updateById(platform);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改平台")
    @ApiOperation(value = "根据ids批量修改平台")
    @ApiImplicitParam(value = "平台列表", name = "platformList", dataType = "List<Platform>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Platform> platformList) {
        Boolean code = platformService.updateBatchById(platformList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除平台")
    @ApiOperation(value = "根据id删除平台")
    @ApiImplicitParam(value = "平台id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = platformService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除平台")
    @ApiOperation(value = "根据ids批量删除平台")
    @ApiImplicitParam(value = "平台id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = platformService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除平台")
    @ApiOperation(value = "根据条件批量删除平台")
    @ApiImplicitParam(value = "平台Model", name = "platform", dataType = "Platform", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Platform platform) {
        QueryWrapper<Platform> wrapper = WrapperUtil.createQueryWrapper(platform);
        Boolean code = platformService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
