package com.cafe.qiniu.config;

import com.cafe.qiniu.enumeration.RegionEnum;
import com.cafe.qiniu.property.QiniuProperties;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.config
 * @Author: zhouboyi
 * @Date: 2023/10/18 17:41
 * @Description: 七牛云配置类
 */
@Configuration
public class QiniuConfig {

    private final QiniuProperties qiniuProperties;

    @Autowired
    public QiniuConfig(QiniuProperties qiniuProperties) {
        this.qiniuProperties = qiniuProperties;
    }

    /**
     * 七牛云授权管理器
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
    }

    /**
     * 存储配置
     *
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration configuration() {
        return new com.qiniu.storage.Configuration(RegionEnum.getRegionByName(qiniuProperties.getRegion()));
    }

    /**
     * 存储桶管理器
     *
     * @return
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), configuration());
    }

    /**
     * 文件上传管理器
     *
     * @return
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(configuration());
    }
}
