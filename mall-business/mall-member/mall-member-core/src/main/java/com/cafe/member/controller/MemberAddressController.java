package com.cafe.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.WrapperUtil;
import com.cafe.member.model.MemberAddress;
import com.cafe.member.service.MemberAddressService;
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
 * @Description: 会员收货地址接口
 */
@Api(value = "会员收货地址接口")
@RestController
@RequestMapping(value = "/member-address")
public class MemberAddressController {

    private final MemberAddressService memberAddressService;

    @Autowired
    public MemberAddressController(MemberAddressService memberAddressService) {
        this.memberAddressService = memberAddressService;
    }

    @LogPrint(value = "查询会员收货地址数量")
    @ApiOperation(value = "查询会员收货地址数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = memberAddressService.count();
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "根据条件查询查询会员收货地址数量")
    @ApiOperation(value = "根据条件查询会员收货地址数量")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody MemberAddress memberAddress) {
        QueryWrapper<MemberAddress> wrapper = WrapperUtil.createQueryWrapper(memberAddress);
        Integer count = memberAddressService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "查询会员收货地址列表")
    @ApiOperation(value = "查询会员收货地址列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<MemberAddress>> list() {
        List<MemberAddress> memberAddressList = memberAddressService.list();
        return ResponseEntity.ok(memberAddressList);
    }

    @LogPrint(value = "根据条件查询会员收货地址列表")
    @ApiOperation(value = "根据条件查询会员收货地址列表")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<MemberAddress>> list(@RequestBody MemberAddress memberAddress) {
        QueryWrapper<MemberAddress> wrapper = WrapperUtil.createQueryWrapper(memberAddress);
        List<MemberAddress> memberAddressList = memberAddressService.list(wrapper);
        return ResponseEntity.ok(memberAddressList);
    }

    @LogPrint(value = "分页查询会员收货地址列表")
    @ApiOperation(value = "分页查询会员收货地址列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<MemberAddress>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<MemberAddress> page = new Page<MemberAddress>().setCurrent(current).setSize(size);
        Page<MemberAddress> memberAddressPage = memberAddressService.page(page);
        return ResponseEntity.ok(memberAddressPage);
    }

    @LogPrint(value = "根据条件分页查询会员收货地址")
    @ApiOperation(value = "根据条件分页查询会员收货地址")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<MemberAddress>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody MemberAddress memberAddress
    ) {
        Page<MemberAddress> page = new Page<MemberAddress>().setCurrent(current).setSize(size);
        QueryWrapper<MemberAddress> wrapper = WrapperUtil.createQueryWrapper(memberAddress);
        Page<MemberAddress> memberAddressPage = memberAddressService.page(page, wrapper);
        return ResponseEntity.ok(memberAddressPage);
    }

    @LogPrint(value = "根据id查询单个会员收货地址")
    @ApiOperation(value = "根据id查询单个会员收货地址")
    @ApiImplicitParam(value = "会员收货地址id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<MemberAddress> one(@PathVariable(value = "id") Long id) {
        MemberAddress memberAddress = memberAddressService.getById(id);
        return ResponseEntity.ok(memberAddress);
    }

    @LogPrint(value = "根据条件查询单个会员收货地址")
    @ApiOperation(value = "根据条件查询单个会员收货地址")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<MemberAddress> one(@RequestBody MemberAddress memberAddress) {
        QueryWrapper<MemberAddress> wrapper = WrapperUtil.createQueryWrapper(memberAddress);
        MemberAddress one = memberAddressService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @LogPrint(value = "新增会员收货地址")
    @ApiOperation(value = "新增会员收货地址")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody MemberAddress memberAddress) {
        Boolean code = memberAddressService.save(memberAddress);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增会员收货地址")
    @ApiOperation(value = "批量新增会员收货地址")
    @ApiImplicitParam(value = "会员收货地址列表", name = "memberAddressList", dataType = "List<MemberAddress>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<MemberAddress> memberAddressList) {
        Boolean code = memberAddressService.saveBatch(memberAddressList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改会员收货地址")
    @ApiOperation(value = "根据id修改会员收货地址")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody MemberAddress memberAddress) {
        Boolean code = memberAddressService.updateById(memberAddress);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改会员收货地址")
    @ApiOperation(value = "根据ids批量修改会员收货地址")
    @ApiImplicitParam(value = "会员收货地址列表", name = "memberAddressList", dataType = "List<MemberAddress>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<MemberAddress> memberAddressList) {
        Boolean code = memberAddressService.updateBatchById(memberAddressList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除会员收货地址")
    @ApiOperation(value = "根据id删除会员收货地址")
    @ApiImplicitParam(value = "会员收货地址id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = memberAddressService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除会员收货地址")
    @ApiOperation(value = "根据ids批量删除会员收货地址")
    @ApiImplicitParam(value = "会员收货地址id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = memberAddressService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据条件批量删除会员收货地址")
    @ApiOperation(value = "根据条件批量删除会员收货地址")
    @ApiImplicitParam(value = "会员收货地址Model", name = "memberAddress", dataType = "MemberAddress", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody MemberAddress memberAddress) {
        QueryWrapper<MemberAddress> wrapper = WrapperUtil.createQueryWrapper(memberAddress);
        Boolean code = memberAddressService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
