package com.cafe.goods.service;

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
     * 批量上下架商品
     *
     * @param ids
     * @param status
     */
    void launch(List<Long> ids, Integer status);
}
