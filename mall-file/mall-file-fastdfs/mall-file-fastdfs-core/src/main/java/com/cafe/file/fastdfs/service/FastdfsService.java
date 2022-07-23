package com.cafe.file.fastdfs.service;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.service
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:03
 * @Description:
 */
public interface FastdfsService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    String[] upload(MultipartFile file) throws Exception;

    /**
     * 文件下载
     *
     * @param groupName      文件的组名 (group1)
     * @param remoteFileName 文件的存储路径名 (M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    InputStream download(String groupName, String remoteFileName) throws Exception;

    /**
     * 删除文件
     *
     * @param groupName      文件的组名 (group1)
     * @param remoteFileName 文件的存储路径名 (M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    Integer delete(String groupName, String remoteFileName) throws Exception;

    /**
     * 获取文件信息
     *
     * @param groupName      文件的组名 (group1)
     * @param remoteFileName 文件的存储路径名 (M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception;

    /**
     * 获取访问 Tracker 的 URL
     *
     * @return
     * @throws Exception
     */
    String getTrackerUrl() throws Exception;

    /**
     * 获取 Storage 组的信息
     *
     * @return
     * @throws Exception
     */
    StorageServer getStorageInfo() throws Exception;

    /**
     * 获取文件所在的 Storage Server 的信息
     *
     * @param groupName      文件的组名 (group1)
     * @param remoteFileName 文件的存储路径名 (M00/00/00/xxx.jpg)
     * @return
     * @throws Exception
     */
    StorageServer getStorageServerInfo(String groupName, String remoteFileName) throws Exception;
}
