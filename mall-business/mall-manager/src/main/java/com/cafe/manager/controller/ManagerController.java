package com.cafe.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.manager.model.Manager;
import com.cafe.manager.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
 * @Package: com.cafe.manager.controller
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 管理员接口
 */
@Api(value = "管理员接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    private final ManagerService managerService;

    @ApiLogPrint(value = "查询管理员数量")
    @ApiOperation(value = "查询管理员数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = managerService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询管理员数量")
    @ApiOperation(value = "根据条件查询管理员数量")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Manager manager) {
        QueryWrapper<Manager> wrapper = WrapperUtil.createQueryWrapper(manager);
        Integer count = managerService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询管理员列表")
    @ApiOperation(value = "查询管理员列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Manager>> list() {
        List<Manager> managerList = managerService.list();
        return ResponseEntity.ok(managerList);
    }

    @ApiLogPrint(value = "根据条件查询管理员列表")
    @ApiOperation(value = "根据条件查询管理员列表")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Manager>> list(@RequestBody Manager manager) {
        QueryWrapper<Manager> wrapper = WrapperUtil.createQueryWrapper(manager);
        List<Manager> managerList = managerService.list(wrapper);
        return ResponseEntity.ok(managerList);
    }

    @ApiLogPrint(value = "分页查询管理员列表")
    @ApiOperation(value = "分页查询管理员列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Manager>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Manager> page = new Page<>(current, size);
        Page<Manager> managerPage = managerService.page(page);
        return ResponseEntity.ok(managerPage);
    }

    @ApiLogPrint(value = "根据条件分页查询管理员")
    @ApiOperation(value = "根据条件分页查询管理员")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Manager>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Manager manager
    ) {
        Page<Manager> page = new Page<>(current, size);
        QueryWrapper<Manager> wrapper = WrapperUtil.createQueryWrapper(manager);
        Page<Manager> managerPage = managerService.page(page, wrapper);
        return ResponseEntity.ok(managerPage);
    }

    @ApiLogPrint(value = "根据id查询单个管理员")
    @ApiOperation(value = "根据id查询单个管理员")
    @ApiImplicitParam(value = "管理员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Manager> one(@PathVariable(value = "id") Long id) {
        Manager manager = managerService.getById(id);
        return ResponseEntity.ok(manager);
    }

    @ApiLogPrint(value = "根据条件查询单个管理员")
    @ApiOperation(value = "根据条件查询单个管理员")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Manager> one(@RequestBody Manager manager) {
        QueryWrapper<Manager> wrapper = WrapperUtil.createQueryWrapper(manager);
        Manager one = managerService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增管理员")
    @ApiOperation(value = "新增管理员")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Manager manager) {
        Boolean code = managerService.save(manager);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增管理员")
    @ApiOperation(value = "批量新增管理员")
    @ApiImplicitParam(value = "管理员列表", name = "managerList", dataType = "List<Manager>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Manager> managerList) {
        Boolean code = managerService.saveBatch(managerList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改管理员")
    @ApiOperation(value = "根据id修改管理员")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Manager manager) {
        Boolean code = managerService.updateById(manager);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改管理员")
    @ApiOperation(value = "根据ids批量修改管理员")
    @ApiImplicitParam(value = "管理员列表", name = "managerList", dataType = "List<Manager>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Manager> managerList) {
        Boolean code = managerService.updateBatchById(managerList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除管理员")
    @ApiOperation(value = "根据id删除管理员")
    @ApiImplicitParam(value = "管理员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = managerService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除管理员")
    @ApiOperation(value = "根据ids批量删除管理员")
    @ApiImplicitParam(value = "管理员id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = managerService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除管理员")
    @ApiOperation(value = "根据条件批量删除管理员")
    @ApiImplicitParam(value = "管理员Model", name = "manager", dataType = "Manager", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Manager manager) {
        QueryWrapper<Manager> wrapper = WrapperUtil.createQueryWrapper(manager);
        Boolean code = managerService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
