package com.cafe.file.minio.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.feign
 * @Author: zhouboyi
 * @Date: 2022/6/9 20:03
 * @Description:
 */
@FeignClient(value = "mall-minio")
@RequestMapping(value = "/minio")
public interface MinioFeign {

    /**
     * 文件上传
     *
     * @param bucket 存储桶
     * @param file   文件
     * @return
     */
    @PostMapping(value = "/upload/{bucket}")
    ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        MultipartFile file
    );

    /**
     * 文件下载
     *
     * @param bucket       存储桶
     * @param fileName     文件名
     * @param httpResponse
     * @return
     */
    @GetMapping(value = "/download/{bucket}/{fileName}")
    ResponseEntity<String> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        HttpServletResponse httpResponse
    );

    /**
     * 获取文件外链 (永久)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @return
     */
    @GetMapping(value = "/url/{bucket}/{fileName}")
    ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName
    );

    /**
     * 获取文件外链 (限时)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @param expiry   过期时间 (单位: 秒)
     * @return
     */
    @GetMapping(value = "/url/{bucket}/{fileName}/{expiry}")
    ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        @PathVariable(value = "expiry") Integer expiry
    );
}
