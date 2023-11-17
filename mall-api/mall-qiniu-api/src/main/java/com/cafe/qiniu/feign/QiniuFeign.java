package com.cafe.qiniu.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.feign
 * @Author: zhouboyi
 * @Date: 2023/11/17 21:04
 * @Description:
 */
@FeignClient(value = "mall-qiniu", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/qiniu")
public interface QiniuFeign {

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
        @RequestParam(value = "file") MultipartFile file
    );

    /**
     * 文件删除
     *
     * @param bucket   存储桶
     * @param filename 文件名
     * @return
     */
    @DeleteMapping(value = "/delete/{bucket}/{filename}")
    ResponseEntity<Void> delete(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename
    );
}
