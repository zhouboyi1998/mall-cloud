package com.cafe.file.fastdfs.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.feign
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:26
 * @Description:
 */
@FeignClient(value = "mall-fastdfs")
@RequestMapping(value = "/fastdfs")
public interface FastDFSFeign {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) throws Exception;
}
