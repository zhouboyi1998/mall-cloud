package com.cafe.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.admin.model.Admin;
import com.cafe.admin.service.AdminService;
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
 * @Package: com.cafe.admin.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 管理员接口
 */
@Api(value = "管理员接口")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @LogPrint(value = "查询管理员列表")
    @ApiOperation(value = "查询管理员列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Admin>> list() {
        List<Admin> adminList = adminService.list();
        return ResponseEntity.ok(adminList);
    }

    @LogPrint(value = "根据条件查询管理员列表")
    @ApiOperation(value = "根据条件查询管理员列表")
    @ApiImplicitParam(value = "管理员Model", name = "admin", dataType = "Admin", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Admin>> list(@RequestBody Admin admin) {
        Wrapper<Admin> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(admin);
        List<Admin> adminList = adminService.list(wrapper);
        return ResponseEntity.ok(adminList);
    }

    @LogPrint(value = "分页查询管理员列表")
    @ApiOperation(value = "分页查询管理员列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Admin>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Admin> page = new Page<Admin>().setCurrent(current).setSize(size);
        Page<Admin> adminPage = adminService.page(page);
        return ResponseEntity.ok(adminPage);
    }

    @LogPrint(value = "根据条件分页查询管理员")
    @ApiOperation(value = "根据条件分页查询管理员")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "管理员Model", name = "admin", dataType = "Admin", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Admin>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Admin admin
    ) {
        Page<Admin> page = new Page<Admin>().setCurrent(current).setSize(size);
        Wrapper<Admin> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(admin);
        Page<Admin> adminPage = adminService.page(page, wrapper);
        return ResponseEntity.ok(adminPage);
    }

    @LogPrint(value = "根据id查询单个管理员")
    @ApiOperation(value = "根据id查询单个管理员")
    @ApiImplicitParam(value = "管理员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Admin> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>().eq(Admin::getId, id);
        Admin admin = adminService.getOne(wrapper);
        return ResponseEntity.ok(admin);
    }

    @LogPrint(value = "新增管理员")
    @ApiOperation(value = "新增管理员")
    @ApiImplicitParam(value = "管理员Model", name = "admin", dataType = "Admin", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Admin admin) {
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        Boolean code = adminService.save(admin);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改管理员")
    @ApiOperation(value = "根据id修改管理员")
    @ApiImplicitParam(value = "管理员Model", name = "admin", dataType = "Admin", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Admin admin) {
        Boolean code = adminService.updateById(admin);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改管理员")
    @ApiOperation(value = "根据ids批量修改管理员")
    @ApiImplicitParam(value = "管理员列表", name = "adminList", dataType = "List<Admin>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Admin> adminList) {
        Boolean code = adminService.updateBatchById(adminList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除管理员")
    @ApiOperation(value = "根据id删除管理员")
    @ApiImplicitParam(value = "管理员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = adminService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除管理员")
    @ApiOperation(value = "根据ids批量删除管理员")
    @ApiImplicitParam(value = "管理员id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = adminService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据管理员用户名查询单个管理员")
    @ApiOperation(value = "根据管理员用户名查询单个管理员")
    @ApiImplicitParam(value = "管理员用户名", name = "adminName", dataType = "String", paramType = "path", required = true)
    @GetMapping(value = "/one/name/{adminName}")
    public ResponseEntity<Admin> one(@PathVariable(value = "adminName") String adminName) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
            .select(Admin.class, tableFieldInfo -> true)
            .eq(Admin::getAdminName, adminName);
        Admin admin = adminService.getOne(wrapper);
        return ResponseEntity.ok(admin);
    }
}
