package com.cafe.config.environment;

import com.cafe.common.constant.pool.StringConstant;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.config.environment
 * @Author: zhouboyi
 * @Date: 2024/2/25 19:52
 * @Description: 抽象配置环境仓库
 */
public abstract class AbstractEnvironmentRepository implements EnvironmentRepository, Ordered {

    @Override
    public Environment findOne(String application, String profile, String label) {
        // 获取 project 名称
        String project = application.split(StringConstant.HYPHEN)[0];
        // 获取 profile 数组
        String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);

        // 创建配置环境
        Environment environment = new Environment(application, profiles, label, null, null);

        // 读取项目公共配置
        addPropertySource(environment, project);
        // 读取模块公共配置
        addPropertySource(environment, application);
        for (String prof : profiles) {
            // 读取项目运行环境配置
            addPropertySource(environment, project + StringConstant.HYPHEN + prof);
            // 读取模块运行环境配置
            addPropertySource(environment, application + StringConstant.HYPHEN + prof);
        }
        return environment;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 读取配置内容并添加到配置环境中
     *
     * @param environment
     * @param propertyName
     */
    protected abstract void addPropertySource(Environment environment, String propertyName);
}
