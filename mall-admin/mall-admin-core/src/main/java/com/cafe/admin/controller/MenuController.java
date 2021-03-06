package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.Menu;
import com.cafe.admin.service.MenuService;
import com.cafe.admin.vo.MenuTreeVO;
import com.cafe.common.core.util.MyBatisPlusWrapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单 (控制器)
 */
@Api(value = "菜单接口")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "查询菜单列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Menu>> list() {
        List<Menu> menuList = menuService.list();
        return ResponseEntity.ok(menuList);
    }

    @ApiOperation(value = "根据条件查询菜单列表")
    @ApiImplicitParam(name = "menu", value = "菜单Model", required = true, paramType = "body", dataType = "Menu")
    @PostMapping(value = "/list")
    public ResponseEntity<List<Menu>> listByWrapper(@RequestBody Menu menu) {
        Wrapper<Menu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(menu);
        List<Menu> menuList = menuService.list(wrapper);
        return ResponseEntity.ok(menuList);
    }

    @ApiOperation(value = "分页查询菜单列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Menu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Menu> page = new Page<Menu>().setCurrent(current).setSize(size);
        Page<Menu> menuPage = menuService.page(page);
        return ResponseEntity.ok(menuPage);
    }

    @ApiOperation(value = "根据条件分页查询菜单")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "menu", value = "菜单Model", required = true, paramType = "body", dataType = "Menu")
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Menu>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Menu menu
    ) {
        Page<Menu> page = new Page<Menu>().setCurrent(current).setSize(size);
        Wrapper<Menu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(menu);
        Page<Menu> menuPage = menuService.page(page, wrapper);
        return ResponseEntity.ok(menuPage);
    }

    @ApiOperation(value = "分页查询菜单列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/page")
    public ResponseEntity<Page<Menu>> pageByParam(@RequestParam Long current, @RequestParam Long size) {
        Page<Menu> page = new Page<Menu>().setCurrent(current).setSize(size);
        Page<Menu> menuPage = menuService.page(page);
        return ResponseEntity.ok(menuPage);
    }

    @ApiOperation(value = "根据条件分页查询菜单")
    @ApiImplicitParam(name = "page", value = "分页查询参数", required = true, paramType = "body", dataType = "Page<Menu>")
    @PostMapping(value = "/page")
    public ResponseEntity<Page<Menu>> pageByWrapper(@RequestBody Page<Menu> page) {
        Menu menu = page.getRecords().get(0);
        Wrapper<Menu> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(menu);
        Page<Menu> menuPage = menuService.page(page, wrapper);
        return ResponseEntity.ok(menuPage);
    }

    @ApiOperation(value = "根据id查询单个菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Menu> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<Menu>().eq(Menu::getId, id);
        Menu menu = menuService.getOne(wrapper);
        return ResponseEntity.ok(menu);
    }

    @ApiOperation(value = "根据id查询单个菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "query", dataType = "Long")
    @GetMapping(value = "/one")
    public ResponseEntity<Menu> one2(@RequestParam Long id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<Menu>().eq(Menu::getId, id);
        Menu menu = menuService.getOne(wrapper);
        return ResponseEntity.ok(menu);
    }

    @ApiOperation(value = "新增菜单")
    @ApiImplicitParam(name = "menu", value = "菜单Model", required = true, paramType = "body", dataType = "Menu")
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Menu menu) {
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        Boolean code = menuService.save(menu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改菜单")
    @ApiImplicitParam(name = "menu", value = "菜单Model", required = true, paramType = "body", dataType = "Menu")
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Menu menu) {
        Boolean code = menuService.updateById(menu);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改菜单")
    @ApiImplicitParam(name = "menuList", value = "菜单列表", required = true, paramType = "body", dataType = "List<Menu>")
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Menu> menuList) {
        Boolean code = menuService.updateBatchById(menuList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = menuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除菜单")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete2(@RequestParam Long id) {
        Boolean code = menuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除菜单")
    @ApiImplicitParam(name = "ids", value = "菜单id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = menuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "获取树形格式的菜单列表")
    @GetMapping(value = "/listMenuTree")
    public ResponseEntity<List<MenuTreeVO>> listMenuTree(HttpServletRequest request) {
        List<MenuTreeVO> menuTreeVOList = menuService.listMenuTree(request);
        return ResponseEntity.ok(menuTreeVOList);
    }
}
