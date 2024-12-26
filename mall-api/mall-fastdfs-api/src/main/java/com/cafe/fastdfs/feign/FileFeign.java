package com.cafe.fastdfs.feign;

import com.cafe.fastdfs.model.vo.FileInfoVO;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
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
@FeignClient(value = "mall-fastdfs", contextId = "file", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/file")
public interface FileFeign {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件组 + 文件名 (例如: /group1/M00/00/00/xxx.jpg)
     */
    @PostMapping(value = "/upload")
    ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file);

    /**
     * 下载文件
     *
     * @param group        文件组
     * @param filename     文件名
     * @param httpResponse HTTP 响应
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
     * @return 删除结果 (0 删除失败, 1 删除成功)
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
     * @return 文件信息
     */
    @GetMapping(value = "/info")
    ResponseEntity<FileInfoVO> info(
        @RequestParam(value = "group") String group,
        @RequestParam(value = "filename") String filename
    );
}
