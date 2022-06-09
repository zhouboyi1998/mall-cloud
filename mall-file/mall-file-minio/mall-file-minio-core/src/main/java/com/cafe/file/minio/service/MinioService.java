package com.cafe.file.minio.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.service
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:53
 * @Description:
 */
public interface MinioService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
