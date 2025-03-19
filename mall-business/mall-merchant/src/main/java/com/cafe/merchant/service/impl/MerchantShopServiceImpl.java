package com.cafe.merchant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.merchant.mapper.MerchantShopMapper;
import com.cafe.merchant.model.entity.MerchantShop;
import com.cafe.merchant.service.MerchantShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家-店铺关联关系业务实现类
 */
@RequiredArgsConstructor
@Service
public class MerchantShopServiceImpl extends ServiceImpl<MerchantShopMapper, MerchantShop> implements MerchantShopService {

    private final MerchantShopMapper merchantShopMapper;
}
