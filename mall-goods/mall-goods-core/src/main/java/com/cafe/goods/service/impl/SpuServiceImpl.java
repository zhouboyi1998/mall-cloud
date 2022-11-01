package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.SpuMapper;
import com.cafe.goods.model.Spu;
import com.cafe.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元业务实现类
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    private SpuMapper spuMapper;

    @Autowired
    public SpuServiceImpl(SpuMapper spuMapper) {
        this.spuMapper = spuMapper;
    }
}
