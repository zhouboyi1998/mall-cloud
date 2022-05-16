package com.cafe.monitor.mysql.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.mysql.property
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:09
 * @Description: 获取 application 中定义的 被监听数据库的连接配置
 */
@Component
@ConfigurationProperties(prefix = "binlog")
public class BinlogProperties {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Long serverId;

    private List<String> tableName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }
}
