package com.cafe.file.minio.service.impl;

import com.cafe.file.minio.controller.MinioController;
import com.cafe.file.minio.property.MinioProperties;
import com.cafe.file.minio.service.MinioService;
import com.cafe.file.minio.tool.MinioTool;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.service.impl
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:54
 * @Description:
 */
@Service
public class MinioServiceImpl implements MinioService {

    private static Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    private MinioProperties minioProperties;

    private MinioClient minioClient;

    @Autowired
    public MinioServiceImpl(
        MinioProperties minioProperties,
        MinioClient minioClient
    ) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            // 生成文件名
            String fileName = MinioTool.generateFileName();
            PutObjectArgs args = PutObjectArgs
                .builder()
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();

            minioClient.putObject(args);
            return fileName;
        } catch (Exception e) {
            LOGGER.error("MinioServiceImpl.upload() failed to upload file: {}", e.getMessage());
            return e.getMessage();
        }
    }
}
