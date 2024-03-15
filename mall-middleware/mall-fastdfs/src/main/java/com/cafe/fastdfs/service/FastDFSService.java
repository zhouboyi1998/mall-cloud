package com.cafe.fastdfs.service;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageServer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.service
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:03
 * @Description:
 */
public interface FastDFSService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    String upload(MultipartFile file) throws Exception;

    /**
     * 文件下载
     *
     * @param groupName      文件组名 (例如: group1)
     * @param remoteFilename 远程文件名 (例如: M00/00/00/xxx.jpg)
     * @param httpResponse
     * @return
     * @throws Exception
     */
    void download(String groupName, String remoteFilename, HttpServletResponse httpResponse) throws Exception;

    /**
     * 删除文件
     *
     * @param groupName      文件组名 (例如: group1)
     * @param remoteFilename 远程文件名 (例如: M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    Integer delete(String groupName, String remoteFilename) throws Exception;

    /**
     * 获取文件信息
     *
     * @param groupName      文件组名 (例如: group1)
     * @param remoteFilename 远程文件名 (例如: M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    FileInfo fileInfo(String groupName, String remoteFilename) throws Exception;

    /**
     * 获取访问 Tracker 的 URL
     *
     * @return
     * @throws Exception
     */
    String trackerUrl() throws Exception;

    /**
     * 获取 Storage 组的信息
     *
     * @return
     * @throws Exception
     */
    StorageServer storageInfo() throws Exception;

    /**
     * 获取文件所在的 Storage Server 的信息
     *
     * @param groupName      文件组名 (例如: group1)
     * @param remoteFilename 远程文件名 (例如: M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    StorageServer storageServerInfo(String groupName, String remoteFilename) throws Exception;
}
