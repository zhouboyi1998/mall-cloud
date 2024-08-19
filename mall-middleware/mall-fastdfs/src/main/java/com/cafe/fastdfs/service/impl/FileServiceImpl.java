package com.cafe.fastdfs.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.fastdfs.bo.FileBO;
import com.cafe.fastdfs.property.FastDFSProperties;
import com.cafe.fastdfs.service.FileService;
import lombok.SneakyThrows;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.service.impl
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:36
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    private final FastDFSProperties fastDFSProperties;

    @Autowired
    public FileServiceImpl(FastDFSProperties fastDFSProperties) {
        this.fastDFSProperties = fastDFSProperties;
    }

    @SneakyThrows
    @Override
    public String upload(MultipartFile file) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();

        // 封装文件上传信息
        FileBO fileBO = new FileBO()
            .setName(originalFilename)
            .setExtension(StringUtils.getFilenameExtension(originalFilename))
            .setContent(file.getBytes());
        String[] result = storageClient.upload_file(fileBO.getContent(), fileBO.getExtension(), null);
        // result[0]: 文件组, result[1]: 文件名
        return StringConstant.SLASH + result[0] + StringConstant.SLASH + result[1];
    }

    @SneakyThrows
    @Override
    public void download(String group, String filename, HttpServletResponse httpResponse) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        byte[] bytes = storageClient.download_file(group, filename);

        // 获取不带路径的文件名
        String[] split = filename.split(StringConstant.SLASH);
        String lastFilename = split[split.length - 1];

        // 配置 HTTP Response
        httpResponse.setCharacterEncoding(fastDFSProperties.getCharacterEncoding());
        httpResponse.setContentType(fastDFSProperties.getContentType());
        httpResponse.setHeader(fastDFSProperties.getHeaderKey(), fastDFSProperties.getHeaderValuePrefix() + lastFilename);

        // 将要下载的文件写入 HTTP Response 中
        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(bytes);
        servletOutputStream.flush();
    }

    @SneakyThrows
    @Override
    public Integer delete(String group, String filename) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.delete_file(group, filename);
    }

    @SneakyThrows
    @Override
    public FileInfo info(String group, String filename) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient.get_file_info(group, filename);
    }
}
