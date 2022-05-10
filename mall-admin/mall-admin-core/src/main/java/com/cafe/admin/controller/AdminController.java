package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.Admin;
import com.cafe.admin.service.AdminService;
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
 * @Description: 管理员 (控制器)
 */
@Api(value = "管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "查询管理员列表")
    @GetMapping("/list")
    public ResponseEntity<List<Admin>> list() {
        List<Admin> adminList = adminService.list();
        return ResponseEntity.ok(adminList);
    }

    @ApiOperation(value = "根据条件查询管理员列表")
    @ApiImplicitParam(name = "admin", value = "管理员Model", required = true, paramType = "body", dataType = "Admin")
    @PostMapping("/list")
    public ResponseEntity<List<Admin>> listByWrapper(@RequestBody Admin admin) {
        Wrapper<Admin> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(admin);
        List<Admin> adminList = adminService.list(wrapper);
        return ResponseEntity.ok(adminList);
    }

    @ApiOperation("分页查询管理员列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Admin>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Admin> page = new Page<Admin>().setCurrent(current).setSize(size);
        Page<Admin> adminPage = adminService.page(page);
        return ResponseEntity.ok(adminPage);
    }

    @ApiOperation(value = "根据条件分页查询管理员")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "admin", value = "管理员Model", required = true, paramType = "body", dataType = "Admin")
    })
    @PostMapping("/page/{current}/{size}")
    public ResponseEntity<IPage<Admin>> pageByWrapper(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Admin admin
    ) {
        Page<Admin> page = new Page<Admin>().setCurrent(current).setSize(size);
        Wrapper<Admin> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(admin);
        Page<Admin> adminPage = adminService.page(page, wrapper);
        return ResponseEntity.ok(adminPage);
    }

    @ApiOperation(value = "根据id查询单个管理员")
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/one/{id}")
    public ResponseEntity<Admin> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>().eq(Admin::getId, id);
        Admin admin = adminService.getOne(wrapper);
        return ResponseEntity.ok(admin);
    }

    @ApiOperation(value = "根据管理员用户名查询单个管理员")
    @ApiImplicitParam(name = "adminName", value = "管理员用户名", required = true, paramType = "path", dataType = "String")
    @GetMapping("/one/name/{adminName}")
    public ResponseEntity<Admin> one(@PathVariable(value = "adminName") String adminName) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>().eq(Admin::getAdminName, adminName);
        Admin admin = adminService.getOne(wrapper);
        return ResponseEntity.ok(admin);
    }

    @ApiOperation(value = "新增管理员")
    @ApiImplicitParam(name = "admin", value = "管理员Model", required = true, paramType = "body", dataType = "Admin")
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Admin admin) {
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        Boolean code = adminService.save(admin);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id修改管理员")
    @ApiImplicitParam(name = "admin", value = "管理员Model", required = true, paramType = "body", dataType = "Admin")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Admin admin) {
        Boolean code = adminService.updateById(admin);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量修改管理员")
    @ApiImplicitParam(name = "adminList", value = "管理员列表", required = true, paramType = "body", dataType = "List<Admin>")
    @PutMapping("/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Admin> adminList) {
        Boolean code = adminService.updateBatchById(adminList);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据id删除管理员")
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = adminService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiOperation(value = "根据ids批量删除管理员")
    @ApiImplicitParam(name = "ids", value = "管理员id列表", required = true, paramType = "body", dataType = "List<Long>")
    @DeleteMapping("/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = adminService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
