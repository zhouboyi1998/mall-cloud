package com.cafe.opensearch.service;

import com.cafe.opensearch.model.index.GoodsIndex;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.update.UpdateResponse;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.opensearch.service
 * @Author: zhouboyi
 * @Date: 2025/6/16 21:06
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
     * @return 保存响应
     */
    IndexResponse save(GoodsIndex goodsIndex);

    /**
     * 批量保存商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 批量响应
     */
    BulkResponse saveBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 修改商品索引
     *
     * @param goodsIndex 商品索引
     * @return 修改响应
     */
    UpdateResponse update(GoodsIndex goodsIndex);

    /**
     * 批量修改商品索引
     *
     * @param goodsIndexList 商品索引列表
     * @return 批量响应
     */
    BulkResponse updateBatch(List<GoodsIndex> goodsIndexList);

    /**
     * 删除商品索引
     *
     * @param id 商品id
     * @return 删除响应
     */
    DeleteResponse delete(Long id);

    /**
     * 批量删除商品索引
     *
     * @param ids 商品id列表
     * @return 批量响应
     */
    BulkResponse deleteBatch(List<Long> ids);
}
