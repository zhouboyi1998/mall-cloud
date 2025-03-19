package com.cafe.elasticsearch.service;

import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.service
 * @Author: zhouboyi
 * @Date: 2024/3/27 2:27
 * @Description:
 */
public interface IndexService {

    /**
     * 查询索引是否存在
     *
     * @param index 索引名称
     * @return 查询结果 (true 存在, false 不存在)
     */
    Boolean exists(String index);

    /**
     * 创建索引
     *
     * @param index 索引名称
     * @return 创建索引响应
     */
    CreateIndexResponse create(String index);

    /**
     * 删除索引
     *
     * @param index 索引名称
     * @return 回复响应
     */
    AcknowledgedResponse delete(String index);
}
