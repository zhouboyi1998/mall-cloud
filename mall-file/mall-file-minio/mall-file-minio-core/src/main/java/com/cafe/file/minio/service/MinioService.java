package com.cafe.file.minio.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
     * @param bucket 存储桶
     * @param file   文件
     * @return
     */
    String upload(String bucket, MultipartFile file);

    /**
     * 文件下载
     *
     * @param bucket       存储桶
     * @param fileName     文件名
     * @param httpResponse
     * @return
     */
    void download(String bucket, String fileName, HttpServletResponse httpResponse);

    /**
     * 获取文件外链 (永久)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @return
     */
    String getFileUrl(String bucket, String fileName);

    /**
     * 获取文件外链 (限时)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @param expiry   过期时间 (单位: 秒)
     * @return
     */
    String getFileUrl(String bucket, String fileName, Integer expiry);
}
