package com.cafe.order.service.impl;

import com.cafe.order.model.AfterSale;
import com.cafe.order.dao.AfterSaleMapper;
import com.cafe.order.service.AfterSaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后业务实现类
 */
@Service
public class AfterSaleServiceImpl extends ServiceImpl<AfterSaleMapper, AfterSale> implements AfterSaleService {

    private final AfterSaleMapper afterSaleMapper;

    @Autowired
    public AfterSaleServiceImpl(AfterSaleMapper afterSaleMapper) {
        this.afterSaleMapper = afterSaleMapper;
    }
}
