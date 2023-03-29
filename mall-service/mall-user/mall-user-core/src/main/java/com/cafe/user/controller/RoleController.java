package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.user.model.Role;
import com.cafe.user.service.RoleService;
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
 * @Date: 2022-05-09
 * @Description: 角色接口
 */
@Api(value = "角色接口")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @LogPrint(value = "查询角色数量")
    @ApiOperation(value = "查询角色数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = roleService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询角色数量")
    @ApiOperation(value = "根据条件查询角色数量")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Role role) {
        QueryWrapper<Role> wrapper = WrapperUtil.createQueryWrapper(role);
        Integer count = roleService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询角色列表")
    @ApiOperation(value = "查询角色列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Role>> list() {
        List<Role> roleList = roleService.list();
        return ResponseEntity.ok(roleList);
    }

    @LogPrint(value = "根据条件查询角色列表")
    @ApiOperation(value = "根据条件查询角色列表")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Role>> list(@RequestBody Role role) {
        QueryWrapper<Role> wrapper = WrapperUtil.createQueryWrapper(role);
        List<Role> roleList = roleService.list(wrapper);
        return ResponseEntity.ok(roleList);
    }

    @LogPrint(value = "分页查询角色列表")
    @ApiOperation(value = "分页查询角色列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Role>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        Page<Role> rolePage = roleService.page(page);
        return ResponseEntity.ok(rolePage);
    }

    @LogPrint(value = "根据条件分页查询角色")
    @ApiOperation(value = "根据条件分页查询角色")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Role>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Role role
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        QueryWrapper<Role> wrapper = WrapperUtil.createQueryWrapper(role);
        Page<Role> rolePage = roleService.page(page, wrapper);
        return ResponseEntity.ok(rolePage);
    }

    @LogPrint(value = "根据id查询单个角色")
    @ApiOperation(value = "根据id查询单个角色")
    @ApiImplicitParam(value = "角色id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Role> one(@PathVariable(value = "id") Long id) {
        Role role = roleService.getById(id);
        return ResponseEntity.ok(role);
    }

    @LogPrint(value = "根据条件查询单个角色")
    @ApiOperation(value = "根据条件查询单个角色")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Role> one(@RequestBody Role role) {
        QueryWrapper<Role> wrapper = WrapperUtil.createQueryWrapper(role);
        Role one = roleService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增角色")
    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Role role) {
        Boolean code = roleService.save(role);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增角色")
    @ApiOperation(value = "批量新增角色")
    @ApiImplicitParam(value = "角色列表", name = "roleList", dataType = "List<Role>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Role> roleList) {
        Boolean code = roleService.saveBatch(roleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改角色")
    @ApiOperation(value = "根据id修改角色")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Role role) {
        Boolean code = roleService.updateById(role);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改角色")
    @ApiOperation(value = "根据ids批量修改角色")
    @ApiImplicitParam(value = "角色列表", name = "roleList", dataType = "List<Role>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Role> roleList) {
        Boolean code = roleService.updateBatchById(roleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除角色")
    @ApiOperation(value = "根据id删除角色")
    @ApiImplicitParam(value = "角色id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除角色")
    @ApiOperation(value = "根据ids批量删除角色")
    @ApiImplicitParam(value = "角色id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除角色")
    @ApiOperation(value = "根据条件批量删除角色")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Role role) {
        QueryWrapper<Role> wrapper = WrapperUtil.createQueryWrapper(role);
        Boolean code = roleService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据用户id查询角色名称列表")
    @ApiOperation(value = "根据用户id查询角色名称列表")
    @ApiImplicitParam(value = "用户id", name = "userId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/list/name/{userId}")
    public ResponseEntity<List<String>> listRoleName(@PathVariable(value = "userId") Long userId) {
        List<String> roleNameList = roleService.listRoleName(userId);
        return ResponseEntity.ok(roleNameList);
    }
}
