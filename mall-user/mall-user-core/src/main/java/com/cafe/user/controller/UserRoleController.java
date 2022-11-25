package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.user.model.UserRole;
import com.cafe.user.service.UserRoleService;
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
 * @Description: 用户-角色关联关系接口
 */
@Api(value = "用户-角色关联关系接口")
@RestController
@RequestMapping(value = "/user-role")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @LogPrint(value = "查询用户-角色关联关系列表")
    @ApiOperation(value = "查询用户-角色关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserRole>> list() {
        List<UserRole> userRoleList = userRoleService.list();
        return ResponseEntity.ok(userRoleList);
    }

    @LogPrint(value = "根据条件查询用户-角色关联关系列表")
    @ApiOperation(value = "根据条件查询用户-角色关联关系列表")
    @ApiImplicitParam(value = "用户-角色关联关系Model", name = "userRole", dataType = "UserRole", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<UserRole>> list(@RequestBody UserRole userRole) {
        Wrapper<UserRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(userRole);
        List<UserRole> userRoleList = userRoleService.list(wrapper);
        return ResponseEntity.ok(userRoleList);
    }

    @LogPrint(value = "分页查询用户-角色关联关系列表")
    @ApiOperation(value = "分页查询用户-角色关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<UserRole>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<UserRole> page = new Page<UserRole>().setCurrent(current).setSize(size);
        Page<UserRole> userRolePage = userRoleService.page(page);
        return ResponseEntity.ok(userRolePage);
    }

    @LogPrint(value = "根据条件分页查询用户-角色关联关系")
    @ApiOperation(value = "根据条件分页查询用户-角色关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "用户-角色关联关系Model", name = "userRole", dataType = "UserRole", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<UserRole>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody UserRole userRole
    ) {
        Page<UserRole> page = new Page<UserRole>().setCurrent(current).setSize(size);
        Wrapper<UserRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(userRole);
        Page<UserRole> userRolePage = userRoleService.page(page, wrapper);
        return ResponseEntity.ok(userRolePage);
    }

    @LogPrint(value = "根据id查询单个用户-角色关联关系")
    @ApiOperation(value = "根据id查询单个用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<UserRole> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<UserRole>().eq(UserRole::getId, id);
        UserRole userRole = userRoleService.getOne(wrapper);
        return ResponseEntity.ok(userRole);
    }

    @LogPrint(value = "新增用户-角色关联关系")
    @ApiOperation(value = "新增用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系Model", name = "userRole", dataType = "UserRole", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody UserRole userRole) {
        LocalDateTime now = LocalDateTime.now();
        userRole.setCreateTime(now).setUpdateTime(now);
        Boolean code = userRoleService.save(userRole);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增用户-角色关联关系")
    @ApiOperation(value = "批量新增用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系列表", name = "userRoleList", dataType = "List<UserRole>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<UserRole> userRoleList) {
        LocalDateTime now = LocalDateTime.now();
        userRoleList = userRoleList.stream()
            .map(userRole -> userRole.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = userRoleService.saveBatch(userRoleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改用户-角色关联关系")
    @ApiOperation(value = "根据id修改用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系Model", name = "userRole", dataType = "UserRole", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody UserRole userRole) {
        Boolean code = userRoleService.updateById(userRole);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改用户-角色关联关系")
    @ApiOperation(value = "根据ids批量修改用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系列表", name = "userRoleList", dataType = "List<UserRole>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<UserRole> userRoleList) {
        Boolean code = userRoleService.updateBatchById(userRoleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除用户-角色关联关系")
    @ApiOperation(value = "根据id删除用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = userRoleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除用户-角色关联关系")
    @ApiOperation(value = "根据ids批量删除用户-角色关联关系")
    @ApiImplicitParam(value = "用户-角色关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = userRoleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
