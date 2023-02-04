package com.cafe.file.minio.config;

import com.cafe.file.minio.property.MinioProperties;
import io.minio.MinioClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.config
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:15
 * @Description: Minio 配置类
 */
@Configuration
public class MinioConfig {

    private MinioProperties minioProperties;

    public MinioConfig(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    /**
     * 将 Minio 客户端注入到 Spring
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient
            .builder()
            .endpoint(minioProperties.getEndpoint())
            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
            .build();
    }
}
