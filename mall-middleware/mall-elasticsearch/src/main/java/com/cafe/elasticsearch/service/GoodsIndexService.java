package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

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
    GetResponse one(String id);

    /**
     * 插入商品索引
     *
     * @param goodsIndex 商品索引
     * @return 索引响应
     */
    IndexResponse insert(GoodsIndex goodsIndex);

    /**
     * 更新商品索引
     *
     * @param goodsIndex 商品索引
     * @return 更新响应
     */
    UpdateResponse update(GoodsIndex goodsIndex);

    /**
     * 删除商品索引
     *
     * @param id 商品ID
     * @return 删除响应
     */
    DeleteResponse delete(String id);

    /**
     * 批量插入商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 主体响应
     */
    BulkResponse insertBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量更新商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 主体响应
     */
    BulkResponse updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品ID列表
     * @return 主体响应
     */
    BulkResponse deleteBatch(List<String> ids);

    /**
     * 查询商品索引
     *
     * @param keyword 关键词
     * @return 查询响应
     */
    SearchResponse list(String keyword);

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
    SearchResponse page(Integer current, Integer size, String keyword, String sort, String rule);
}
