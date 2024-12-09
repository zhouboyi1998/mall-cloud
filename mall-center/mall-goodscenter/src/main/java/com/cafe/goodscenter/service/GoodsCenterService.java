package com.cafe.goodscenter.service;

import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.goodscenter.model.vo.SpuDetail;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.service
 * @Author: zhouboyi
 * @Date: 2024/7/31 23:18
 * @Description: 商品中心业务接口
 */
public interface GoodsCenterService {

    /**
     * 搜索商品
     *
     * @param keyword 关键词
     * @return
     */
    List<GoodsSummary> summary(String keyword);

    /**
     * 获取商品详情
     *
     * @param skuId
     * @return
     */
    SpuDetail detail(Long skuId);
}
