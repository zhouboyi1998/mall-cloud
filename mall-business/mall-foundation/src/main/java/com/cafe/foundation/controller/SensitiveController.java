package com.cafe.foundation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.foundation.facade.SensitiveFacade;
import com.cafe.foundation.model.entity.Sensitive;
import com.cafe.foundation.service.SensitiveService;
import com.cafe.infrastructure.mybatisplus.util.WrapperUtil;
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
 * @Package: com.cafe.foundation.controller
 * @Author: zhouboyi
 * @Date: 2025-07-27
 * @Description: 敏感词接口
 */
@Api(value = "敏感词接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sensitive")
public class SensitiveController {

    private final SensitiveService sensitiveService;

    private final SensitiveFacade sensitiveFacade;

    @ApiLogPrint(value = "查询敏感词数量")
    @ApiOperation(value = "查询敏感词数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = sensitiveService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询敏感词数量")
    @ApiOperation(value = "根据条件查询敏感词数量")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Sensitive sensitive) {
        QueryWrapper<Sensitive> wrapper = WrapperUtil.createQueryWrapper(sensitive);
        Integer count = sensitiveService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询敏感词列表")
    @ApiOperation(value = "查询敏感词列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Sensitive>> list() {
        List<Sensitive> sensitiveList = sensitiveService.list();
        return ResponseEntity.ok(sensitiveList);
    }

    @ApiLogPrint(value = "根据条件查询敏感词列表")
    @ApiOperation(value = "根据条件查询敏感词列表")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Sensitive>> list(@RequestBody Sensitive sensitive) {
        QueryWrapper<Sensitive> wrapper = WrapperUtil.createQueryWrapper(sensitive);
        List<Sensitive> sensitiveList = sensitiveService.list(wrapper);
        return ResponseEntity.ok(sensitiveList);
    }

    @ApiLogPrint(value = "分页查询敏感词列表")
    @ApiOperation(value = "分页查询敏感词列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sensitive>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Sensitive> page = new Page<>(current, size);
        Page<Sensitive> sensitivePage = sensitiveService.page(page);
        return ResponseEntity.ok(sensitivePage);
    }

    @ApiLogPrint(value = "根据条件分页查询敏感词")
    @ApiOperation(value = "根据条件分页查询敏感词")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Sensitive>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Sensitive sensitive
    ) {
        Page<Sensitive> page = new Page<>(current, size);
        QueryWrapper<Sensitive> wrapper = WrapperUtil.createQueryWrapper(sensitive);
        Page<Sensitive> sensitivePage = sensitiveService.page(page, wrapper);
        return ResponseEntity.ok(sensitivePage);
    }

    @ApiLogPrint(value = "根据id查询单个敏感词")
    @ApiOperation(value = "根据id查询单个敏感词")
    @ApiImplicitParam(value = "敏感词id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Sensitive> one(@PathVariable(value = "id") Long id) {
        Sensitive sensitive = sensitiveService.getById(id);
        return ResponseEntity.ok(sensitive);
    }

    @ApiLogPrint(value = "根据条件查询单个敏感词")
    @ApiOperation(value = "根据条件查询单个敏感词")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Sensitive> one(@RequestBody Sensitive sensitive) {
        QueryWrapper<Sensitive> wrapper = WrapperUtil.createQueryWrapper(sensitive);
        Sensitive one = sensitiveService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增敏感词")
    @ApiOperation(value = "新增敏感词")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Sensitive sensitive) {
        Boolean code = sensitiveService.save(sensitive);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增敏感词")
    @ApiOperation(value = "批量新增敏感词")
    @ApiImplicitParam(value = "敏感词列表", name = "sensitiveList", dataType = "List<Sensitive>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Sensitive> sensitiveList) {
        Boolean code = sensitiveService.saveBatch(sensitiveList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改敏感词")
    @ApiOperation(value = "根据id修改敏感词")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Sensitive sensitive) {
        Boolean code = sensitiveService.updateById(sensitive);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改敏感词")
    @ApiOperation(value = "根据ids批量修改敏感词")
    @ApiImplicitParam(value = "敏感词列表", name = "sensitiveList", dataType = "List<Sensitive>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Sensitive> sensitiveList) {
        Boolean code = sensitiveService.updateBatchById(sensitiveList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除敏感词")
    @ApiOperation(value = "根据id删除敏感词")
    @ApiImplicitParam(value = "敏感词id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = sensitiveService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除敏感词")
    @ApiOperation(value = "根据ids批量删除敏感词")
    @ApiImplicitParam(value = "敏感词id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = sensitiveService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除敏感词")
    @ApiOperation(value = "根据条件批量删除敏感词")
    @ApiImplicitParam(value = "敏感词Model", name = "sensitive", dataType = "Sensitive", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Sensitive sensitive) {
        QueryWrapper<Sensitive> wrapper = WrapperUtil.createQueryWrapper(sensitive);
        Boolean code = sensitiveService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "初始化敏感词字典树")
    @ApiOperation(value = "初始化敏感词字典树")
    @GetMapping(value = "/init-sensitive-trie")
    public ResponseEntity<Void> initSensitiveTrie() {
        sensitiveFacade.initSensitiveTrie();
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "添加敏感词到字典树中")
    @ApiOperation(value = "添加敏感词到字典树中")
    @ApiImplicitParam(value = "敏感词Id列表", name = "sensitiveIds", dataType = "List", paramType = "body", required = true)
    @PostMapping(value = "/add-sensitive-words")
    public ResponseEntity<Void> addSensitiveWords(@RequestBody List<Long> sensitiveIds) {
        sensitiveFacade.addSensitiveWords(sensitiveIds);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "匹配文本中的所有敏感词")
    @ApiOperation(value = "匹配文本中的所有敏感词")
    @ApiImplicitParam(value = "被校验的文本", name = "text", dataType = "String", paramType = "body", required = true)
    @PostMapping(value = "/match-text")
    public ResponseEntity<List<String>> matchText(@RequestBody String text) {
        List<String> sensitiveWords = sensitiveFacade.matchText(text);
        return ResponseEntity.ok(sensitiveWords);
    }

    @ApiLogPrint(value = "校验文本中是否包含敏感词")
    @ApiOperation(value = "校验文本中是否包含敏感词")
    @ApiImplicitParam(value = "被校验的文本", name = "text", dataType = "String", paramType = "body", required = true)
    @PostMapping(value = "/validate-text")
    public ResponseEntity<Boolean> validateText(@RequestBody String text) {
        Boolean hasSensitiveWord = sensitiveFacade.validateText(text);
        return ResponseEntity.ok(hasSensitiveWord);
    }
}
