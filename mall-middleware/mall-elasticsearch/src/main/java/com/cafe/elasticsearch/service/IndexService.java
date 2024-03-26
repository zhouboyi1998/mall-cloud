package com.cafe.elasticsearch.service;

import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

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
     * @return
     * @throws IOException
     */
    Boolean exists(String index) throws IOException;

    /**
     * 创建索引
     *
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    CreateIndexResponse create(String index) throws IOException;

    /**
     * 删除索引
     *
     * @param index 索引名称
     * @return
     * @throws IOException
     */
    AcknowledgedResponse delete(String index) throws IOException;
}
