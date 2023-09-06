package com.cafe.monitor.binlog.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.property
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:09
 * @Description: 获取 application 中定义的数据库监听配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "binlog")
public class BinlogProperties {

    /**
     * 主机名
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * MySQL 服务端唯一标识
     */
    private Long serverId;

    /**
     * 监听的数据表
     */
    private List<String> table;
}
