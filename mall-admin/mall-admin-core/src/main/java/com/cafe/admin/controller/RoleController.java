package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.Role;
import com.cafe.admin.service.RoleService;
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
 * @Description: 角色接口
 */
@Api(value = "角色接口")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
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
        Wrapper<Role> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(role);
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
        Wrapper<Role> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(role);
        Page<Role> rolePage = roleService.page(page, wrapper);
        return ResponseEntity.ok(rolePage);
    }

    @LogPrint(value = "根据id查询单个角色")
    @ApiOperation(value = "根据id查询单个角色")
    @ApiImplicitParam(value = "角色id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Role> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>().eq(Role::getId, id);
        Role role = roleService.getOne(wrapper);
        return ResponseEntity.ok(role);
    }

    @LogPrint(value = "新增角色")
    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Role role) {
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        Boolean code = roleService.save(role);
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

    @LogPrint(value = "分页查询角色列表")
    @ApiOperation(value = "分页查询角色列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "query", required = true)
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Role>> soapPage(
        @RequestParam(value = "current") Long current,
        @RequestParam(value = "size") Long size
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        Page<Role> rolePage = roleService.page(page);
        return ResponseEntity.ok(rolePage);
    }

    @LogPrint(value = "根据条件分页查询角色")
    @ApiOperation(value = "根据条件分页查询角色")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "query", required = true),
        @ApiImplicitParam(value = "角色Model", name = "role", dataType = "Role", paramType = "body", required = true)
    })
    @PostMapping(value = "/page")
    public ResponseEntity<Page<Role>> soapPage(
        @RequestParam(value = "current") Long current,
        @RequestParam(value = "size") Long size,
        @RequestBody Role role
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        Wrapper<Role> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(role);
        Page<Role> rolePage = roleService.page(page, wrapper);
        return ResponseEntity.ok(rolePage);
    }

    @LogPrint(value = "根据id查询单个角色")
    @ApiOperation(value = "根据id查询单个角色")
    @ApiImplicitParam(value = "角色id", name = "id", dataType = "Long", paramType = "query", required = true)
    @GetMapping(value = "/one")
    public ResponseEntity<Role> soapOne(@RequestParam(value = "id") Long id) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>().eq(Role::getId, id);
        Role role = roleService.getOne(wrapper);
        return ResponseEntity.ok(role);
    }

    @LogPrint(value = "根据id删除角色")
    @ApiOperation(value = "根据id删除角色")
    @ApiImplicitParam(value = "角色id", name = "id", dataType = "Long", paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> soapDelete(@RequestParam(value = "id") Long id) {
        Boolean code = roleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "查询所有角色名称列表")
    @ApiOperation(value = "查询所有角色名称列表")
    @GetMapping(value = "/list/name")
    public ResponseEntity<List<String>> listRoleName() {
        List<String> roleNameList = roleService.listRoleName();
        return ResponseEntity.ok(roleNameList);
    }

    @LogPrint(value = "根据管理员id查询角色名称列表")
    @ApiOperation(value = "根据管理员id查询角色名称列表")
    @ApiImplicitParam(value = "管理员id", name = "adminId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/list/name/{adminId}")
    public ResponseEntity<List<String>> listRoleNameByAdminId(@PathVariable(value = "adminId") Long adminId) {
        List<String> roleNameList = roleService.listRoleNameByAdminId(adminId);
        return ResponseEntity.ok(roleNameList);
    }
}
