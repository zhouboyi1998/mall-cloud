package com.cafe.file.minio.controller;

import com.cafe.file.minio.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        MultipartFile file
    ) {
        String result = minioService.upload(bucket, file);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/download/{bucket}/{fileName}")
    public ResponseEntity<String> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        HttpServletResponse httpResponse
    ) {
        minioService.download(bucket, fileName, httpResponse);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/url/{bucket}/{fileName}")
    public ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName
    ) {
        String url = minioService.getFileUrl(bucket, fileName);
        return ResponseEntity.ok(url);
    }

    @GetMapping(value = "/url/{bucket}/{fileName}/{expiry}")
    public ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        @PathVariable(value = "expiry") Integer expiry
    ) {
        String url = minioService.getFileUrl(bucket, fileName, expiry);
        return ResponseEntity.ok(url);
    }
}
