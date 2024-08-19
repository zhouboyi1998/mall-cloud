package com.cafe.minio.config;

import com.cafe.minio.property.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.config
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:15
 * @Description: MinIO 配置类
 */
@RequiredArgsConstructor
@Configuration
public class MinioConfig {

    private final MinioProperties minioProperties;

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
