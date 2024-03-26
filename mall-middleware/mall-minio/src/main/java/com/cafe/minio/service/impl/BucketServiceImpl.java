package com.cafe.minio.service.impl;

import com.cafe.minio.service.BucketService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/26 15:10
 * @Description:
 */
@Service
public class BucketServiceImpl implements BucketService {

    private final MinioClient minioClient;

    @Autowired
    public BucketServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public List<String> list() throws Exception {
        // 查询存储桶列表, 返回存储桶名称列表
        return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
    }

    @Override
    public Boolean exists(String bucket) throws Exception {
        // 构造参数
        BucketExistsArgs args = BucketExistsArgs.builder().bucket(bucket).build();
        // 查询存储桶是否存在
        return minioClient.bucketExists(args);
    }

    @Override
    public void create(String bucket) throws Exception {
        // 构造参数
        MakeBucketArgs args = MakeBucketArgs.builder().bucket(bucket).build();
        // 新建存储桶
        minioClient.makeBucket(args);
    }

    @Override
    public void remove(String bucket) throws Exception {
        // 构造参数
        RemoveBucketArgs args = RemoveBucketArgs.builder().bucket(bucket).build();
        // 删除存储桶
        minioClient.removeBucket(args);
    }
}
