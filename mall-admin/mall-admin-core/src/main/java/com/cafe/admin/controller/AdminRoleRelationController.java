package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.AdminRoleRelation;
import com.cafe.admin.service.AdminRoleRelationService;
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
 * @Description: 用户-角色关联 (控制器)
 */
@Api(value = "用户-角色关联接口")
@RestController
@RequestMapping("/adminRoleRelation")
public class AdminRoleRelationController {

    private AdminRoleRelationService adminRoleRelationService;

    @Autowired
    public AdminRoleRelationController(AdminRoleRelationService adminRoleRelationService) {
        this.adminRoleRelationService = adminRoleRelationService;
    }

    @ApiOperation(value = "查询用户-角色关联列表")
    @GetMapping("/list")
    public ResponseEntity<List<AdminRoleRelation>> list() {
        List<AdminRoleRelation> adminRoleRelationList = adminRoleRelationService.list();
        return ResponseEntity.ok(adminRoleRelationList);
    }

    @ApiOperation(value = "根据条件查询用户-角色关联列表")
    @ApiImplicitParam(name = "adminRoleRelation", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRoleRelation")
    @PostMapping("/list")
    public ResponseEntity<List<AdminRoleRelation>> listByWrapper(@RequestBody AdminRoleRelation adminRoleRelation) {
        Wrapper<AdminRoleRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRoleRelation);
        List<AdminRoleRelation> adminRoleRelationList = adminRoleRelationService.list(wrapper);
        return ResponseEntity.ok(adminRoleRelationList);
    }

    @ApiOperation("分页查询用户-角色关联列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<AdminRoleRelation>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<AdminRoleRelation> page = new Page<AdminRoleRelation>().setCurrent(current).setSize(size);
        Page<AdminRoleRelation> adminRoleRelationPage = adminRoleRelationService.page(page);
        return ResponseEntity.ok(adminRoleRelationPage);
    }

    @ApiOperation(value = "根据条件分页查询用户-角色关联")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "adminRoleRelation", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRoleRelation")
    })
    @PostMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<AdminRoleRelation>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody AdminRoleRelation adminRoleRelation
    ) {
        Page<AdminRoleRelation> page = new Page<AdminRoleRelation>().setCurrent(current).setSize(size);
        Wrapper<AdminRoleRelation> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(adminRoleRelation);
        Page<AdminRoleRelation> adminRoleRelationPage = adminRoleRelationService.page(page, wrapper);
        return ResponseEntity.ok(adminRoleRelationPage);
    }

    @ApiOperation(value = "根据id查询单个用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/one/{id}")
    public ResponseEntity<AdminRoleRelation> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<AdminRoleRelation> wrapper = new LambdaQueryWrapper<AdminRoleRelation>().eq(AdminRoleRelation::getId, id);
        AdminRoleRelation adminRoleRelation = adminRoleRelationService.getOne(wrapper);
        return ResponseEntity.ok(adminRoleRelation);
    }

    @ApiOperation(value = "新增用户-角色关联")
    @ApiImplicitParam(name = "adminRoleRelation", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRoleRelation")
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody AdminRoleRelation adminRoleRelation) {
        adminRoleRelation.setCreateTime(new Date());
        adminRoleRelation.setUpdateTime(new Date());
        Boolean code = adminRoleRelationService.save(adminRoleRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改用户-角色关联")
    @ApiImplicitParam(name = "adminRoleRelation", value = "用户-角色关联Model", required = true, paramType = "body", dataType = "AdminRoleRelation")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody AdminRoleRelation adminRoleRelation) {
        Boolean code = adminRoleRelationService.updateById(adminRoleRelation);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改用户-角色关联")
    @ApiImplicitParam(name = "adminRoleRelationList", value = "用户-角色关联列表", required = true, paramType = "body", dataType = "List<AdminRoleRelation>")
    @PutMapping("/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<AdminRoleRelation> adminRoleRelationList) {
        Boolean code = adminRoleRelationService.updateBatchById(adminRoleRelationList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除用户-角色关联")
    @ApiImplicitParam(name = "id", value = "用户-角色关联id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = adminRoleRelationService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除用户-角色关联")
    @ApiImplicitParam(name = "ids", value = "用户-角色关联id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping("/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = adminRoleRelationService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
