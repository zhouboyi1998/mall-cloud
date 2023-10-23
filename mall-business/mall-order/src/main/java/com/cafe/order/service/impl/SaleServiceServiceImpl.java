package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.order.mapper.SaleServiceMapper;
import com.cafe.order.model.SaleService;
import com.cafe.order.service.SaleServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后服务业务实现类
 */
@Service
public class SaleServiceServiceImpl extends ServiceImpl<SaleServiceMapper, SaleService> implements SaleServiceService {

    private final SaleServiceMapper saleServiceMapper;

    @Autowired
    public SaleServiceServiceImpl(SaleServiceMapper saleServiceMapper) {
        this.saleServiceMapper = saleServiceMapper;
    }
}
