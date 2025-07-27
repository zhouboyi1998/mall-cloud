package com.cafe.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.goods.facade.SpuFacade;
import com.cafe.goods.model.entity.Spu;
import com.cafe.goods.model.vo.SpuVO;
import com.cafe.goods.service.SpuService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.controller
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元接口
 */
@Api(value = "标准化产品单元接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/spu")
public class SpuController {

    private final SpuService spuService;

    private final SpuFacade spuFacade;

    @ApiLogPrint(value = "查询标准化产品单元数量")
    @ApiOperation(value = "查询标准化产品单元数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = spuService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询标准化产品单元数量")
    @ApiOperation(value = "根据条件查询标准化产品单元数量")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Spu spu) {
        QueryWrapper<Spu> wrapper = WrapperUtil.createQueryWrapper(spu);
        Integer count = spuService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询标准化产品单元列表")
    @ApiOperation(value = "查询标准化产品单元列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Spu>> list() {
        List<Spu> spuList = spuService.list();
        return ResponseEntity.ok(spuList);
    }

    @ApiLogPrint(value = "根据条件查询标准化产品单元列表")
    @ApiOperation(value = "根据条件查询标准化产品单元列表")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Spu>> list(@RequestBody Spu spu) {
        QueryWrapper<Spu> wrapper = WrapperUtil.createQueryWrapper(spu);
        List<Spu> spuList = spuService.list(wrapper);
        return ResponseEntity.ok(spuList);
    }

    @ApiLogPrint(value = "分页查询标准化产品单元列表")
    @ApiOperation(value = "分页查询标准化产品单元列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Spu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Spu> page = new Page<>(current, size);
        Page<Spu> spuPage = spuService.page(page);
        return ResponseEntity.ok(spuPage);
    }

    @ApiLogPrint(value = "根据条件分页查询标准化产品单元")
    @ApiOperation(value = "根据条件分页查询标准化产品单元")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Spu>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Spu spu
    ) {
        Page<Spu> page = new Page<>(current, size);
        QueryWrapper<Spu> wrapper = WrapperUtil.createQueryWrapper(spu);
        Page<Spu> spuPage = spuService.page(page, wrapper);
        return ResponseEntity.ok(spuPage);
    }

    @ApiLogPrint(value = "根据id查询单个标准化产品单元")
    @ApiOperation(value = "根据id查询单个标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Spu> one(@PathVariable(value = "id") Long id) {
        Spu spu = spuService.getById(id);
        return ResponseEntity.ok(spu);
    }

    @ApiLogPrint(value = "根据条件查询单个标准化产品单元")
    @ApiOperation(value = "根据条件查询单个标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Spu> one(@RequestBody Spu spu) {
        QueryWrapper<Spu> wrapper = WrapperUtil.createQueryWrapper(spu);
        Spu one = spuService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增标准化产品单元")
    @ApiOperation(value = "新增标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody @Valid Spu spu) {
        Boolean code = spuService.save(spu);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增标准化产品单元")
    @ApiOperation(value = "批量新增标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元列表", name = "spuList", dataType = "List<Spu>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Spu> spuList) {
        Boolean code = spuService.saveBatch(spuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改标准化产品单元")
    @ApiOperation(value = "根据id修改标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody @Valid Spu spu) {
        Boolean code = spuService.updateById(spu);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改标准化产品单元")
    @ApiOperation(value = "根据ids批量修改标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元列表", name = "spuList", dataType = "List<Spu>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Spu> spuList) {
        Boolean code = spuService.updateBatchById(spuList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除标准化产品单元")
    @ApiOperation(value = "根据id删除标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = spuService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除标准化产品单元")
    @ApiOperation(value = "根据ids批量删除标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = spuService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除标准化产品单元")
    @ApiOperation(value = "根据条件批量删除标准化产品单元")
    @ApiImplicitParam(value = "标准化产品单元Model", name = "spu", dataType = "Spu", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Spu spu) {
        QueryWrapper<Spu> wrapper = WrapperUtil.createQueryWrapper(spu);
        Boolean code = spuService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据 skuId 查询 SPU 视图模型")
    @ApiOperation(value = "根据 skuId 查询 SPU 视图模型")
    @ApiImplicitParam(value = "SKU id", name = "skuId", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/vo/{skuId}")
    public ResponseEntity<SpuVO> vo(@PathVariable(value = "skuId") Long skuId) {
        SpuVO spuVO = spuFacade.vo(skuId);
        return ResponseEntity.ok(spuVO);
    }
}
