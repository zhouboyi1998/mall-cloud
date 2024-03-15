package com.cafe.canal.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.canal.property
 * @Author: zhouboyi
 * @Date: 2022/7/13 23:32
 * @Description: Canal 配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "canal")
public class CanalProperties {

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 目标
     */
    private String destination;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 批处理数据量
     */
    private Integer batchSize;

    /**
     * 监听并推送至 RabbitMQ 的数据表
     */
    private List<String> rabbitTable;

    /**
     * 监听并推送至 RocketMQ 的数据表
     */
    private List<String> rocketTable;
}
