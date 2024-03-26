package com.cafe.fastdfs.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.feign
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:26
 * @Description:
 */
@FeignClient(value = "mall-fastdfs", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/file")
public interface FileFeign {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return
     */
    @PostMapping(value = "/upload")
    ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file);

    /**
     * 下载文件
     *
     * @param group        文件组
     * @param filename     文件名
     * @param httpResponse
     * @return
     */
    @GetMapping(value = "/download")
    ResponseEntity<Void> download(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename,
        HttpServletResponse httpResponse
    );

    /**
     * 删除文件
     *
     * @param group    文件组
     * @param filename 文件名
     * @return
     */
    @DeleteMapping(value = "/delete")
    ResponseEntity<Integer> delete(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    );

    /**
     * 获取文件信息
     *
     * @param group    文件组
     * @param filename 文件名
     * @return
     */
    @GetMapping(value = "/info")
    ResponseEntity<FileInfo> info(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    );
}
