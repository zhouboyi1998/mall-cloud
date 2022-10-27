package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.AdminRole;
import com.cafe.admin.service.AdminRoleService;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.common.log.annotation.LogPrint;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联接口
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

    @LogPrint(value = "查询用户-角色关联列表")
    @ApiOperation(value = "查询用户-角色关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<AdminRole>> list() {
        List<AdminRole> adminRoleList = adminRoleService.list();
        return ResponseEntity.ok(adminRoleList);
    }

    @LogPrint(value = "根据条件查询用户-角色关联列表")
    @ApiOperation(value = "根据条件查询用户-角色关联列表")
    @ApiImplicitParam(value = "用户-角色关联Model", name = "adminRole", dataType = "AdminRole", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<AdminRole>> list(@RequestBody AdminRole adminRole) {
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        return ResponseEntity.ok(adminRoleList);
    }

    @LogPrint(value = "分页查询用户-角色关联列表")
    @ApiOperation(value = "分页查询用户-角色关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
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

    @LogPrint(value = "根据条件分页查询用户-角色关联")
    @ApiOperation(value = "根据条件分页查询用户-角色关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "用户-角色关联Model", name = "adminRole", dataType = "AdminRole", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<AdminRole>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody AdminRole adminRole
    ) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        Page<AdminRole> adminRolePage = adminRoleService.page(page, wrapper);
        return ResponseEntity.ok(adminRolePage);
    }

    @LogPrint(value = "根据id查询单个用户-角色关联")
    @ApiOperation(value = "根据id查询单个用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<AdminRole> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getId, id);
        AdminRole adminRole = adminRoleService.getOne(wrapper);
        return ResponseEntity.ok(adminRole);
    }

    @LogPrint(value = "新增用户-角色关联")
    @ApiOperation(value = "新增用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联Model", name = "adminRole", dataType = "AdminRole", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody AdminRole adminRole) {
        adminRole.setCreateTime(LocalDateTime.now());
        adminRole.setUpdateTime(LocalDateTime.now());
        Boolean code = adminRoleService.save(adminRole);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改用户-角色关联")
    @ApiOperation(value = "根据id修改用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联Model", name = "adminRole", dataType = "AdminRole", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody AdminRole adminRole) {
        Boolean code = adminRoleService.updateById(adminRole);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改用户-角色关联")
    @ApiOperation(value = "根据ids批量修改用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联列表", name = "adminRoleList", dataType = "List<AdminRole>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<AdminRole> adminRoleList) {
        Boolean code = adminRoleService.updateBatchById(adminRoleList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除用户-角色关联")
    @ApiOperation(value = "根据id删除用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = adminRoleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除用户-角色关联")
    @ApiOperation(value = "根据ids批量删除用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = adminRoleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "分页查询用户-角色关联列表")
    @ApiOperation(value = "分页查询用户-角色关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "query", required = true)
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<AdminRole>> soapPage(
        @RequestParam(value = "current") Long current,
        @RequestParam(value = "size") Long size
    ) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Page<AdminRole> adminRolePage = adminRoleService.page(page);
        return ResponseEntity.ok(adminRolePage);
    }

    @LogPrint(value = "根据条件分页查询用户-角色关联")
    @ApiOperation(value = "根据条件分页查询用户-角色关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "用户-角色关联Model", name = "adminRole", dataType = "AdminRole", paramType = "body", required = true)
    })
    @PostMapping(value = "/page")
    public ResponseEntity<Page<AdminRole>> soapPage(
        @RequestParam(value = "current") Long current,
        @RequestParam(value = "size") Long size,
        @RequestBody AdminRole adminRole
    ) {
        Page<AdminRole> page = new Page<AdminRole>().setCurrent(current).setSize(size);
        Wrapper<AdminRole> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRole);
        Page<AdminRole> adminRolePage = adminRoleService.page(page, wrapper);
        return ResponseEntity.ok(adminRolePage);
    }

    @LogPrint(value = "根据id查询单个用户-角色关联")
    @ApiOperation(value = "根据id查询单个用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联id", name = "id", dataType = "Long", paramType = "query", required = true)
    @GetMapping(value = "/one")
    public ResponseEntity<AdminRole> soapOne(@RequestParam(value = "id") Long id) {
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getId, id);
        AdminRole adminRole = adminRoleService.getOne(wrapper);
        return ResponseEntity.ok(adminRole);
    }

    @LogPrint(value = "根据id删除用户-角色关联")
    @ApiOperation(value = "根据id删除用户-角色关联")
    @ApiImplicitParam(value = "用户-角色关联id", name = "id", dataType = "Long", paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> soapDelete(@RequestParam(value = "id") Long id) {
        Boolean code = adminRoleService.removeById(id);
        return ResponseEntity.ok(code);
    }
}
