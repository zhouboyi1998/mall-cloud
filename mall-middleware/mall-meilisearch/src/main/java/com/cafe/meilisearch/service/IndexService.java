package com.cafe.meilisearch.service;

import com.cafe.meilisearch.model.dto.IndexDTO;
import com.cafe.meilisearch.model.dto.Page;
import com.meilisearch.sdk.model.IndexesQuery;
import com.meilisearch.sdk.model.TaskInfo;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.service
 * @Author: zhouboyi
 * @Date: 2025/6/2 2:33
 * @Description:
 */
public interface IndexService {

    /**
     * 查询索引列表
     *
     * @return 索引列表
     */
    List<IndexDTO> list();

    /**
     * 分页查询索引列表
     *
     * @param indexesQuery 分页查询参数
     * @return 索引列表
     */
    Page<IndexDTO> page(IndexesQuery indexesQuery);

    /**
     * 根据uid查询单个索引
     *
     * @param uid 索引uid
     * @return 索引
     */
    IndexDTO one(String uid);

    /**
     * 创建索引
     *
     * @param uid        索引uid
     * @param primaryKey 索引的主键字段
     * @return 任务信息
     */
    TaskInfo create(String uid, String primaryKey);

    /**
     * 修改索引的主键字段
     *
     * @param uid        索引uid
     * @param primaryKey 索引的主键字段
     * @return 任务信息
     */
    TaskInfo update(String uid, String primaryKey);

    /**
     * 删除索引
     *
     * @param uid 索引uid
     * @return 任务信息
     */
    TaskInfo delete(String uid);
}
