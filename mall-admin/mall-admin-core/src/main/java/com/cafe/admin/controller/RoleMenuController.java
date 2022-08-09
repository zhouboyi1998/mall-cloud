package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.bo.MenuRoleRelationBO;
import com.cafe.admin.model.RoleMenu;
import com.cafe.admin.service.RoleMenuService;
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
@RequestMapping(value = "/roleMenu")
public class RoleMenuController {

    private RoleMenuService roleMenuService;

    @Autowired
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @ApiOperation(value = "查询角色-菜单关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<RoleMenu>> list() {
        List<RoleMenu> roleMenuList = roleMenuService.list();
        return ResponseEntity.ok(roleMenuList);
    }

    @ApiOperation(value = "根据条件查询角色-菜单关联列表")
    @ApiImplicitParam(name = "roleMenu", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenu")
    @PostMapping(value = "/list")
    public ResponseEntity<List<RoleMenu>> list(@RequestBody RoleMenu roleMenu) {
        Wrapper<RoleMenu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenu);
        List<RoleMenu> roleMenuList = roleMenuService.list(wrapper);
        return ResponseEntity.ok(roleMenuList);
    }

    @ApiOperation(value = "分页查询角色-菜单关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<RoleMenu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<RoleMenu> page = new Page<RoleMenu>().setCurrent(current).setSize(size);
        Page<RoleMenu> roleMenuPage = roleMenuService.page(page);
        return ResponseEntity.ok(roleMenuPage);
    }

    @ApiOperation(value = "根据条件分页查询角色-菜单关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "roleMenu", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenu")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<RoleMenu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody RoleMenu roleMenu
    ) {
        Page<RoleMenu> page = new Page<RoleMenu>().setCurrent(current).setSize(size);
        Wrapper<RoleMenu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenu);
        Page<RoleMenu> roleMenuPage = roleMenuService.page(page, wrapper);
        return ResponseEntity.ok(roleMenuPage);
    }

    @ApiOperation(value = "根据id查询单个角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleMenu> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getId, id);
        RoleMenu roleMenu = roleMenuService.getOne(wrapper);
        return ResponseEntity.ok(roleMenu);
    }

    @ApiOperation(value = "新增角色-菜单关联")
    @ApiImplicitParam(name = "roleMenu", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenu")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody RoleMenu roleMenu) {
        roleMenu.setCreateTime(LocalDateTime.now());
        roleMenu.setUpdateTime(LocalDateTime.now());
        Boolean code = roleMenuService.save(roleMenu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改角色-菜单关联")
    @ApiImplicitParam(name = "roleMenu", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenu")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody RoleMenu roleMenu) {
        Boolean code = roleMenuService.updateById(roleMenu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改角色-菜单关联")
    @ApiImplicitParam(name = "roleMenuList", value = "角色-菜单关联列表", required = true, paramType = "body", dataType = "List<RoleMenu>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<RoleMenu> roleMenuList) {
        Boolean code = roleMenuService.updateBatchById(roleMenuList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleMenuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除角色-菜单关联")
    @ApiImplicitParam(name = "ids", value = "角色-菜单关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleMenuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "分页查询角色-菜单关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<RoleMenu>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<RoleMenu> page = new Page<RoleMenu>().setCurrent(current).setSize(size);
        Page<RoleMenu> roleMenuPage = roleMenuService.page(page);
        return ResponseEntity.ok(roleMenuPage);
    }

    @ApiOperation(value = "根据条件分页查询角色-菜单关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "roleMenu", value = "角色-菜单关联Model", required = true, paramType = "body", dataType = "RoleMenu")
    })
    @PostMapping(value = "/page")
    public ResponseEntity<Page<RoleMenu>> pageByParam(
        @RequestParam Long current,
        @RequestParam Long size,
        @RequestBody RoleMenu roleMenu
    ) {
        Page<RoleMenu> page = new Page<RoleMenu>().setCurrent(current).setSize(size);
        Wrapper<RoleMenu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenu);
        Page<RoleMenu> roleMenuPage = roleMenuService.page(page, wrapper);
        return ResponseEntity.ok(roleMenuPage);
    }

    @ApiOperation(value = "根据id查询单个角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<RoleMenu> oneByParam(@RequestParam Long id) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getId, id);
        RoleMenu roleMenu = roleMenuService.getOne(wrapper);
        return ResponseEntity.ok(roleMenu);
    }

    @ApiOperation(value = "根据id删除角色-菜单关联")
    @ApiImplicitParam(name = "id", value = "角色-菜单关联id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> deleteByParam(@RequestParam Long id) {
        Boolean code = roleMenuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "获取所有菜单路径和角色名称对应关系")
    @GetMapping(value = "/list/menuPath/roleName/bo")
    public ResponseEntity<List<MenuRoleRelationBO>> listMenuRoleRelationBO() {
        List<Long> menuIds = new ArrayList<Long>();
        List<MenuRoleRelationBO> boList = roleMenuService.listMenuRoleRelationBO(menuIds);
        return ResponseEntity.ok(boList);
    }

    @ApiOperation(value = "根据 菜单id列表 获取菜单路径和角色名称对应关系列表")
    @ApiImplicitParam(name = "menuIds", value = "菜单id列表", required = true, paramType = "body", dataType = "List<Long>")
    @PostMapping(value = "/list/menuPath/roleName/bo")
    public ResponseEntity<List<MenuRoleRelationBO>> listMenuRoleRelationBO(@RequestBody List<Long> menuIds) {
        List<MenuRoleRelationBO> boList = roleMenuService.listMenuRoleRelationBO(menuIds);
        return ResponseEntity.ok(boList);
    }
}
