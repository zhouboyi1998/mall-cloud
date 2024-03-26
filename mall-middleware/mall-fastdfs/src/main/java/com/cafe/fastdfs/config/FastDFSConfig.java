package com.cafe.fastdfs.config;

import org.csource.fastdfs.ClientGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.config
 * @Author: zhouboyi
 * @Date: 2024/3/26 17:13
 * @Description: FastDFS 配置类
 */
@Configuration
public class FastDFSConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSConfig.class);

    @PostConstruct
    public void initClientGlobal() {
        try {
            // 加载配置文件中的 Tracker 连接信息
            ClientGlobal.init("fastdfs_client.conf");
            LOGGER.info("FastDFSConfig.initClientGlobal(): ClientGlobal init success!");
        } catch (Exception e) {
            LOGGER.error("FastDFSConfig.initClientGlobal(): ClientGlobal init failed! message -> {}", e.getMessage(), e);
        }
    }
}
