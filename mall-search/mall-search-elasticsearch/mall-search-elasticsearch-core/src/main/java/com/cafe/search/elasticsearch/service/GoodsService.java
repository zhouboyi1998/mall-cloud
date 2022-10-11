package com.cafe.search.elasticsearch.service;

import com.cafe.goods.bo.GoodsBO;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.reindex.BulkByScrollResponse;

import java.io.IOException;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:39
 * @Description:
 */
public interface GoodsService {

    /**
     * 获取商品
     *
     * @param _id
     * @return
     * @throws IOException
     */
    GetResponse one(String _id) throws IOException;

    /**
     * 插入商品
     *
     * @param goodsBOList
     * @return
     * @throws IOException
     */
    IndexResponse insert(GoodsBO goodsBOList) throws IOException;

    /**
     * 更新商品
     *
     * @param goodsBOList
     * @return
     * @throws IOException
     */
    UpdateResponse update(GoodsBO goodsBOList) throws IOException;

    /**
     * 删除商品
     *
     * @param _id
     * @return
     * @throws IOException
     */
    DeleteResponse delete(String _id) throws IOException;

    /**
     * 批量插入商品
     *
     * @param goodsBOList
     * @return
     * @throws IOException
     */
    BulkResponse insertBatch(List<GoodsBO> goodsBOList) throws IOException;

    /**
     * 批量更新商品
     *
     * @param goodsBOList
     * @return
     * @throws IOException
     */
    BulkResponse updateBatch(List<GoodsBO> goodsBOList) throws IOException;

    /**
     * 批量删除商品
     *
     * @param _ids
     * @return
     * @throws IOException
     */
    BulkResponse deleteBatch(List<String> _ids) throws IOException;

    /**
     * 搜索商品
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

    /**
     * 批量导入商品
     *
     * @param current
     * @param size
     * @return
     * @throws IOException
     */
    BulkResponse importBatch(Long current, Long size) throws IOException;

    /**
     * 根据 ids 批量导入商品
     *
     * @param ids SKU ID
     * @return
     * @throws IOException
     */
    BulkResponse importBatch(List<Long> ids) throws IOException;

    /**
     * 根据 idField 更新 nameField 的值
     *
     * @param idField   筛选列的属性名
     * @param idValue   筛选列的值
     * @param nameField 更新列的属性名
     * @param nameValue 更新列的值
     * @return
     * @throws IOException
     */
    BulkByScrollResponse updateBatchByQuery(String idField, Long idValue, String nameField, String nameValue) throws IOException;
}
