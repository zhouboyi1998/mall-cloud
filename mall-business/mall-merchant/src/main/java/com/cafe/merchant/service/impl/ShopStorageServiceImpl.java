package com.cafe.merchant.service.impl;

import com.cafe.merchant.model.ShopStorage;
import com.cafe.merchant.mapper.ShopStorageMapper;
import com.cafe.merchant.service.ShopStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺-仓库关联关系业务实现类
 */
@Service
public class ShopStorageServiceImpl extends ServiceImpl<ShopStorageMapper, ShopStorage> implements ShopStorageService {

    private final ShopStorageMapper shopStorageMapper;

    @Autowired
    public ShopStorageServiceImpl(ShopStorageMapper shopStorageMapper) {
        this.shopStorageMapper = shopStorageMapper;
    }
}
