package com.cafe.file.minio.controller;

import com.cafe.file.minio.property.MinioProperties;
import com.cafe.file.minio.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.controller
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:24
 * @Description:
 */
@RestController
@RequestMapping(value = "/minio")
public class MinioController {

    private MinioService minioService;

    @Autowired
    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(MultipartFile file) {
        String result = minioService.upload(file);
        return ResponseEntity.ok(result);
    }
}
