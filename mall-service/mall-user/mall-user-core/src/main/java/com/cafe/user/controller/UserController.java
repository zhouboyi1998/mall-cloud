package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.user.model.User;
import com.cafe.user.service.UserService;
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
 * @Date: 2022-05-09
 * @Description: 用户接口
 */
@Api(value = "用户接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @LogPrint(value = "查询用户数量")
    @ApiOperation(value = "查询用户数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = userService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询用户数量")
    @ApiOperation(value = "根据条件查询用户数量")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody User user) {
        QueryWrapper<User> wrapper = WrapperUtil.createQueryWrapper(user);
        Integer count = userService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询用户列表")
    @ApiOperation(value = "查询用户列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> list() {
        List<User> userList = userService.list();
        return ResponseEntity.ok(userList);
    }

    @LogPrint(value = "根据条件查询用户列表")
    @ApiOperation(value = "根据条件查询用户列表")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<User>> list(@RequestBody User user) {
        QueryWrapper<User> wrapper = WrapperUtil.createQueryWrapper(user);
        List<User> userList = userService.list(wrapper);
        return ResponseEntity.ok(userList);
    }

    @LogPrint(value = "分页查询用户列表")
    @ApiOperation(value = "分页查询用户列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<User>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<User> page = new Page<User>().setCurrent(current).setSize(size);
        Page<User> userPage = userService.page(page);
        return ResponseEntity.ok(userPage);
    }

    @LogPrint(value = "根据条件分页查询用户")
    @ApiOperation(value = "根据条件分页查询用户")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<User>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody User user
    ) {
        Page<User> page = new Page<User>().setCurrent(current).setSize(size);
        QueryWrapper<User> wrapper = WrapperUtil.createQueryWrapper(user);
        Page<User> userPage = userService.page(page, wrapper);
        return ResponseEntity.ok(userPage);
    }

    @LogPrint(value = "根据id查询单个用户")
    @ApiOperation(value = "根据id查询单个用户")
    @ApiImplicitParam(value = "用户id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<User> one(@PathVariable(value = "id") Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @LogPrint(value = "根据条件查询单个用户")
    @ApiOperation(value = "根据条件查询单个用户")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<User> one(@RequestBody User user) {
        QueryWrapper<User> wrapper = WrapperUtil.createQueryWrapper(user);
        User one = userService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增用户")
    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now).setUpdateTime(now);
        Boolean code = userService.save(user);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增用户")
    @ApiOperation(value = "批量新增用户")
    @ApiImplicitParam(value = "用户列表", name = "userList", dataType = "List<User>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<User> userList) {
        LocalDateTime now = LocalDateTime.now();
        userList = userList.stream()
            .map(user -> user.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = userService.saveBatch(userList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改用户")
    @ApiOperation(value = "根据id修改用户")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody User user) {
        Boolean code = userService.updateById(user);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改用户")
    @ApiOperation(value = "根据ids批量修改用户")
    @ApiImplicitParam(value = "用户列表", name = "userList", dataType = "List<User>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<User> userList) {
        Boolean code = userService.updateBatchById(userList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除用户")
    @ApiOperation(value = "根据id删除用户")
    @ApiImplicitParam(value = "用户id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = userService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除用户")
    @ApiOperation(value = "根据ids批量删除用户")
    @ApiImplicitParam(value = "用户id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = userService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除用户")
    @ApiOperation(value = "根据条件批量删除用户")
    @ApiImplicitParam(value = "用户Model", name = "user", dataType = "User", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody User user) {
        QueryWrapper<User> wrapper = WrapperUtil.createQueryWrapper(user);
        Boolean code = userService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据用户名和客户端id查询用户详情")
    @ApiOperation(value = "根据用户名和客户端id查询用户详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "用户名", name = "username", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "客户端id", name = "clientId", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/detail/username/{username}/{clientId}")
    public ResponseEntity<User> detailByUsername(
        @PathVariable(value = "username") String username,
        @PathVariable(value = "clientId") String clientId
    ) {
        User user = userService.detailByUsername(username, clientId);
        return ResponseEntity.ok(user);
    }

    @LogPrint(value = "根据手机号和客户端id查询用户详情")
    @ApiOperation(value = "根据手机号和客户端id查询用户详情")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "手机号", name = "mobile", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "客户端id", name = "clientId", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/detail/mobile/{mobile}/{clientId}")
    public ResponseEntity<User> detailByMobile(
        @PathVariable(value = "mobile") String mobile,
        @PathVariable(value = "clientId") String clientId
    ) {
        User user = userService.detailByMobile(mobile, clientId);
        return ResponseEntity.ok(user);
    }
}
