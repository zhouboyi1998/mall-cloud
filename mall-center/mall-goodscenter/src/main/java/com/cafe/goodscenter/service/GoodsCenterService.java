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
     * @param current   页码
     * @param size      每页数据数量
     * @param sortField 排序字段
     * @param sortRule  排序规则
     * @param keyword   关键词
     * @return 商品摘要列表
     */
    List<GoodsSummary> summary(Integer current, Integer size, String sortField, String sortRule, String keyword);

    /**
     * 获取商品详情
     *
     * @param skuId 库存量单位ID列表
     * @return 商品详情
     */
    SpuDetail detail(Long skuId);
}
