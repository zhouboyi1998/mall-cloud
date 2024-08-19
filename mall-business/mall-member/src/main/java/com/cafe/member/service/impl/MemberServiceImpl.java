package com.cafe.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.member.mapper.MemberMapper;
import com.cafe.member.model.Member;
import com.cafe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员业务实现类
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final MemberMapper memberMapper;
}
