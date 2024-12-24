package com.cafe.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.core.jwt.Payload;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
import com.cafe.user.model.entity.Resource;
import com.cafe.user.service.ResourceService;
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
 * @Description: 资源接口
 */
@Api(value = "资源接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @ApiLogPrint(value = "查询资源数量")
    @ApiOperation(value = "查询资源数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = resourceService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询资源数量")
    @ApiOperation(value = "根据条件查询资源数量")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Resource resource) {
        QueryWrapper<Resource> wrapper = WrapperUtil.createQueryWrapper(resource);
        Integer count = resourceService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询资源列表")
    @ApiOperation(value = "查询资源列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Resource>> list() {
        List<Resource> resourceList = resourceService.list();
        return ResponseEntity.ok(resourceList);
    }

    @ApiLogPrint(value = "根据条件查询资源列表")
    @ApiOperation(value = "根据条件查询资源列表")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Resource>> list(@RequestBody Resource resource) {
        QueryWrapper<Resource> wrapper = WrapperUtil.createQueryWrapper(resource);
        List<Resource> resourceList = resourceService.list(wrapper);
        return ResponseEntity.ok(resourceList);
    }

    @ApiLogPrint(value = "分页查询资源列表")
    @ApiOperation(value = "分页查询资源列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Resource>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Resource> page = new Page<>(current, size);
        Page<Resource> resourcePage = resourceService.page(page);
        return ResponseEntity.ok(resourcePage);
    }

    @ApiLogPrint(value = "根据条件分页查询资源")
    @ApiOperation(value = "根据条件分页查询资源")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Resource>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Resource resource
    ) {
        Page<Resource> page = new Page<>(current, size);
        QueryWrapper<Resource> wrapper = WrapperUtil.createQueryWrapper(resource);
        Page<Resource> resourcePage = resourceService.page(page, wrapper);
        return ResponseEntity.ok(resourcePage);
    }

    @ApiLogPrint(value = "根据id查询单个资源")
    @ApiOperation(value = "根据id查询单个资源")
    @ApiImplicitParam(value = "资源id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Resource> one(@PathVariable(value = "id") Long id) {
        Resource resource = resourceService.getById(id);
        return ResponseEntity.ok(resource);
    }

    @ApiLogPrint(value = "根据条件查询单个资源")
    @ApiOperation(value = "根据条件查询单个资源")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Resource> one(@RequestBody Resource resource) {
        QueryWrapper<Resource> wrapper = WrapperUtil.createQueryWrapper(resource);
        Resource one = resourceService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增资源")
    @ApiOperation(value = "新增资源")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Resource resource) {
        Boolean code = resourceService.save(resource);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增资源")
    @ApiOperation(value = "批量新增资源")
    @ApiImplicitParam(value = "资源列表", name = "resourceList", dataType = "List<Resource>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Resource> resourceList) {
        Boolean code = resourceService.saveBatch(resourceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改资源")
    @ApiOperation(value = "根据id修改资源")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Resource resource) {
        Boolean code = resourceService.updateById(resource);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改资源")
    @ApiOperation(value = "根据ids批量修改资源")
    @ApiImplicitParam(value = "资源列表", name = "resourceList", dataType = "List<Resource>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Resource> resourceList) {
        Boolean code = resourceService.updateBatchById(resourceList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除资源")
    @ApiOperation(value = "根据id删除资源")
    @ApiImplicitParam(value = "资源id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = resourceService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除资源")
    @ApiOperation(value = "根据ids批量删除资源")
    @ApiImplicitParam(value = "资源id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = resourceService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除资源")
    @ApiOperation(value = "根据条件批量删除资源")
    @ApiImplicitParam(value = "资源Model", name = "resource", dataType = "Resource", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Resource resource) {
        QueryWrapper<Resource> wrapper = WrapperUtil.createQueryWrapper(resource);
        Boolean code = resourceService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据权限列表查询菜单树列表")
    @ApiOperation(value = "根据权限列表查询菜单树列表")
    @GetMapping(value = "/menu-tree-list")
    public ResponseEntity<List<Tree>> menuTreeList(
        @ModelAttribute(value = RequestConstant.ModelAttribute.PAYLOAD) Payload payload
    ) {
        List<Tree> menuTreeList = resourceService.menuTreeList(payload.getAuthorities());
        return ResponseEntity.ok(menuTreeList);
    }
}
