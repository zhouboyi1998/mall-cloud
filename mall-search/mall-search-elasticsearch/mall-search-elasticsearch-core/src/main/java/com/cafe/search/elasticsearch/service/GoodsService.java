package com.cafe.search.elasticsearch.service;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2022/7/28 9:39
 * @Description:
 */
public interface GoodsService {

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
    SearchResponse search(Integer current, Integer size, String keyword, String sort, String rule) throws IOException;

    /**
     * 批量导入商品数据
     *
     * @param current
     * @param size
     * @return
     * @throws IOException
     */
    BulkResponse importBatch(Long current, Long size) throws IOException;
}
