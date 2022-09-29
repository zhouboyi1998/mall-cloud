package com.cafe.file.minio.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.file.minio.service.MinioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @LogPrint(description = "文件上传")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "MultipartFile")
    })
    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        MultipartFile file
    ) throws Exception {
        String result = minioService.upload(bucket, file);
        return ResponseEntity.ok(result);
    }

    @LogPrint(description = "文件下载")
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
    ) throws Exception {
        minioService.download(bucket, fileName, httpResponse);
        return ResponseEntity.ok("success");
    }

    @LogPrint(description = "获取文件外链 (永久)")
    @ApiOperation(value = "获取文件外链 (永久)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bucket", value = "存储桶", required = true, paramType = "path", dataType = "String"),
        @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/url/{bucket}/{fileName}")
    public ResponseEntity<String> getFileUrl(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "fileName") String fileName
    ) throws Exception {
        String url = minioService.getFileUrl(bucket, fileName);
        return ResponseEntity.ok(url);
    }

    @LogPrint(description = "获取文件外链 (限时)")
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
    ) throws Exception {
        String url = minioService.getFileUrl(bucket, fileName, expiry);
        return ResponseEntity.ok(url);
    }
}
