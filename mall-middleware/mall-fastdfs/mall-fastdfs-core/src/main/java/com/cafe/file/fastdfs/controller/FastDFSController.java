package com.cafe.file.fastdfs.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.file.fastdfs.service.FastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.controller
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:35
 * @Description: FastDFS 文件接口
 */
@Api(value = "FastDFS 文件接口")
@RestController
@RequestMapping(value = "/fastdfs")
public class FastDFSController {

    private final FastDFSService fastDFSService;

    @Autowired
    public FastDFSController(FastDFSService fastDFSService) {
        this.fastDFSService = fastDFSService;
    }

    @LogPrint(value = "文件上传")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(value = "文件", name = "file", dataType = "MultipartFile", paramType = "form", required = true)
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        // 将文件上传到 FastDFS 中
        String[] values = fastDFSService.upload(file);
        String url = fastDFSService.getTrackerUrl() + "/" + values[0] + "/" + values[1];
        return ResponseEntity.ok(url);
    }
}