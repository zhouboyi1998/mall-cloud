package com.cafe.minio.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.service
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:53
 * @Description:
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param bucket 存储桶
     * @param file   文件
     * @return 文件存储路径
     */
    String upload(String bucket, MultipartFile file);

    /**
     * 下载文件
     *
     * @param bucket       存储桶
     * @param filename     文件名
     * @param httpResponse HTTP 响应
     */
    void download(String bucket, String filename, HttpServletResponse httpResponse);

    /**
     * 获取文件外链
     *
     * @param bucket   存储桶
     * @param filename 文件名
     * @param expiry   过期时间 (单位: 秒)
     * @return 外链URL
     */
    String url(String bucket, String filename, Integer expiry);
}
