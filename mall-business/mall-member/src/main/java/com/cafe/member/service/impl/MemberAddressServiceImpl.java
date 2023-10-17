package com.cafe.member.service.impl;

import com.cafe.member.model.MemberAddress;
import com.cafe.member.mapper.MemberAddressMapper;
import com.cafe.member.service.MemberAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员收货地址业务实现类
 */
@Service
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressService {

    private final MemberAddressMapper memberAddressMapper;

    @Autowired
    public MemberAddressServiceImpl(MemberAddressMapper memberAddressMapper) {
        this.memberAddressMapper = memberAddressMapper;
    }
}
