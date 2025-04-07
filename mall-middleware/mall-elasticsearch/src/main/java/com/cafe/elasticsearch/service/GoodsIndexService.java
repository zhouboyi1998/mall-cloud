package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import org.springframework.data.domain.Page;

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
     * @param id 商品id
     * @return 商品索引
     */
    GoodsIndex one(Long id);

    /**
     * 保存商品索引
     *
     * @param goodsIndex 商品索引
     * @return 保存的商品索引
     */
    GoodsIndex save(GoodsIndex goodsIndex);

    /**
     * 批量保存商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 保存的商品索引列表
     */
    List<GoodsIndex> saveBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 修改商品索引
     *
     * @param goodsIndex 商品索引
     * @return 修改的商品索引
     */
    GoodsIndex update(GoodsIndex goodsIndex);

    /**
     * 批量修改商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 修改的商品索引列表
     */
    List<GoodsIndex> updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 删除商品索引
     *
     * @param id 商品id
     */
    void delete(Long id);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品id列表
     */
    void deleteBatch(List<Long> ids);

    /**
     * 搜索商品索引
     *
     * @param current   页码
     * @param size      每页数据数量
     * @param sortField 排序字段
     * @param sortRule  排序规则
     * @param keyword   关键词
     * @return 商品索引列表
     */
    Page<GoodsIndex> search(Integer current, Integer size, String sortField, String sortRule, String keyword);
}
