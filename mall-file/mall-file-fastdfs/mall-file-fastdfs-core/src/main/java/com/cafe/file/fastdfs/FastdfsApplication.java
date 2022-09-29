package com.cafe.file.fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:00
 * @Description:
 */
@SpringCloudApplication
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.cafe")
public class FastdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsApplication.class, args);
    }
}
