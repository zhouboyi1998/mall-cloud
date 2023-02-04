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
     * @throws Exception
     */
    String upload(String bucket, MultipartFile file) throws Exception;

    /**
     * 文件下载
     *
     * @param bucket       存储桶
     * @param fileName     文件名
     * @param httpResponse
     * @return
     * @throws Exception
     */
    void download(String bucket, String fileName, HttpServletResponse httpResponse) throws Exception;

    /**
     * 获取文件外链 (永久)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    String getFileUrl(String bucket, String fileName) throws Exception;

    /**
     * 获取文件外链 (限时)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @param expiry   过期时间 (单位: 秒)
     * @return
     * @throws Exception
     */
    String getFileUrl(String bucket, String fileName, Integer expiry) throws Exception;
}
