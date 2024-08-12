package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StockItemMapper;
import com.cafe.storage.model.StockItem;
import com.cafe.storage.service.StockItemService;
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
public class StockItemServiceImpl extends ServiceImpl<StockItemMapper, StockItem> implements StockItemService {

    private final StockItemMapper stockItemMapper;

    @Autowired
    public StockItemServiceImpl(StockItemMapper stockItemMapper) {
        this.stockItemMapper = stockItemMapper;
    }
}
