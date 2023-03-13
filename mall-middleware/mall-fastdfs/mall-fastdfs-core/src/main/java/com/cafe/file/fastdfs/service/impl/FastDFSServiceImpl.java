package com.cafe.file.fastdfs.service.impl;

import com.cafe.common.constant.StringConstant;
import com.cafe.file.fastdfs.model.FastDFSFile;
import com.cafe.file.fastdfs.property.FastDFSProperties;
import com.cafe.file.fastdfs.service.FastDFSService;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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

    private final FastDFSProperties fastDFSProperties;

    @Autowired
    public FastDFSServiceImpl(FastDFSProperties fastDFSProperties) {
        this.fastDFSProperties = fastDFSProperties;
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
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
        String[] result = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExtension(), null);
        // result[0]:
        return StringConstant.SLASH + result[0] + StringConstant.SLASH + result[1];
    }

    @Override
    public void download(String groupName, String remoteFilename, HttpServletResponse httpResponse) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] bytes = storageClient.download_file(groupName, remoteFilename);

        // 获取文件名
        String[] split = remoteFilename.split(StringConstant.SLASH);
        String filename = split[split.length - 1];

        // 配置 HTTP Response
        httpResponse.setCharacterEncoding(fastDFSProperties.getCharacterEncoding());
        httpResponse.setContentType(fastDFSProperties.getContentType());
        httpResponse.setHeader(fastDFSProperties.getHeaderKey(), fastDFSProperties.getHeaderValuePrefix() + filename);

        // 将要下载的文件写入 HTTP Response 中
        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(bytes);
        servletOutputStream.flush();
    }

    @Override
    public Integer delete(String groupName, String remoteFilename) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.delete_file(groupName, remoteFilename);
    }

    @Override
    public FileInfo fileInfo(String groupName, String remoteFilename) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.get_file_info(groupName, remoteFilename);
    }

    @Override
    public String trackerUrl() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        Integer trackerPort = ClientGlobal.getG_tracker_http_port();
        return ip + StringConstant.COLON + trackerPort;
    }

    @Override
    public StorageServer storageInfo() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }

    @Override
    public StorageServer storageServerInfo(String groupName, String remoteFilename) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorage(trackerServer, groupName, remoteFilename);
    }
}
