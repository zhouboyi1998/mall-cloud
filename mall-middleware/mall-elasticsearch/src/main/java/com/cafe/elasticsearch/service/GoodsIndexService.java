package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.index.GoodsIndex;
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
     * @param id
     * @return
     */
    GetResponse one(String id);

    /**
     * 插入商品索引
     *
     * @param goodsIndex
     * @return
     */
    IndexResponse insert(GoodsIndex goodsIndex);

    /**
     * 更新商品索引
     *
     * @param goodsIndex
     * @return
     */
    UpdateResponse update(GoodsIndex goodsIndex);

    /**
     * 删除商品索引
     *
     * @param id
     * @return
     */
    DeleteResponse delete(String id);

    /**
     * 批量插入商品索引
     *
     * @param goodsIndexList
     * @return
     */
    BulkResponse insertBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量更新商品索引
     *
     * @param goodsIndexList
     * @return
     */
    BulkResponse updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 批量删除商品索引
     *
     * @param ids
     * @return
     */
    BulkResponse deleteBatch(List<String> ids);

    /**
     * 查询商品索引
     *
     * @param keyword 关键词
     * @return
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
     * @return
     */
    SearchResponse page(Integer current, Integer size, String keyword, String sort, String rule);
}
