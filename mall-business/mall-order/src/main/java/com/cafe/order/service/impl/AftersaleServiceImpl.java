package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.order.mapper.AftersaleMapper;
import com.cafe.order.model.Aftersale;
import com.cafe.order.service.AftersaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后业务实现类
 */
@RequiredArgsConstructor
@Service
public class AftersaleServiceImpl extends ServiceImpl<AftersaleMapper, Aftersale> implements AftersaleService {

    private final AftersaleMapper aftersaleMapper;
}
