package com.cafe.monitor.binlog.database.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.binlog.database.property
 * @Author: zhouboyi
 * @Date: 2022/5/16 15:09
 * @Description: 获取 application 中定义的数据库监听配置
 */
@Component
@ConfigurationProperties(prefix = "binlog")
public class BinlogProperties {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Long serverId;

    private List<String> table;

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

    public List<String> getTable() {
        return table;
    }

    public void setTable(List<String> table) {
        this.table = table;
    }
}
