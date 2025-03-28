package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:39
 * @Description:
 */
public interface GoodsIndexService {

    /**
     * 获取商品索引
     *
     * @param id 商品ID
     * @return 获取响应
     */
    GoodsIndex one(Long id);

    /**
     * 插入商品索引
     *
     * @param goodsIndex 商品索引
     * @return 索引响应
     */
    GoodsIndex insert(GoodsIndex goodsIndex);

    /**
     * 更新商品索引
     *
     * @param goodsIndex 商品索引
     * @return 更新响应
     */
    GoodsIndex update(GoodsIndex goodsIndex);

    /**
     * 删除商品索引
     *
     * @param id 商品ID
     * @return 删除响应
     */
    void delete(Long id);

    /**
     * 批量插入商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 主体响应
     */
    List<GoodsIndex> insertBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量更新商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 主体响应
     */
    List<GoodsIndex> updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品ID列表
     * @return 主体响应
     */
    void deleteBatch(List<Long> ids);

    /**
     * 查询商品索引
     *
     * @param keyword 关键词
     * @return 查询响应
     */
    List<GoodsIndex> list(String keyword);

    /**
     * 分页查询商品索引
     *
     * @param current 页码
     * @param size    每页数据数量
     * @param keyword 关键词
     * @param sort    排序属性
     * @param rule    排序规则
     * @return 查询响应
     */
    Page<GoodsIndex> page(Integer current, Integer size, String keyword, String sort, String rule);
}
