package com.cafe.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.member.model.Address;
import com.cafe.member.service.AddressService;
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
 * @Package: com.cafe.member.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 收货地址接口
 */
@Api(value = "收货地址接口")
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @LogPrint(value = "查询收货地址数量")
    @ApiOperation(value = "查询收货地址数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = addressService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询收货地址数量")
    @ApiOperation(value = "根据条件查询收货地址数量")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Address address) {
        QueryWrapper<Address> wrapper = WrapperUtil.createQueryWrapper(address);
        Integer count = addressService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询收货地址列表")
    @ApiOperation(value = "查询收货地址列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Address>> list() {
        List<Address> addressList = addressService.list();
        return ResponseEntity.ok(addressList);
    }

    @LogPrint(value = "根据条件查询收货地址列表")
    @ApiOperation(value = "根据条件查询收货地址列表")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Address>> list(@RequestBody Address address) {
        QueryWrapper<Address> wrapper = WrapperUtil.createQueryWrapper(address);
        List<Address> addressList = addressService.list(wrapper);
        return ResponseEntity.ok(addressList);
    }

    @LogPrint(value = "分页查询收货地址列表")
    @ApiOperation(value = "分页查询收货地址列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Address>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Address> page = new Page<Address>().setCurrent(current).setSize(size);
        Page<Address> addressPage = addressService.page(page);
        return ResponseEntity.ok(addressPage);
    }

    @LogPrint(value = "根据条件分页查询收货地址")
    @ApiOperation(value = "根据条件分页查询收货地址")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Address>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Address address
    ) {
        Page<Address> page = new Page<Address>().setCurrent(current).setSize(size);
        QueryWrapper<Address> wrapper = WrapperUtil.createQueryWrapper(address);
        Page<Address> addressPage = addressService.page(page, wrapper);
        return ResponseEntity.ok(addressPage);
    }

    @LogPrint(value = "根据id查询单个收货地址")
    @ApiOperation(value = "根据id查询单个收货地址")
    @ApiImplicitParam(value = "收货地址id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Address> one(@PathVariable(value = "id") Long id) {
        Address address = addressService.getById(id);
        return ResponseEntity.ok(address);
    }

    @LogPrint(value = "根据条件查询单个收货地址")
    @ApiOperation(value = "根据条件查询单个收货地址")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Address> one(@RequestBody Address address) {
        QueryWrapper<Address> wrapper = WrapperUtil.createQueryWrapper(address);
        Address one = addressService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增收货地址")
    @ApiOperation(value = "新增收货地址")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Address address) {
        Boolean code = addressService.save(address);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增收货地址")
    @ApiOperation(value = "批量新增收货地址")
    @ApiImplicitParam(value = "收货地址列表", name = "addressList", dataType = "List<Address>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Address> addressList) {
        Boolean code = addressService.saveBatch(addressList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改收货地址")
    @ApiOperation(value = "根据id修改收货地址")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Address address) {
        Boolean code = addressService.updateById(address);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改收货地址")
    @ApiOperation(value = "根据ids批量修改收货地址")
    @ApiImplicitParam(value = "收货地址列表", name = "addressList", dataType = "List<Address>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Address> addressList) {
        Boolean code = addressService.updateBatchById(addressList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除收货地址")
    @ApiOperation(value = "根据id删除收货地址")
    @ApiImplicitParam(value = "收货地址id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = addressService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除收货地址")
    @ApiOperation(value = "根据ids批量删除收货地址")
    @ApiImplicitParam(value = "收货地址id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = addressService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除收货地址")
    @ApiOperation(value = "根据条件批量删除收货地址")
    @ApiImplicitParam(value = "收货地址Model", name = "address", dataType = "Address", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Address address) {
        QueryWrapper<Address> wrapper = WrapperUtil.createQueryWrapper(address);
        Boolean code = addressService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
