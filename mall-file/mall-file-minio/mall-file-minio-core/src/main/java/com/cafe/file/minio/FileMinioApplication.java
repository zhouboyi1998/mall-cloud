package com.cafe.file.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:12
 * @Description:
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class FileMinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileMinioApplication.class, args);
    }
}
