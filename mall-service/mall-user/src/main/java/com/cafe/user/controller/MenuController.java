package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.core.request.UserDetails;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.user.model.Menu;
import com.cafe.user.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 * @Description: 菜单接口
 */
@Api(value = "菜单接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    @ApiLogPrint(value = "查询菜单数量")
    @ApiOperation(value = "查询菜单数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = menuService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询菜单数量")
    @ApiOperation(value = "根据条件查询菜单数量")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Menu menu) {
        QueryWrapper<Menu> wrapper = WrapperUtil.createQueryWrapper(menu);
        Integer count = menuService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询菜单列表")
    @ApiOperation(value = "查询菜单列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Menu>> list() {
        List<Menu> menuList = menuService.list();
        return ResponseEntity.ok(menuList);
    }

    @ApiLogPrint(value = "根据条件查询菜单列表")
    @ApiOperation(value = "根据条件查询菜单列表")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Menu>> list(@RequestBody Menu menu) {
        QueryWrapper<Menu> wrapper = WrapperUtil.createQueryWrapper(menu);
        List<Menu> menuList = menuService.list(wrapper);
        return ResponseEntity.ok(menuList);
    }

    @ApiLogPrint(value = "分页查询菜单列表")
    @ApiOperation(value = "分页查询菜单列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Menu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Menu> page = new Page<>(current, size);
        Page<Menu> menuPage = menuService.page(page);
        return ResponseEntity.ok(menuPage);
    }

    @ApiLogPrint(value = "根据条件分页查询菜单")
    @ApiOperation(value = "根据条件分页查询菜单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Menu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Menu menu
    ) {
        Page<Menu> page = new Page<>(current, size);
        QueryWrapper<Menu> wrapper = WrapperUtil.createQueryWrapper(menu);
        Page<Menu> menuPage = menuService.page(page, wrapper);
        return ResponseEntity.ok(menuPage);
    }

    @ApiLogPrint(value = "根据id查询单个菜单")
    @ApiOperation(value = "根据id查询单个菜单")
    @ApiImplicitParam(value = "菜单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Menu> one(@PathVariable(value = "id") Long id) {
        Menu menu = menuService.getById(id);
        return ResponseEntity.ok(menu);
    }

    @ApiLogPrint(value = "根据条件查询单个菜单")
    @ApiOperation(value = "根据条件查询单个菜单")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Menu> one(@RequestBody Menu menu) {
        QueryWrapper<Menu> wrapper = WrapperUtil.createQueryWrapper(menu);
        Menu one = menuService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增菜单")
    @ApiOperation(value = "新增菜单")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Menu menu) {
        Boolean code = menuService.save(menu);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增菜单")
    @ApiOperation(value = "批量新增菜单")
    @ApiImplicitParam(value = "菜单列表", name = "menuList", dataType = "List<Menu>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Menu> menuList) {
        Boolean code = menuService.saveBatch(menuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改菜单")
    @ApiOperation(value = "根据id修改菜单")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Menu menu) {
        Boolean code = menuService.updateById(menu);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改菜单")
    @ApiOperation(value = "根据ids批量修改菜单")
    @ApiImplicitParam(value = "菜单列表", name = "menuList", dataType = "List<Menu>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Menu> menuList) {
        Boolean code = menuService.updateBatchById(menuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除菜单")
    @ApiOperation(value = "根据id删除菜单")
    @ApiImplicitParam(value = "菜单id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = menuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除菜单")
    @ApiOperation(value = "根据ids批量删除菜单")
    @ApiImplicitParam(value = "菜单id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = menuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除菜单")
    @ApiOperation(value = "根据条件批量删除菜单")
    @ApiImplicitParam(value = "菜单Model", name = "menu", dataType = "Menu", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Menu menu) {
        QueryWrapper<Menu> wrapper = WrapperUtil.createQueryWrapper(menu);
        Boolean code = menuService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据权限列表查询菜单树列表")
    @ApiOperation(value = "根据权限列表查询菜单树列表")
    @GetMapping(value = "/tree-list")
    public ResponseEntity<List<Tree>> treeList(
        @ModelAttribute(value = RequestConstant.ModelAttribute.USER_DETAILS) UserDetails userDetails
    ) {
        List<Tree> treeList = menuService.treeList(userDetails.getAuthorities());
        return ResponseEntity.ok(treeList);
    }
}
