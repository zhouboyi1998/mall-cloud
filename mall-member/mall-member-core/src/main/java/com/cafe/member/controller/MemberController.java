package com.cafe.member.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.common.mysql.util.MyBatisPlusWrapperUtil;
import com.cafe.member.model.Member;
import com.cafe.member.service.MemberService;
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
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.controller
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员接口
 */
@Api(value = "会员接口")
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @LogPrint(value = "查询会员列表")
    @ApiOperation(value = "查询会员列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Member>> list() {
        List<Member> memberList = memberService.list();
        return ResponseEntity.ok(memberList);
    }

    @LogPrint(value = "根据条件查询会员列表")
    @ApiOperation(value = "根据条件查询会员列表")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/list")
    public ResponseEntity<List<Member>> list(@RequestBody Member member) {
        Wrapper<Member> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(member);
        List<Member> memberList = memberService.list(wrapper);
        return ResponseEntity.ok(memberList);
    }

    @LogPrint(value = "分页查询会员列表")
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
        Page<Member> page = new Page<Member>().setCurrent(current).setSize(size);
        Page<Member> memberPage = memberService.page(page);
        return ResponseEntity.ok(memberPage);
    }

    @LogPrint(value = "根据条件分页查询会员")
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
        Page<Member> page = new Page<Member>().setCurrent(current).setSize(size);
        Wrapper<Member> wrapper = MyBatisPlusWrapperUtil.createQueryWrapperByModel(member);
        Page<Member> memberPage = memberService.page(page, wrapper);
        return ResponseEntity.ok(memberPage);
    }

    @LogPrint(value = "根据id查询单个会员")
    @ApiOperation(value = "根据id查询单个会员")
    @ApiImplicitParam(value = "会员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @GetMapping(value = "/one/{id}")
    public ResponseEntity<Member> one(@PathVariable(value = "id") Long id) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<Member>().eq(Member::getId, id);
        Member member = memberService.getOne(wrapper);
        return ResponseEntity.ok(member);
    }

    @LogPrint(value = "新增会员")
    @ApiOperation(value = "新增会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Member member) {
        LocalDateTime now = LocalDateTime.now();
        member.setCreateTime(now).setUpdateTime(now);
        Boolean code = memberService.save(member);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "批量新增会员")
    @ApiOperation(value = "批量新增会员")
    @ApiImplicitParam(value = "会员列表", name = "memberList", dataType = "List<Member>", paramType = "body", required = true)
    @PostMapping(value = "/insert/batch")
    public ResponseEntity<Boolean> insertBatch(@RequestBody List<Member> memberList) {
        LocalDateTime now = LocalDateTime.now();
        memberList = memberList.stream()
            .map(member -> member.setCreateTime(now).setUpdateTime(now))
            .collect(Collectors.toList());
        Boolean code = memberService.saveBatch(memberList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id修改会员")
    @ApiOperation(value = "根据id修改会员")
    @ApiImplicitParam(value = "会员Model", name = "member", dataType = "Member", paramType = "body", required = true)
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody Member member) {
        Boolean code = memberService.updateById(member);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量修改会员")
    @ApiOperation(value = "根据ids批量修改会员")
    @ApiImplicitParam(value = "会员列表", name = "memberList", dataType = "List<Member>", paramType = "body", required = true)
    @PutMapping(value = "/update/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<Member> memberList) {
        Boolean code = memberService.updateBatchById(memberList);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据id删除会员")
    @ApiOperation(value = "根据id删除会员")
    @ApiImplicitParam(value = "会员id", name = "id", dataType = "Long", paramType = "path", required = true)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Boolean code = memberService.removeById(id);
        return ResponseEntity.ok(code);
    }

    @LogPrint(value = "根据ids批量删除会员")
    @ApiOperation(value = "根据ids批量删除会员")
    @ApiImplicitParam(value = "会员id列表", name = "ids", dataType = "List<Long>", paramType = "body", required = true)
    @DeleteMapping(value = "/delete/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        Boolean code = memberService.removeByIds(ids);
        return ResponseEntity.ok(code);
    }
}
