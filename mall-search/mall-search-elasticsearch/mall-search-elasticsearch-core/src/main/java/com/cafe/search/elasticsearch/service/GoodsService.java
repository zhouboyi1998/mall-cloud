package com.cafe.search.elasticsearch.service;

import com.cafe.goods.dto.SkuElasticSearchDTO;
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
     * @param dto
     * @return
     * @throws IOException
     */
    IndexResponse insert(SkuElasticSearchDTO dto) throws IOException;

    /**
     * 更新商品
     *
     * @param dto
     * @return
     * @throws IOException
     */
    UpdateResponse update(SkuElasticSearchDTO dto) throws IOException;

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
     * @param dtoList
     * @return
     * @throws IOException
     */
    BulkResponse insertBatch(List<SkuElasticSearchDTO> dtoList) throws IOException;

    /**
     * 批量更新商品
     *
     * @param dtoList
     * @return
     * @throws IOException
     */
    BulkResponse updateBatch(List<SkuElasticSearchDTO> dtoList) throws IOException;

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
}
