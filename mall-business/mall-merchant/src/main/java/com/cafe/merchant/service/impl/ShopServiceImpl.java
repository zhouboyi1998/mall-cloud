package com.cafe.merchant.service.impl;

import com.cafe.merchant.model.Shop;
import com.cafe.merchant.mapper.ShopMapper;
import com.cafe.merchant.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺业务实现类
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    private final ShopMapper shopMapper;

    @Autowired
    public ShopServiceImpl(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }
}
