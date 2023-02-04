package com.cafe.member.service.impl;

import com.cafe.member.model.Member;
import com.cafe.member.dao.MemberMapper;
import com.cafe.member.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员业务实现类
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }
}
