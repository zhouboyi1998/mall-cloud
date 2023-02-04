package com.cafe.merchant.service.impl;

import com.cafe.merchant.model.MerchantShop;
import com.cafe.merchant.dao.MerchantShopMapper;
import com.cafe.merchant.service.MerchantShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家-店铺关联关系业务实现类
 */
@Service
public class MerchantShopServiceImpl extends ServiceImpl<MerchantShopMapper, MerchantShop> implements MerchantShopService {

    private final MerchantShopMapper merchantShopMapper;

    @Autowired
    public MerchantShopServiceImpl(MerchantShopMapper merchantShopMapper) {
        this.merchantShopMapper = merchantShopMapper;
    }
}
