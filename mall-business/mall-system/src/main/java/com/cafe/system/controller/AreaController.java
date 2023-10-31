package com.cafe.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.system.dto.AreaDTO;
import com.cafe.system.model.Area;
import com.cafe.system.service.AreaService;
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

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 区域接口
 */
@Api(value = "区域接口")
@RestController
@RequestMapping(value = "/area")
public class AreaController {

    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @LogPrint(value = "查询区域数量")
    @ApiOperation(value = "查询区域数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = areaService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询区域数量")
    @ApiOperation(value = "根据条件查询区域数量")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Area area) {
        QueryWrapper<Area> wrapper = WrapperUtil.createQueryWrapper(area);
        Integer count = areaService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询区域列表")
    @ApiOperation(value = "查询区域列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Area>> list() {
        List<Area> areaList = areaService.list();
        return ResponseEntity.ok(areaList);
    }

    @LogPrint(value = "根据条件查询区域列表")
    @ApiOperation(value = "根据条件查询区域列表")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Area>> list(@RequestBody Area area) {
        QueryWrapper<Area> wrapper = WrapperUtil.createQueryWrapper(area);
        List<Area> areaList = areaService.list(wrapper);
        return ResponseEntity.ok(areaList);
    }

    @LogPrint(value = "分页查询区域列表")
    @ApiOperation(value = "分页查询区域列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Area>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Area> page = new Page<Area>().setCurrent(current).setSize(size);
        Page<Area> areaPage = areaService.page(page);
        return ResponseEntity.ok(areaPage);
    }

    @LogPrint(value = "根据条件分页查询区域")
    @ApiOperation(value = "根据条件分页查询区域")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Area>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Area area
    ) {
        Page<Area> page = new Page<Area>().setCurrent(current).setSize(size);
        QueryWrapper<Area> wrapper = WrapperUtil.createQueryWrapper(area);
        Page<Area> areaPage = areaService.page(page, wrapper);
        return ResponseEntity.ok(areaPage);
    }

    @LogPrint(value = "根据id查询单个区域")
    @ApiOperation(value = "根据id查询单个区域")
    @ApiImplicitParam(value = "区域id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Area> one(@PathVariable(value = "id") Long id) {
        Area area = areaService.getById(id);
        return ResponseEntity.ok(area);
    }

    @LogPrint(value = "根据条件查询单个区域")
    @ApiOperation(value = "根据条件查询单个区域")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Area> one(@RequestBody Area area) {
        QueryWrapper<Area> wrapper = WrapperUtil.createQueryWrapper(area);
        Area one = areaService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增区域")
    @ApiOperation(value = "新增区域")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Area area) {
        Boolean code = areaService.save(area);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增区域")
    @ApiOperation(value = "批量新增区域")
    @ApiImplicitParam(value = "区域列表", name = "areaList", dataType = "List<Area>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Area> areaList) {
        Boolean code = areaService.saveBatch(areaList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改区域")
    @ApiOperation(value = "根据id修改区域")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Area area) {
        Boolean code = areaService.updateById(area);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改区域")
    @ApiOperation(value = "根据ids批量修改区域")
    @ApiImplicitParam(value = "区域列表", name = "areaList", dataType = "List<Area>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Area> areaList) {
        Boolean code = areaService.updateBatchById(areaList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除区域")
    @ApiOperation(value = "根据id删除区域")
    @ApiImplicitParam(value = "区域id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = areaService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除区域")
    @ApiOperation(value = "根据ids批量删除区域")
    @ApiImplicitParam(value = "区域id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = areaService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除区域")
    @ApiOperation(value = "根据条件批量删除区域")
    @ApiImplicitParam(value = "区域Model", name = "area", dataType = "Area", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Area area) {
        QueryWrapper<Area> wrapper = WrapperUtil.createQueryWrapper(area);
        Boolean code = areaService.remove(wrapper);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据省份id、城市id、区县id获取区域")
    @ApiOperation(value = "根据省份id、城市id、区县id获取区域")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "省份id", name = "provinceId", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "城市id", name = "cityId", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "区县id", name = "districtId", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/dto/{provinceId}/{cityId}/{districtId}")
    public ResponseEntity<AreaDTO> dto(
        @PathVariable(value = "provinceId") Long provinceId,
        @PathVariable(value = "cityId") Long cityId,
        @PathVariable(value = "districtId") Long districtId
    ) {
        AreaDTO dto = areaService.dto(provinceId, cityId, districtId);
        return ResponseEntity.ok(dto);
    }
}
