package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.user.model.RoleMenu;
import com.cafe.user.service.RoleMenuService;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
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

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联接口
 */
@Api(value = "角色-菜单关联接口")
@RestController
@RequestMapping(value = "/role-menu")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    @Autowired
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @LogPrint(value = "查询角色-菜单关联列表")
    @ApiOperation(value = "查询角色-菜单关联列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<RoleMenu>> list() {
        List<RoleMenu> roleMenuList = roleMenuService.list();
        return ResponseEntity.ok(roleMenuList);
    }

    @LogPrint(value = "根据条件查询角色-菜单关联列表")
    @ApiOperation(value = "根据条件查询角色-菜单关联列表")
    @ApiImplicitParam(value = "角色-菜单关联Model", name = "roleMenu", dataType = "RoleMenu", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<RoleMenu>> list(@RequestBody RoleMenu roleMenu) {
        Wrapper<RoleMenu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(roleMenu);
        List<RoleMenu> roleMenuList = roleMenuService.list(wrapper);
        return ResponseEntity.ok(roleMenuList);
    }

    @LogPrint(value = "分页查询角色-菜单关联列表")
    @ApiOperation(value = "分页查询角色-菜单关联列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
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

    @LogPrint(value = "根据条件分页查询角色-菜单关联")
    @ApiOperation(value = "根据条件分页查询角色-菜单关联")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "角色-菜单关联Model", name = "roleMenu", dataType = "RoleMenu", paramType = "body", required = true)
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

    @LogPrint(value = "根据id查询单个角色-菜单关联")
    @ApiOperation(value = "根据id查询单个角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleMenu> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getId, id);
        RoleMenu roleMenu = roleMenuService.getOne(wrapper);
        return ResponseEntity.ok(roleMenu);
    }

    @LogPrint(value = "新增角色-菜单关联")
    @ApiOperation(value = "新增角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联Model", name = "roleMenu", dataType = "RoleMenu", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody RoleMenu roleMenu) {
        roleMenu.setCreateTime(LocalDateTime.now());
        roleMenu.setUpdateTime(LocalDateTime.now());
        Boolean code = roleMenuService.save(roleMenu);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改角色-菜单关联")
    @ApiOperation(value = "根据id修改角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联Model", name = "roleMenu", dataType = "RoleMenu", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody RoleMenu roleMenu) {
        Boolean code = roleMenuService.updateById(roleMenu);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改角色-菜单关联")
    @ApiOperation(value = "根据ids批量修改角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联列表", name = "roleMenuList", dataType = "List<RoleMenu>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<RoleMenu> roleMenuList) {
        Boolean code = roleMenuService.updateBatchById(roleMenuList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除角色-菜单关联")
    @ApiOperation(value = "根据id删除角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleMenuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除角色-菜单关联")
    @ApiOperation(value = "根据ids批量删除角色-菜单关联")
    @ApiImplicitParam(value = "角色-菜单关联id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleMenuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
