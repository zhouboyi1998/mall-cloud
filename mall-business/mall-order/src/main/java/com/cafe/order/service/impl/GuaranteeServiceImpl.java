package com.cafe.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.order.mapper.GuaranteeMapper;
import com.cafe.order.model.entity.Guarantee;
import com.cafe.order.service.GuaranteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 保障业务实现类
 */
@RequiredArgsConstructor
@Service
public class GuaranteeServiceImpl extends ServiceImpl<GuaranteeMapper, Guarantee> implements GuaranteeService {

    private final GuaranteeMapper guaranteeMapper;
}
