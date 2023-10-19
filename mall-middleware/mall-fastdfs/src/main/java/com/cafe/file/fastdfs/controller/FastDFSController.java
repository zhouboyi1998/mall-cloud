package com.cafe.file.fastdfs.controller;

import com.cafe.common.log.annotation.LogPrint;
import com.cafe.file.fastdfs.service.FastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
        String filepath = fastDFSService.upload(file);
        return ResponseEntity.ok(filepath);
    }

    @LogPrint(value = "文件下载")
    @ApiOperation(value = "文件下载")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "文件组名", name = "groupName", dataType = "String", paramType = "query", required = true),
        @ApiImplicitParam(value = "远程文件名", name = "remoteFilename", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/download")
    public ResponseEntity<Void> download(
        @RequestParam(value = "groupName") String groupName,
        @RequestParam(value = "remoteFilename") String remoteFilename,
        HttpServletResponse httpResponse
    ) throws Exception {
        fastDFSService.download(groupName, remoteFilename, httpResponse);
        return ResponseEntity.ok().build();
    }

    @LogPrint(value = "获取访问 Tracker 的 URL")
    @ApiOperation(value = "获取访问 Tracker 的 URL")
    @GetMapping(value = "/tracker-url")
    public ResponseEntity<String> trackerUrl() throws Exception {
        String trackerUrl = fastDFSService.trackerUrl();
        return ResponseEntity.ok(trackerUrl);
    }
}
