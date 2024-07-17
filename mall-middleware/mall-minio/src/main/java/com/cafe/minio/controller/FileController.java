package com.cafe.minio.controller;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.log.annotation.ApiLogPrint;
import com.cafe.minio.service.FileService;
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
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.controller
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:24
 * @Description: MinIO 文件接口
 */
@Api(value = "MinIO 文件接口")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiLogPrint(value = "上传文件")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    })
    @PostMapping(value = "/upload/{bucket}")
    public ResponseEntity<String> upload(
        @PathVariable(value = "bucket") String bucket,
        @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        String filepath = fileService.upload(bucket, file);
        return ResponseEntity.ok(filepath);
    }

    @ApiLogPrint(value = "下载文件")
    @ApiOperation(value = "下载文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/download/{bucket}/{filename}")
    public ResponseEntity<Void> download(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename,
        HttpServletResponse httpResponse
    ) throws Exception {
        fileService.download(bucket, filename, httpResponse);
        return ResponseEntity.ok().build();
    }

    @ApiLogPrint(value = "获取文件外链 (不指定过期时间, 默认过期时间: 7 天, 最大过期时间: 7天)")
    @ApiOperation(value = "获取文件外链 (不指定过期时间, 默认过期时间: 7 天, 最大过期时间: 7天)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "存储桶", name = "bucket", dataType = "String", paramType = "path", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "path", required = true)
    })
    @GetMapping(value = "/url/{bucket}/{filename}")
    public ResponseEntity<String> url(
        @PathVariable(value = "bucket") String bucket,
        @PathVariable(value = "filename") String filename
    ) throws Exception {
        String url = fileService.url(bucket, filename, (int) TimeUnit.DAYS.toSeconds(IntegerConstant.SEVEN));
        return ResponseEntity.ok(url);
    }

    @ApiLogPrint(value = "获取文件外链 (指定过期时间)")
    @ApiOperation(value = "获取文件外链 (指定过期时间)")
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
        String url = fileService.url(bucket, filename, expiry);
        return ResponseEntity.ok(url);
    }
}
