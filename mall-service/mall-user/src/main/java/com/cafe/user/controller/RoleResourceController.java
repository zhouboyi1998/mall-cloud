package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.user.facade.RoleResourceFacade;
import com.cafe.user.model.bo.ResourceRoleBO;
import com.cafe.user.model.entity.RoleResource;
import com.cafe.user.service.RoleResourceService;
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
 * @Package: com.cafe.user.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-资源关联关系接口
 */
@Api(value = "角色-资源关联关系接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/role-resource")
public class RoleResourceController {

    private final RoleResourceService roleResourceService;

    private final RoleResourceFacade roleResourceFacade;

    @ApiLogPrint(value = "查询角色-资源关联关系数量")
    @ApiOperation(value = "查询角色-资源关联关系数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = roleResourceService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询角色-资源关联关系数量")
    @ApiOperation(value = "根据条件查询角色-资源关联关系数量")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody RoleResource roleResource) {
        QueryWrapper<RoleResource> wrapper = WrapperUtil.createQueryWrapper(roleResource);
        Integer count = roleResourceService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询角色-资源关联关系列表")
    @ApiOperation(value = "查询角色-资源关联关系列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<RoleResource>> list() {
        List<RoleResource> roleResourceList = roleResourceService.list();
        return ResponseEntity.ok(roleResourceList);
    }

    @ApiLogPrint(value = "根据条件查询角色-资源关联关系列表")
    @ApiOperation(value = "根据条件查询角色-资源关联关系列表")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<RoleResource>> list(@RequestBody RoleResource roleResource) {
        QueryWrapper<RoleResource> wrapper = WrapperUtil.createQueryWrapper(roleResource);
        List<RoleResource> roleResourceList = roleResourceService.list(wrapper);
        return ResponseEntity.ok(roleResourceList);
    }

    @ApiLogPrint(value = "分页查询角色-资源关联关系列表")
    @ApiOperation(value = "分页查询角色-资源关联关系列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<RoleResource>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<RoleResource> page = new Page<>(current, size);
        Page<RoleResource> roleResourcePage = roleResourceService.page(page);
        return ResponseEntity.ok(roleResourcePage);
    }

    @ApiLogPrint(value = "根据条件分页查询角色-资源关联关系")
    @ApiOperation(value = "根据条件分页查询角色-资源关联关系")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<RoleResource>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody RoleResource roleResource
    ) {
        Page<RoleResource> page = new Page<>(current, size);
        QueryWrapper<RoleResource> wrapper = WrapperUtil.createQueryWrapper(roleResource);
        Page<RoleResource> roleResourcePage = roleResourceService.page(page, wrapper);
        return ResponseEntity.ok(roleResourcePage);
    }

    @ApiLogPrint(value = "根据id查询单个角色-资源关联关系")
    @ApiOperation(value = "根据id查询单个角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<RoleResource> one(@PathVariable(value = "id") Long id) {
        RoleResource roleResource = roleResourceService.getById(id);
        return ResponseEntity.ok(roleResource);
    }

    @ApiLogPrint(value = "根据条件查询单个角色-资源关联关系")
    @ApiOperation(value = "根据条件查询单个角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<RoleResource> one(@RequestBody RoleResource roleResource) {
        QueryWrapper<RoleResource> wrapper = WrapperUtil.createQueryWrapper(roleResource);
        RoleResource one = roleResourceService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增角色-资源关联关系")
    @ApiOperation(value = "新增角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody RoleResource roleResource) {
        Boolean code = roleResourceService.save(roleResource);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增角色-资源关联关系")
    @ApiOperation(value = "批量新增角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系列表", name = "roleResourceList", dataType = "List<RoleResource>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<RoleResource> roleResourceList) {
        Boolean code = roleResourceService.saveBatch(roleResourceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改角色-资源关联关系")
    @ApiOperation(value = "根据id修改角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody RoleResource roleResource) {
        Boolean code = roleResourceService.updateById(roleResource);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改角色-资源关联关系")
    @ApiOperation(value = "根据ids批量修改角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系列表", name = "roleResourceList", dataType = "List<RoleResource>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<RoleResource> roleResourceList) {
        Boolean code = roleResourceService.updateBatchById(roleResourceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除角色-资源关联关系")
    @ApiOperation(value = "根据id删除角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = roleResourceService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除角色-资源关联关系")
    @ApiOperation(value = "根据ids批量删除角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = roleResourceService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除角色-资源关联关系")
    @ApiOperation(value = "根据条件批量删除角色-资源关联关系")
    @ApiImplicitParam(value = "角色-资源关联关系Model", name = "roleResource", dataType = "RoleResource", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody RoleResource roleResource) {
        QueryWrapper<RoleResource> wrapper = WrapperUtil.createQueryWrapper(roleResource);
        Boolean code = roleResourceService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "查询 [资源内容-角色名称] 对应关系列表")
    @ApiOperation(value = "查询 [资源内容-角色名称] 对应关系列表")
    @GetMapping(value = "/resource-role-bo/list")
    public ResponseEntity<List<ResourceRoleBO>> resourceRoleBOList() {
        List<ResourceRoleBO> resourceRoleBOList = roleResourceService.resourceRoleBOList(null);
        return ResponseEntity.ok(resourceRoleBOList);
    }

    @ApiLogPrint(value = "根据资源ids查询 [资源内容-角色名称] 对应关系列表")
    @ApiOperation(value = "根据资源ids查询 [资源内容-角色名称] 对应关系列表")
    @ApiImplicitParam(value = "资源ids", name = "resourceIds", dataType = "List<Long>", paramType = "body", required = true)
    @PostMapping(value = "/resource-role-bo/list")
    public ResponseEntity<List<ResourceRoleBO>> resourceRoleBOList(@RequestBody List<Long> resourceIds) {
        List<ResourceRoleBO> resourceRoleBOList = roleResourceService.resourceRoleBOList(resourceIds);
        return ResponseEntity.ok(resourceRoleBOList);
    }

    @ApiLogPrint(value = "初始化 [资源内容-角色名称] 对应关系列表到 Redis 缓存中")
    @ApiOperation(value = "初始化 [资源内容-角色名称] 对应关系列表到 Redis 缓存中")
    @GetMapping(value = "/init-cache")
    public ResponseEntity<Void> initResourceRoleCache() {
        roleResourceFacade.initResourceRoleCache();
        return ResponseEntity.ok().build();
    }
}
