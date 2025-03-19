package com.cafe.clickhouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.clickhouse.mapper.GoodsWideMapper;
import com.cafe.clickhouse.model.entity.GoodsWide;
import com.cafe.clickhouse.service.GoodsWideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.clickhouse.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/15 17:10
 * @Description: 商品宽表业务实现类
 */
@RequiredArgsConstructor
@Service
public class GoodsWideServiceImpl extends ServiceImpl<GoodsWideMapper, GoodsWide> implements GoodsWideService {

    private final GoodsWideMapper goodsWideMapper;
}
