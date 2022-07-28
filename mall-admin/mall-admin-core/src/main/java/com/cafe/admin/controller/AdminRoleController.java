package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.AdminRole;
import com.cafe.admin.service.AdminRoleService;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联 (控制器)
 */
@Api(value = "用户-角色关联接口")
@RestController
@RequestMapping(value = "/adminRole")
public class AdminRoleController {

    private AdminRoleService adminRoleService;

    @Autowired
    public AdminRoleController(AdminRoleService adminRoleService) {
        this.adminRoleService = adminRoleService;
    }

    @ApiOperation(value = "查询用户-角色关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<AdminRole>> list() {
        List<AdminRole> adminRoleList = adminRoleService.list();
        return ResponseEntity.ok(adminRoleList);
    }

    @ApiOperation(value = "根据条件查询用户-角色关联列表")
    @ApiImplicitParam(name = "adminRole", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRole")
    @PostMapping(value = "/list")
    public ResponseEntity<List<AdminRole>> listByWrapper(@RequestBody AdminRole adminRole) {
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        return ResponseEntity.ok(adminRoleList);
    }

    @ApiOperation(value = "分页查询用户-角色关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<AdminRole>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Page<AdminRole> adminRolePage = adminRoleService.page(page);
        return ResponseEntity.ok(adminRolePage);
    }

    @ApiOperation(value = "根据条件分页查询用户-角色关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "adminRole", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRole")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<AdminRole>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody AdminRole adminRole
    ) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        Page<AdminRole> adminRolePage = adminRoleService.page(page, wrapper);
        return ResponseEntity.ok(adminRolePage);
    }

    @ApiOperation(value = "分页查询用户-角色关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<AdminRole>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Page<AdminRole> adminRolePage = adminRoleService.page(page);
        return ResponseEntity.ok(adminRolePage);
    }

    @ApiOperation(value = "根据条件分页查询用户-角色关联")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<AdminRole>")
    @PostMapping(value = "/page")
    public ResponseEntity<Page<AdminRole>> pageByWrapper(@RequestBody Page<AdminRole> page) {
        AdminRole adminRole = page.getRecords().get(0);
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        Page<AdminRole> adminRolePage = adminRoleService.page(page, wrapper);
        return ResponseEntity.ok(adminRolePage);
    }

    @ApiOperation(value = "根据id查询单个用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AdminRole> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getId, id);
        AdminRole adminRole = adminRoleService.getOne(wrapper);
        return ResponseEntity.ok(adminRole);
    }

    @ApiOperation(value = "根据id查询单个用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<AdminRole> one2(@RequestParam Long id) {
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getId, id);
        AdminRole adminRole = adminRoleService.getOne(wrapper);
        return ResponseEntity.ok(adminRole);
    }

    @ApiOperation(value = "新增用户-角色关联")
    @ApiImplicitParam(name = "adminRole", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRole")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody AdminRole adminRole) {
        adminRole.setCreateTime(LocalDateTime.now());
        adminRole.setUpdateTime(LocalDateTime.now());
        Boolean code = adminRoleService.save(adminRole);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改用户-角色关联")
    @ApiImplicitParam(name = "adminRole", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRole")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody AdminRole adminRole) {
        Boolean code = adminRoleService.updateById(adminRole);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改用户-角色关联")
    @ApiImplicitParam(name = "adminRoleList", value = "用户-角色关联列表", required = true, paramType = "body", dataType = "List<AdminRole>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<AdminRole> adminRoleList) {
        Boolean code = adminRoleService.updateBatchById(adminRoleList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = adminRoleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = adminRoleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除用户-角色关联")
    @ApiImplicitParam(name = "ids", value = "用户-角色关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = adminRoleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
