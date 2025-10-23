package com.cafe.config.environment;

import com.cafe.config.property.ConfigurationCenterProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.config.environment
 * @Author: zhouboyi
 * @Date: 2025/10/23 16:07
 * @Description: 本地配置环境仓库
 */
@RequiredArgsConstructor
@Repository
@ConditionalOnProperty(name = "configuration-center.repository", havingValue = "NATIVE")
public class NativeConfigEnvironmentRepository extends AbstractEnvironmentRepository {

    private final ConfigurationCenterProperties configurationCenterProperties;

    /**
     * 配置目录路径
     */
    private String basePath;

    /**
     * 解析配置目录路径
     */
    @PostConstruct
    public void resolveBasePath() throws FileNotFoundException {
        String resourceLocation = ResourceUtils.FILE_URL_PREFIX + configurationCenterProperties.getLocal().getBasePath();
        basePath = ResourceUtils.getFile(resourceLocation).getPath();
    }

    @Override
    protected void addPropertySource(Environment environment, String propertyName) {
        // 解析配置文件路径
        File configFile = new File(basePath + File.separator + propertyName + ".yaml");
        // 检查配置文件是否存在
        if (!configFile.exists()) {
            return;
        }

        // 加载配置文件
        Resource resource = new FileSystemResource(configFile);
        // 创建 yaml - properties 格式转换工厂
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        // 将配置内容写入到 yaml - properties 格式转换工厂中
        factory.setResources(resource);
        // 将配置内容从 yaml 格式转换为 properties 格式
        Properties properties = factory.getObject();
        if (Objects.nonNull(properties) && !properties.isEmpty()) {
            // 将 properties 格式配置内容添加到配置环境中
            environment.add(new PropertySource(propertyName, properties));
        }
    }
}
