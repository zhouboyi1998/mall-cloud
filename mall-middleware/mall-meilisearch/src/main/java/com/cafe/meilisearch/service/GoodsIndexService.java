package com.cafe.meilisearch.service;

import com.cafe.meilisearch.model.index.GoodsIndex;
import com.meilisearch.sdk.model.Searchable;
import com.meilisearch.sdk.model.TaskInfo;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service
 * @Author: zhouboyi
 * @Date: 2025/6/5 22:16
 * @Description:
 */
public interface GoodsIndexService {

    /**
     * 初始化商品索引
     *
     * @return 任务信息
     */
    List<TaskInfo> init();

    /**
     * 获取商品索引
     *
     * @param id 商品id
     * @return 商品索引
     */
    GoodsIndex one(Long id);

    /**
     * 保存商品索引
     *
     * @param goodsIndex 商品索引
     * @return 任务信息
     */
    TaskInfo save(GoodsIndex goodsIndex);

    /**
     * 批量保存商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 任务信息列表
     */
    List<TaskInfo> saveBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 修改商品索引
     *
     * @param goodsIndex 商品索引
     * @return 任务信息
     */
    TaskInfo update(GoodsIndex goodsIndex);

    /**
     * 批量修改商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 任务信息列表
     */
    List<TaskInfo> updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 删除商品索引
     *
     * @param id 商品id
     * @return 任务信息
     */
    TaskInfo delete(Long id);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品id列表
     * @return 任务信息列表
     */
    List<TaskInfo> deleteBatch(List<Long> ids);

    /**
     * 搜索商品索引
     *
     * @param current   页码
     * @param size      每页数据数量
     * @param sortField 排序字段
     * @param sortRule  排序规则
     * @param keyword   关键词
     * @return 搜索结果
     */
    Searchable search(Integer current, Integer size, String sortField, String sortRule, String keyword);
}
