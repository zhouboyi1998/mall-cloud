package com.cafe.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.member.mapper.AddressMapper;
import com.cafe.member.model.Address;
import com.cafe.member.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 收货地址业务实现类
 */
@RequiredArgsConstructor
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    private final AddressMapper addressMapper;
}
