package com.cafe.file.minio.config;

import com.cafe.file.minio.property.MinioProperties;
import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.config
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:15
 * @Description: MinIO 配置类
 */
@Configuration
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Autowired
    public MinioConfig(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    /**
     * MinIO 客户端
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioProperties.getEndpoint())
            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
            .build();
    }
}
