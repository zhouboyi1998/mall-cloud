package com.cafe.file.fastdfs.service.impl;

import com.cafe.file.fastdfs.model.FastDFSFile;
import com.cafe.file.fastdfs.service.FastDFSService;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:36
 * @Description:
 */
@Service
public class FastDFSServiceImpl implements FastDFSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSServiceImpl.class);

    static {
        try {
            // 获取配置文件的路径
            String config = new ClassPathResource("fastdfs_client.conf").getPath();
            // 加载配置文件中的 Tracker 连接信息
            ClientGlobal.init(config);
            LOGGER.info("FastdfsServiceImpl: Connect FastDFS tracker success.");
        } catch (Exception e) {
            LOGGER.error("FastdfsServiceImpl: Connect FastDFS tracker fail -> {}", e.getMessage());
        }
    }

    @Override
    public String[] upload(MultipartFile file) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();

        // 封装文件上传信息
        FastDFSFile fastDFSFile = new FastDFSFile()
            .setName(originalFilename)
            .setExtension(StringUtils.getFilenameExtension(originalFilename))
            .setContent(file.getBytes());
        return storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExtension(), null);
    }

    @Override
    public InputStream download(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public Integer delete(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.delete_file(groupName, remoteFileName);
    }

    @Override
    public FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    @Override
    public String getTrackerUrl() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        Integer trackerPort = ClientGlobal.getG_tracker_http_port();
        return ip + ":" + trackerPort;
    }

    @Override
    public StorageServer getStorageInfo() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }

    @Override
    public StorageServer getStorageServerInfo(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorage(trackerServer, groupName, remoteFileName);
    }
}
