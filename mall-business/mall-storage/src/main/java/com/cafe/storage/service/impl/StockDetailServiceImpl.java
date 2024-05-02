package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StockDetailMapper;
import com.cafe.storage.model.StockDetail;
import com.cafe.storage.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service.impl
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 库存明细业务实现类
 */
@Service
public class StockDetailServiceImpl extends ServiceImpl<StockDetailMapper, StockDetail> implements StockDetailService {

    private final StockDetailMapper stockDetailMapper;

    @Autowired
    public StockDetailServiceImpl(StockDetailMapper stockDetailMapper) {
        this.stockDetailMapper = stockDetailMapper;
    }
}
