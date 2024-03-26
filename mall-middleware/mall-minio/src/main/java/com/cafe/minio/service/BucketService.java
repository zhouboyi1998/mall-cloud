package com.cafe.minio.service;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.service
 * @Author: zhouboyi
 * @Date: 2024/3/26 15:09
 * @Description:
 */
public interface BucketService {

    /**
     * 查询存储桶列表
     *
     * @return
     * @throws Exception
     */
    List<String> list() throws Exception;

    /**
     * 查询存储桶是否存在
     *
     * @param bucket 存储桶
     * @return
     * @throws Exception
     */
    Boolean exists(String bucket) throws Exception;

    /**
     * 新建存储桶
     *
     * @param bucket 存储桶
     * @return
     * @throws Exception
     */
    void create(String bucket) throws Exception;

    /**
     * 删除存储桶
     *
     * @param bucket 存储桶
     * @return
     * @throws Exception
     */
    void remove(String bucket) throws Exception;
}
