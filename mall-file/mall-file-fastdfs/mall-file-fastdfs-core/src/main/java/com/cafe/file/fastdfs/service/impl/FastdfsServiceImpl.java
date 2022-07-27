package com.cafe.file.fastdfs.service.impl;

import com.cafe.file.fastdfs.model.FastdfsFile;
import com.cafe.file.fastdfs.service.FastdfsService;
import org.csource.fastdfs.*;
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
public class FastdfsServiceImpl implements FastdfsService {

    static {
        try {
            // 获取配置文件位置
            String config = new ClassPathResource("fastdfs_client.conf").getPath();
            // 加载配置文件中的 Tracker 连接信息
            ClientGlobal.init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] upload(MultipartFile file) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 封装文件上传信息
        FastdfsFile fastdfsFile = new FastdfsFile(
            file.getOriginalFilename(),
            StringUtils.getFilenameExtension(file.getOriginalFilename()),
            file.getBytes()
        );
        String[] values = storageClient.upload_file(fastdfsFile.getContent(), fastdfsFile.getExtension(), null);
        return values;
    }

    @Override
    public InputStream download(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;
    }

    @Override
    public Integer delete(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        Integer status = storageClient.delete_file(groupName, remoteFileName);
        return status;
    }

    @Override
    public FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        return fileInfo;
    }

    @Override
    public String getTrackerUrl() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        Integer trackerPort = ClientGlobal.getG_tracker_http_port();
        String url = ip + ":" + trackerPort;
        return url;
    }

    @Override
    public StorageServer getStorageInfo() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        return storeStorage;
    }

    @Override
    public StorageServer getStorageServerInfo(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getFetchStorage(trackerServer, groupName, remoteFileName);
        return storageServer;
    }
}
