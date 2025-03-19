package com.cafe.merchant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.merchant.mapper.ShopMapper;
import com.cafe.merchant.model.entity.Shop;
import com.cafe.merchant.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺业务实现类
 */
@RequiredArgsConstructor
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    private final ShopMapper shopMapper;
}
