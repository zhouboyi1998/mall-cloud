package com.cafe.fastdfs.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.config
 * @Author: zhouboyi
 * @Date: 2024/3/26 17:13
 * @Description: FastDFS 配置类
 */
@Slf4j
@Configuration
public class FastDFSConfig {

    @SneakyThrows
    @PostConstruct
    public void initClientGlobal() {
        // 加载配置文件中的 Tracker 连接信息
        ClientGlobal.init("fastdfs_client.conf");
        log.info("FastDFSConfig.initClientGlobal(): ClientGlobal init success!");
    }
}
