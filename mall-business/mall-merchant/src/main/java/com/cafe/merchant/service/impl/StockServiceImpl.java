package com.cafe.merchant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.merchant.mapper.StockMapper;
import com.cafe.merchant.model.Stock;
import com.cafe.merchant.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存业务实现类
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    private final StockMapper stockMapper;

    @Autowired
    public StockServiceImpl(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }
}
