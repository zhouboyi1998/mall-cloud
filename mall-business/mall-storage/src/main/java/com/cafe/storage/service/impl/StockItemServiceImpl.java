package com.cafe.storage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.storage.mapper.StockItemMapper;
import com.cafe.storage.model.entity.StockItem;
import com.cafe.storage.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service.impl
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 库存明细业务实现类
 */
@RequiredArgsConstructor
@Service
public class StockItemServiceImpl extends ServiceImpl<StockItemMapper, StockItem> implements StockItemService {

    private final StockItemMapper stockItemMapper;
}
