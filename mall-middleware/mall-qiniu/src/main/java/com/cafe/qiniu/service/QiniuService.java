package com.cafe.qiniu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.service
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:33
 * @Description:
 */
public interface QiniuService {

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
     * 文件删除
     *
     * @param bucket   存储桶
     * @param filename 文件名
     * @return
     * @throws Exception
     */
    void delete(String bucket, String filename) throws Exception;
}
