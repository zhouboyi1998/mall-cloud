package com.cafe.goodscenter.service;

import com.cafe.goodscenter.model.vo.GoodsDetail;
import com.cafe.goodscenter.model.vo.GoodsSummary;
import com.cafe.infrastructure.elasticsearch.model.vo.AggregatedPageVO;

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
     * 批量上下架商品
     *
     * @param status 商品状态
     * @param skuIds 库存量单位ID列表
     */
    void shelve(Integer status, List<Long> skuIds);

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
    AggregatedPageVO<GoodsSummary> summary(Integer current, Integer size, String sortField, String sortRule, String keyword);

    /**
     * 获取商品详情
     *
     * @param skuId 库存量单位ID列表
     * @return 商品详情
     */
    GoodsDetail detail(Long skuId);
}
