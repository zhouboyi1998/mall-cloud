package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.Role;
import com.cafe.admin.service.RoleService;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员角色 (控制器)
 */
@Api(value = "管理员角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "查询管理员角色列表")
    @GetMapping("/list")
    public ResponseEntity<List<Role>> list() {
        List<Role> roleList = roleService.list();
        return ResponseEntity.ok(roleList);
    }

    @ApiOperation(value = "根据条件查询管理员角色列表")
    @ApiImplicitParam(name = "role", value = "管理员角色Model", required = true, paramType = "body", dataType = "Role")
    @PostMapping("/list")
    public ResponseEntity<List<Role>> listByWrapper(@RequestBody Role role) {
        Wrapper<Role> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(role);
        List<Role> roleList = roleService.list(wrapper);
        return ResponseEntity.ok(roleList);
    }

    @ApiOperation("分页查询管理员角色列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Role>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        Page<Role> rolePage = roleService.page(page);
        return ResponseEntity.ok(rolePage);
    }

    @ApiOperation(value = "根据条件分页查询管理员角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "role", value = "管理员角色Model", required = true, paramType = "body", dataType = "Role")
    })
    @PostMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Role>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Role role
    ) {
        Page<Role> page = new Page<Role>().setCurrent(current).setSize(size);
        Wrapper<Role> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(role);
        Page<Role> rolePage = roleService.page(page, wrapper);
        return ResponseEntity.ok(rolePage);
    }

    @ApiOperation(value = "根据id查询单个管理员角色")
    @ApiImplicitParam(name = "id", value = "管理员角色id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/one/{id}")
    public ResponseEntity<Role> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>().eq(Role::getId, id);
        Role role = roleService.getOne(wrapper);
        return ResponseEntity.ok(role);
    }

    @ApiOperation(value = "新增管理员角色")
    @ApiImplicitParam(name = "role", value = "管理员角色Model", required = true, paramType = "body", dataType = "Role")
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Role role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        Boolean code = roleService.save(role);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改管理员角色")
    @ApiImplicitParam(name = "role", value = "管理员角色Model", required = true, paramType = "body", dataType = "Role")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Role role) {
        Boolean code = roleService.updateById(role);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改管理员角色")
    @ApiImplicitParam(name = "roleList", value = "管理员角色列表", required = true, paramType = "body", dataType = "List<Role>")
    @PutMapping("/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Role> roleList) {
        Boolean code = roleService.updateBatchById(roleList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除管理员角色")
    @ApiImplicitParam(name = "id", value = "管理员角色id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除管理员角色")
    @ApiImplicitParam(name = "ids", value = "管理员角色id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping("/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据管理员id查询角色名称列表")
    @ApiImplicitParam(name = "adminId", value = "管理员id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/list/name/{adminId}")
    public ResponseEntity<List<String>> listRoleName(@PathVariable(value = "adminId") Long adminId) {
        List<String> roleNameList = roleService.listRoleName(adminId);
        return ResponseEntity.ok(roleNameList);
    }
}
