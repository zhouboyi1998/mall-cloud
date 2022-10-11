package com.cafe.goods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cafe.goods.dao.GoodsBOMapper;
import com.cafe.goods.bo.GoodsBO;
import com.cafe.goods.service.GoodsBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service.impl
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:36
 * @Description:
 */
@Service
public class GoodsBOServiceImpl extends ServiceImpl<GoodsBOMapper, GoodsBO> implements GoodsBOService {

    private GoodsBOMapper goodsBOMapper;

    @Autowired
    public GoodsBOServiceImpl(GoodsBOMapper goodsBOMapper) {
        this.goodsBOMapper = goodsBOMapper;
    }

    @Override
    public List<GoodsBO> list(List<Long> ids) {
        return goodsBOMapper.list(ids);
    }

    @Override
    public Page<GoodsBO> page(Page<GoodsBO> page) {
        return goodsBOMapper.page(page);
    }
}
