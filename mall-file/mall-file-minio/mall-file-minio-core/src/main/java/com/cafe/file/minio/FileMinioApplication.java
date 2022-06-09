package com.cafe.file.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:12
 * @Description:
 */
@SpringCloudApplication
@ComponentScan(basePackages = "com.cafe")
public class FileMinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileMinioApplication.class, args);
    }
}
