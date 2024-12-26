package com.cafe.minio.feign;

import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.feign
 * @Author: zhouboyi
 * @Date: 2022/6/9 20:03
 * @Description:
 */
@FeignClient(value = "mall-minio", contextId = "file", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/file")
public interface FileFeign {

    /**
     * 上传文件
     *
     * @param bucket 存储桶
     * @param file   文件
     * @return 文件存储路径
     */
    @PostMapping(value = "/upload/{bucket}")
    ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        @RequestParam(value = "file") MultipartFile file
    );

    /**
     * 下载文件
     *
     * @param bucket       存储桶
     * @param fileName     文件名
     * @param httpResponse HTTP 响应
     */
    @GetMapping(value = "/download/{bucket}/{fileName}")
    ResponseEntity<Void> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        HttpServletResponse httpResponse
    );

    /**
     * 获取文件外链 (不指定过期时间, 默认过期时间: 7 天, 最大过期时间: 7天)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @return 外链URL
     */
    @GetMapping(value = "/url/{bucket}/{fileName}")
    ResponseEntity<String> url(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName
    );

    /**
     * 获取文件外链 (指定过期时间)
     *
     * @param bucket   存储桶
     * @param fileName 文件名
     * @param expiry   过期时间 (单位: 秒)
     * @return 外链URL
     */
    @GetMapping(value = "/url/{bucket}/{fileName}/{expiry}")
    ResponseEntity<String> url(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        @PathVariable(value = "expiry") Integer expiry
    );
}
