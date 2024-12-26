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
     * @return 存储桶列表
     */
    List<String> list();

    /**
     * 查询存储桶是否存在
     *
     * @param bucket 存储桶
     * @return 查询结果 (true 存在, false 不存在)
     */
    Boolean exists(String bucket);

    /**
     * 新建存储桶
     *
     * @param bucket 存储桶
     */
    void create(String bucket);

    /**
     * 删除存储桶
     *
     * @param bucket 存储桶
     */
    void remove(String bucket);
}
