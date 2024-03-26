package com.cafe.fastdfs.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.fastdfs.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.csource.fastdfs.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.controller
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:35
 * @Description: FastDFS 文件接口
 */
@Api(value = "FastDFS 文件接口")
@RestController
@RequestMapping(value = "/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @LogPrint(value = "上传文件")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String filepath = fileService.upload(file);
        return ResponseEntity.ok(filepath);
    }

    @LogPrint(value = "下载文件")
    @ApiOperation(value = "下载文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/download")
    public ResponseEntity<Void> download(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename,
        HttpServletResponse httpResponse
    ) throws Exception {
        fileService.download(group, filename, httpResponse);
        return ResponseEntity.ok().build();
    }

    @LogPrint(value = "删除文件")
    @ApiOperation(value = "删除文件")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Integer> delete(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    ) throws Exception {
        Integer count = fileService.delete(group, filename);
        return ResponseEntity.ok(count);
    }

    @LogPrint(value = "获取文件信息")
    @ApiOperation(value = "获取文件信息")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组", name = "group", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "文件名", name = "filename", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/info")
    public ResponseEntity<FileInfo> info(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    ) throws Exception {
        FileInfo fileInfo = fileService.info(group, filename);
        return ResponseEntity.ok(fileInfo);
    }
}
