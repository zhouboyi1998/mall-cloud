package com.cafe.monitor.canal.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.canal.property
 * @Author: zhouboyi
 * @Date: 2022/7/13 23:32
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "canal")
public class CanalProperties {

    private String host;

    private Integer port;

    private String destination;

    private String username;

    private String password;

    private Integer batchSize;

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public List<String> getTable() {
        return table;
    }

    public void setTable(List<String> table) {
        this.table = table;
    }
}
