package com.cafe.fastdfs.service;

import org.csource.fastdfs.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.service
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:03
 * @Description:
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    String upload(MultipartFile file) throws Exception;

    /**
     * 下载文件
     *
     * @param group        文件组 (例如: group1)
     * @param filename     文件名 (例如: M00/00/00/xxx.jpg)
     * @param httpResponse
     * @return
     * @throws Exception
     */
    void download(String group, String filename, HttpServletResponse httpResponse) throws Exception;

    /**
     * 删除文件
     *
     * @param group    文件组 (例如: group1)
     * @param filename 文件名 (例如: M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    Integer delete(String group, String filename) throws Exception;

    /**
     * 获取文件信息
     *
     * @param group    文件组 (例如: group1)
     * @param filename 文件名 (例如: M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    FileInfo info(String group, String filename) throws Exception;
}
