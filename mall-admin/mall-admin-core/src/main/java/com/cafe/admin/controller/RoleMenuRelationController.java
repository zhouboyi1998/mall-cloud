package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.bo.MenuPathAndRoleNameBO;
import com.cafe.admin.model.RoleMenuRelation;
import com.cafe.admin.service.RoleMenuRelationService;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (控制器)
 */
@Api(value = "角色-菜单关联接口")
@RestController
@RequestMapping(value = "/roleMenuRelation")
public class RoleMenuRelationController {

    private RoleMenuRelationService roleMenuRelationService;

    @Autowired
    public RoleMenuRelationController(RoleMenuRelationService roleMenuRelationService) {
        this.roleMenuRelationService = roleMenuRelationService;
    }

    @ApiOperation(value = "查询角色-菜单关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<RoleMenuRelation>> list() {
        List<RoleMenuRelation> roleMenuRelationList = roleMenuRelationService.list();
        return ResponseEntity.ok(roleMenuRelationList);
    }

    @ApiOperation(value = "根据条件查询角色-菜单关联列表")
    @ApiImplicitParam(name = "roleMenuRelation", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenuRelation")
    @PostMapping(value = "/list")
    public ResponseEntity<List<RoleMenuRelation>> listByWrapper(@RequestBody RoleMenuRelation roleMenuRelation) {
        Wrapper<RoleMenuRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenuRelation);
        List<RoleMenuRelation> roleMenuRelationList = roleMenuRelationService.list(wrapper);
        return ResponseEntity.ok(roleMenuRelationList);
    }

    @ApiOperation(value = "分页查询角色-菜单关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<RoleMenuRelation>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<RoleMenuRelation> page = new Page<RoleMenuRelation>().setCurrent(current).setSize(size);
        Page<RoleMenuRelation> roleMenuRelationPage = roleMenuRelationService.page(page);
        return ResponseEntity.ok(roleMenuRelationPage);
    }

    @ApiOperation(value = "根据条件分页查询角色-菜单关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "roleMenuRelation", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenuRelation")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<IPage<RoleMenuRelation>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody RoleMenuRelation roleMenuRelation
    ) {
        Page<RoleMenuRelation> page = new Page<RoleMenuRelation>().setCurrent(current).setSize(size);
        Wrapper<RoleMenuRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenuRelation);
        Page<RoleMenuRelation> roleMenuRelationPage = roleMenuRelationService.page(page, wrapper);
        return ResponseEntity.ok(roleMenuRelationPage);
    }

    @ApiOperation(value = "分页查询角色-菜单关联列表")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<RoleMenuRelation>")
    @GetMapping(value = "/page")
    public ResponseEntity<IPage<RoleMenuRelation>> page(@RequestBody Page<RoleMenuRelation> page) {
        Page<RoleMenuRelation> roleMenuRelationPage = roleMenuRelationService.page(page);
        return ResponseEntity.ok(roleMenuRelationPage);
    }

    @ApiOperation(value = "根据条件分页查询角色-菜单关联")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<RoleMenuRelation>")
    @PostMapping(value = "/page")
    public ResponseEntity<IPage<RoleMenuRelation>> pageByWrapper(@RequestBody Page<RoleMenuRelation> page) {
        RoleMenuRelation roleMenuRelation = page.getRecords().get(0);
        Wrapper<RoleMenuRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenuRelation);
        Page<RoleMenuRelation> roleMenuRelationPage = roleMenuRelationService.page(page, wrapper);
        return ResponseEntity.ok(roleMenuRelationPage);
    }

    @ApiOperation(value = "根据id查询单个角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleMenuRelation> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<RoleMenuRelation> wrapper = new LambdaQueryWrapper<RoleMenuRelation>().eq(RoleMenuRelation::getId, id);
        RoleMenuRelation roleMenuRelation = roleMenuRelationService.getOne(wrapper);
        return ResponseEntity.ok(roleMenuRelation);
    }

    @ApiOperation(value = "根据id查询单个角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<RoleMenuRelation> one2(@RequestParam Long id) {
        LambdaQueryWrapper<RoleMenuRelation> wrapper = new LambdaQueryWrapper<RoleMenuRelation>().eq(RoleMenuRelation::getId, id);
        RoleMenuRelation roleMenuRelation = roleMenuRelationService.getOne(wrapper);
        return ResponseEntity.ok(roleMenuRelation);
    }

    @ApiOperation(value = "新增角色-菜单关联")
    @ApiImplicitParam(name = "roleMenuRelation", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenuRelation")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody RoleMenuRelation roleMenuRelation) {
        roleMenuRelation.setCreateTime(LocalDateTime.now());
        roleMenuRelation.setUpdateTime(LocalDateTime.now());
        Boolean code = roleMenuRelationService.save(roleMenuRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改角色-菜单关联")
    @ApiImplicitParam(name = "roleMenuRelation", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenuRelation")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody RoleMenuRelation roleMenuRelation) {
        Boolean code = roleMenuRelationService.updateById(roleMenuRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改角色-菜单关联")
    @ApiImplicitParam(name = "roleMenuRelationList", value = "角色-菜单关联列表", required = true, paramType = "body", dataType = "List<RoleMenuRelation>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<RoleMenuRelation> roleMenuRelationList) {
        Boolean code = roleMenuRelationService.updateBatchById(roleMenuRelationList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleMenuRelationService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = roleMenuRelationService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除角色-菜单关联")
    @ApiImplicitParam(name = "ids", value = "角色-菜单关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleMenuRelationService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "获取所有菜单路径和角色名称对应关系")
    @GetMapping(value = "/list/menuPath/roleName/bo")
    public ResponseEntity<List<MenuPathAndRoleNameBO>> listMenuPathAndRoleNameBO() {
        List<Long> menuIds = new ArrayList<Long>();
        List<MenuPathAndRoleNameBO> boList = roleMenuRelationService.listMenuPathAndRoleNameBO(menuIds);
        return ResponseEntity.ok(boList);
    }

    @ApiOperation(value = "根据 菜单id列表 获取菜单路径和角色名称对应关系列表")
    @ApiImplicitParam(name = "menuIds", value = "菜单id列表", required = true, paramType = "body", dataType = "List<Long>")
    @PostMapping(value = "/list/menuPath/roleName/bo")
    public ResponseEntity<List<MenuPathAndRoleNameBO>> listMenuPathAndRoleNameBO(@RequestBody List<Long> menuIds) {
        List<MenuPathAndRoleNameBO> boList = roleMenuRelationService.listMenuPathAndRoleNameBO(menuIds);
        return ResponseEntity.ok(boList);
    }
}
