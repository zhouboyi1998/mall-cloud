package com.cafe.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.common.mybatisplus.util.WrapperUtil;
import com.cafe.member.model.entity.Member;
import com.cafe.member.service.MemberService;
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
 * @Package: com.cafe.member.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员接口
 */
@Api(value = "会员接口")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @ApiLogPrint(value = "查询会员数量")
    @ApiOperation(value = "查询会员数量")
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> count() {
        Integer count = memberService.count();
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "根据条件查询查询会员数量")
    @ApiOperation(value = "根据条件查询会员数量")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/count")
    public ResponseEntity<Integer> count(@RequestBody Member member) {
        QueryWrapper<Member> wrapper = WrapperUtil.createQueryWrapper(member);
        Integer count = memberService.count(wrapper);
        return ResponseEntity.ok(count);
    }

    @ApiLogPrint(value = "查询会员列表")
    @ApiOperation(value = "查询会员列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Member>> list() {
        List<Member> memberList = memberService.list();
        return ResponseEntity.ok(memberList);
    }

    @ApiLogPrint(value = "根据条件查询会员列表")
    @ApiOperation(value = "根据条件查询会员列表")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Member>> list(@RequestBody Member member) {
        QueryWrapper<Member> wrapper = WrapperUtil.createQueryWrapper(member);
        List<Member> memberList = memberService.list(wrapper);
        return ResponseEntity.ok(memberList);
    }

    @ApiLogPrint(value = "分页查询会员列表")
    @ApiOperation(value = "分页查询会员列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true)
    })
    @GetMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Member>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    ) {
        Page<Member> page = new Page<>(current, size);
        Page<Member> memberPage = memberService.page(page);
        return ResponseEntity.ok(memberPage);
    }

    @ApiLogPrint(value = "根据条件分页查询会员")
    @ApiOperation(value = "根据条件分页查询会员")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "页码", name = "current", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "每页显示数量", name = "size", dataType = "Long", paramType = "path", required = true),
        @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    })
    @PostMapping(value = "/page/{current}/{size}")
    public ResponseEntity<Page<Member>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size,
        @RequestBody Member member
    ) {
        Page<Member> page = new Page<>(current, size);
        QueryWrapper<Member> wrapper = WrapperUtil.createQueryWrapper(member);
        Page<Member> memberPage = memberService.page(page, wrapper);
        return ResponseEntity.ok(memberPage);
    }

    @ApiLogPrint(value = "根据id查询单个会员")
    @ApiOperation(value = "根据id查询单个会员")
    @ApiImplicitParam(value = "会员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Member> one(@PathVariable(value = "id") Long id) {
        Member member = memberService.getById(id);
        return ResponseEntity.ok(member);
    }

    @ApiLogPrint(value = "根据条件查询单个会员")
    @ApiOperation(value = "根据条件查询单个会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/one")
    public ResponseEntity<Member> one(@RequestBody Member member) {
        QueryWrapper<Member> wrapper = WrapperUtil.createQueryWrapper(member);
        Member one = memberService.getOne(wrapper);
        return ResponseEntity.ok(one);
    }

    @ApiLogPrint(value = "新增会员")
    @ApiOperation(value = "新增会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Member member) {
        Boolean code = memberService.save(member);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "批量新增会员")
    @ApiOperation(value = "批量新增会员")
    @ApiImplicitParam(value = "会员列表", name = "memberList", dataType = "List<Member>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Member> memberList) {
        Boolean code = memberService.saveBatch(memberList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id修改会员")
    @ApiOperation(value = "根据id修改会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Member member) {
        Boolean code = memberService.updateById(member);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量修改会员")
    @ApiOperation(value = "根据ids批量修改会员")
    @ApiImplicitParam(value = "会员列表", name = "memberList", dataType = "List<Member>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Member> memberList) {
        Boolean code = memberService.updateBatchById(memberList);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据id删除会员")
    @ApiOperation(value = "根据id删除会员")
    @ApiImplicitParam(value = "会员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = memberService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据ids批量删除会员")
    @ApiOperation(value = "根据ids批量删除会员")
    @ApiImplicitParam(value = "会员id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = memberService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }

    @ApiLogPrint(value = "根据条件批量删除会员")
    @ApiOperation(value = "根据条件批量删除会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Member member) {
        QueryWrapper<Member> wrapper = WrapperUtil.createQueryWrapper(member);
        Boolean code = memberService.remove(wrapper);
        return ResponseEntity.ok(code);
    }
}
