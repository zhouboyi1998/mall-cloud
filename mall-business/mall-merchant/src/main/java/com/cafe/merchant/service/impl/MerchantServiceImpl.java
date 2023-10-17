package com.cafe.merchant.service.impl;

import com.cafe.merchant.model.Merchant;
import com.cafe.merchant.mapper.MerchantMapper;
import com.cafe.merchant.service.MerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家业务实现类
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    private final MerchantMapper merchantMapper;

    @Autowired
    public MerchantServiceImpl(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
    }
}
