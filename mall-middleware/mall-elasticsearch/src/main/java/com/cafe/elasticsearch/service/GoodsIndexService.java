package com.cafe.elasticsearch.service;

import com.cafe.elasticsearch.index.GoodsIndex;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
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
     * @throws IOException
     */
    GetResponse one(String id) throws IOException;

    /**
     * 插入商品索引
     *
     * @param goodsIndex
     * @return
     * @throws IOException
     */
    IndexResponse insert(GoodsIndex goodsIndex) throws IOException;

    /**
     * 更新商品索引
     *
     * @param goodsIndex
     * @return
     * @throws IOException
     */
    UpdateResponse update(GoodsIndex goodsIndex) throws IOException;

    /**
     * 删除商品索引
     *
     * @param id
     * @return
     * @throws IOException
     */
    DeleteResponse delete(String id) throws IOException;

    /**
     * 批量插入商品索引
     *
     * @param goodsIndexList
     * @return
     * @throws IOException
     */
    BulkResponse insertBatch(List<GoodsIndex> goodsIndexList) throws IOException;

    /**
     * 批量更新商品索引
     *
     * @param goodsIndexList
     * @return
     * @throws IOException
     */
    BulkResponse updateBatch(List<GoodsIndex> goodsIndexList) throws IOException;

    /**
     * 批量删除商品索引
     *
     * @param ids
     * @return
     * @throws IOException
     */
    BulkResponse deleteBatch(List<String> ids) throws IOException;

    /**
     * 搜索商品索引
     *
     * @param current 页码
     * @param size    每页数据数量
     * @param keyword 关键词
     * @param sort    排序属性
     * @param rule    排序规则
     * @return
     * @throws IOException
     */
    SearchResponse page(Integer current, Integer size, String keyword, String sort, String rule) throws IOException;
}
