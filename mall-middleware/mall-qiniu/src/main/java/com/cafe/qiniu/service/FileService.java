package com.cafe.qiniu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.service
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:33
 * @Description:
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param bucket 存储桶
     * @param file   文件
     * @return 存储痛 + 文件名
     */
    String upload(String bucket, MultipartFile file);

    /**
     * 删除文件
     *
     * @param bucket   存储桶
     * @param filename 文件名
     * @return 响应状态码
     */
    Integer delete(String bucket, String filename);
}
