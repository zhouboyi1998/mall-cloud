package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.user.model.UserPlatform;
import com.cafe.user.service.UserPlatformService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.controller
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 用户-平台关联关系接口
 */
@Api(value = "用户-平台关联关系接口")
@RestController
@RequestMapping(value = "/user-platform")
public class UserPlatformController {

    private final UserPlatformService userPlatformService;

    @Autowired
    public UserPlatformController(UserPlatformService userPlatformService) {
        this.userPlatformService = userPlatformService;
    }

    @LogPrint(value = "查询用户-平台关联关系数量")
    @ApiOperation(value = "查询用户-平台关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = userPlatformService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询用户-平台关联关系数量")
    @ApiOperation(value = "根据条件查询用户-平台关联关系数量")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody UserPlatform userPlatform) {
        QueryWrapper<UserPlatform> wrapper = WrapperUtil.createQueryWrapper(userPlatform);
        Integer count = userPlatformService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询用户-平台关联关系列表")
    @ApiOperation(value = "查询用户-平台关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserPlatform>> list() {
        List<UserPlatform> userPlatformList = userPlatformService.list();
        return ResponseEntity.ok(userPlatformList);
    }

    @LogPrint(value = "根据条件查询用户-平台关联关系列表")
    @ApiOperation(value = "根据条件查询用户-平台关联关系列表")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<UserPlatform>> list(@RequestBody UserPlatform userPlatform) {
        QueryWrapper<UserPlatform> wrapper = WrapperUtil.createQueryWrapper(userPlatform);
        List<UserPlatform> userPlatformList = userPlatformService.list(wrapper);
        return ResponseEntity.ok(userPlatformList);
    }

    @LogPrint(value = "分页查询用户-平台关联关系列表")
    @ApiOperation(value = "分页查询用户-平台关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<UserPlatform>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<UserPlatform> page = new Page<UserPlatform>().setCurrent(current).setSize(size);
        Page<UserPlatform> userPlatformPage = userPlatformService.page(page);
        return ResponseEntity.ok(userPlatformPage);
    }

    @LogPrint(value = "根据条件分页查询用户-平台关联关系")
    @ApiOperation(value = "根据条件分页查询用户-平台关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<UserPlatform>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody UserPlatform userPlatform
    ) {
        Page<UserPlatform> page = new Page<UserPlatform>().setCurrent(current).setSize(size);
        QueryWrapper<UserPlatform> wrapper = WrapperUtil.createQueryWrapper(userPlatform);
        Page<UserPlatform> userPlatformPage = userPlatformService.page(page, wrapper);
        return ResponseEntity.ok(userPlatformPage);
    }

    @LogPrint(value = "根据id查询单个用户-平台关联关系")
    @ApiOperation(value = "根据id查询单个用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<UserPlatform> one(@PathVariable(value = "id") Long id) {
        UserPlatform userPlatform = userPlatformService.getById(id);
        return ResponseEntity.ok(userPlatform);
    }

    @LogPrint(value = "根据条件查询单个用户-平台关联关系")
    @ApiOperation(value = "根据条件查询单个用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<UserPlatform> one(@RequestBody UserPlatform userPlatform) {
        QueryWrapper<UserPlatform> wrapper = WrapperUtil.createQueryWrapper(userPlatform);
        UserPlatform one = userPlatformService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增用户-平台关联关系")
    @ApiOperation(value = "新增用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody UserPlatform userPlatform) {
        LocalDateTime now = LocalDateTime.now();
        userPlatform.setCreateTime(now).setUpdateTime(now);
        Boolean code = userPlatformService.save(userPlatform);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增用户-平台关联关系")
    @ApiOperation(value = "批量新增用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系列表", name = "userPlatformList", dataType = "List<UserPlatform>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<UserPlatform> userPlatformList) {
        LocalDateTime now = LocalDateTime.now();
        userPlatformList = userPlatformList.stream()
            .map(userPlatform -> userPlatform.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = userPlatformService.saveBatch(userPlatformList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改用户-平台关联关系")
    @ApiOperation(value = "根据id修改用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody UserPlatform userPlatform) {
        Boolean code = userPlatformService.updateById(userPlatform);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改用户-平台关联关系")
    @ApiOperation(value = "根据ids批量修改用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系列表", name = "userPlatformList", dataType = "List<UserPlatform>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<UserPlatform> userPlatformList) {
        Boolean code = userPlatformService.updateBatchById(userPlatformList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除用户-平台关联关系")
    @ApiOperation(value = "根据id删除用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = userPlatformService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除用户-平台关联关系")
    @ApiOperation(value = "根据ids批量删除用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = userPlatformService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除用户-平台关联关系")
    @ApiOperation(value = "根据条件批量删除用户-平台关联关系")
    @ApiImplicitParam(value = "用户-平台关联关系Model", name = "userPlatform", dataType = "UserPlatform", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UserPlatform userPlatform) {
        QueryWrapper<UserPlatform> wrapper = WrapperUtil.createQueryWrapper(userPlatform);
        Boolean code = userPlatformService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
