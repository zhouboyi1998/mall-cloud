package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.goods.bo.Goods;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:35
 * @Description:
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 根据 SKU ids 查询商品列表
     *
     * @param ids
     * @return
     */
    List<Goods> list(List<Long> ids);

    /**
     * 分页查询商品列表
     *
     * @param page
     * @return
     */
    Page<Goods> page(Page<Goods> page);
}
