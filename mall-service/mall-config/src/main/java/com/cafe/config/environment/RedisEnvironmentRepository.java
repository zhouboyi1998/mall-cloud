package com.cafe.config.environment;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.config.environment
 * @Author: zhouboyi
 * @Date: 2024/2/25 19:55
 * @Description: Redis 配置环境仓库
 */
@Repository
public class RedisEnvironmentRepository extends AbstractEnvironmentRepository {

    /**
     * 配置在 Redis 中的 Hash
     */
    private static final String REDIS_HASH = "CONFIG";

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisEnvironmentRepository(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void addPropertySource(Environment environment, String propertyName) {
        // 从 Redis 数据源中读取 yaml 格式的配置内容 (propertyName 即配置在 Redis 中的 Key)
        String configuration = stringRedisTemplate.<String, String>opsForHash().get(REDIS_HASH, propertyName);
        if (Objects.isNull(configuration)) {
            return;
        }

        // 使用输入流读取配置中的内容
        InputStream inputStream = new ByteArrayInputStream(configuration.getBytes());
        try {
            // 创建 yaml - properties 格式转换工厂
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            // 将配置内容写入到 yaml - properties 格式转换工厂中
            factory.setResources(new InputStreamResource(inputStream));
            // 将配置内容从 yaml 格式转换为 properties 格式
            Properties properties = factory.getObject();
            if (Objects.nonNull(properties) && !properties.isEmpty()) {
                // 将 properties 格式配置内容添加到配置环境中
                environment.add(new PropertySource(propertyName, properties));
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
