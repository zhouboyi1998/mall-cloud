package com.cafe.file.minio.controller;

import com.cafe.file.minio.service.MinioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    private MinioService minioService;

    @Autowired
    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @ApiOperation(value = "文件上传")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "MultipartFile")
    })
    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        MultipartFile file
    ) {
        try {
            String result = minioService.upload(bucket, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            LOGGER.error("MinioController.upload() failed to upload file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value = "文件下载")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/download/{bucket}/{fileName}")
    public ResponseEntity<String> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        HttpServletResponse httpResponse
    ) {
        try {
            minioService.download(bucket, fileName, httpResponse);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            LOGGER.error("MinioController.download() failed to download file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value = "获取文件外链 (永久)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/url/{bucket}/{fileName}")
    public ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName
    ) {
        try {
            String url = minioService.getFileUrl(bucket, fileName);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            LOGGER.error("MinioController.getFileUrl() failed to get permanent file url: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ApiOperation(value = "获取文件外链 (限时)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "expiry", value = "过期时间 (单位: 秒)", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/url/{bucket}/{fileName}/{expiry}")
    public ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName,
        @PathVariable(value = "expiry") Integer expiry
    ) {
        try {
            String url = minioService.getFileUrl(bucket, fileName, expiry);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            LOGGER.error("MinioController.getFileUrl() failed to get time-limited file url: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
