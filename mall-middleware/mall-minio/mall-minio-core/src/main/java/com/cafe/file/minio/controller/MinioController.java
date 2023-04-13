package com.cafe.file.minio.controller;

import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.log.annotation.LogPrint;
import com.cafe.file.minio.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.controller
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:24
 * @Description: MinIO 文件接口
 */
@Api(value = "MinIO 文件接口")
@RestController
@RequestMapping(value = "/minio")
public class MinioController {

    private final MinioService minioService;

    @Autowired
    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @LogPrint(value = "文件上传")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    })
    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        String filepath = minioService.upload(bucket, file);
        return ResponseEntity.ok(filepath);
    }

    @LogPrint(value = "文件下载")
    @ApiOperation(value = "文件下载")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/download/{bucket}/{filename}")
    public ResponseEntity<String> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename,
        HttpServletResponse httpResponse
    ) throws Exception {
        minioService.download(bucket, filename, httpResponse);
        return ResponseEntity.ok(HttpStatusEnum.SUCCESS.getReasonPhrase());
    }

    @LogPrint(value = "获取文件外链 (永久)")
    @ApiOperation(value = "获取文件外链 (永久)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/url/{bucket}/{filename}")
    public ResponseEntity<String> url(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename
    ) throws Exception {
        String url = minioService.url(bucket, filename);
        return ResponseEntity.ok(url);
    }

    @LogPrint(value = "获取文件外链 (限时)")
    @ApiOperation(value = "获取文件外链 (限时)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "过期时间 (单位: 秒)", name = "expiry", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/url/{bucket}/{filename}/{expiry}")
    public ResponseEntity<String> url(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename,
        @PathVariable(value = "expiry") Integer expiry
    ) throws Exception {
        String url = minioService.url(bucket, filename, expiry);
        return ResponseEntity.ok(url);
    }
}
