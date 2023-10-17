package com.cafe.order.service.impl;

import com.cafe.order.model.DetailGuarantee;
import com.cafe.order.mapper.DetailGuaranteeMapper;
import com.cafe.order.service.DetailGuaranteeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service.impl
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细-保障关联关系业务实现类
 */
@Service
public class DetailGuaranteeServiceImpl extends ServiceImpl<DetailGuaranteeMapper, DetailGuarantee> implements DetailGuaranteeService {

    private final DetailGuaranteeMapper detailGuaranteeMapper;

    @Autowired
    public DetailGuaranteeServiceImpl(DetailGuaranteeMapper detailGuaranteeMapper) {
        this.detailGuaranteeMapper = detailGuaranteeMapper;
    }
}
