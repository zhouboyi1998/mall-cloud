package com.cafe.monitor.debezium.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.monitor.debezium.property
 * @Author: zhouboyi
 * @Date: 2023/4/21 16:25
 * @Description: Debezium 配置
 */
@Component
@ConfigurationProperties(prefix = "debezium")
public class DebeziumProperties {

    private String name;

    private Offset offset;

    private History history;

    private Strategy strategy;

    private Datasource datasource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public static class Offset {

        private String handler;

        private String filename;

        private String flushInterval;

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFlushInterval() {
            return flushInterval;
        }

        public void setFlushInterval(String flushInterval) {
            this.flushInterval = flushInterval;
        }
    }

    public static class History {

        private String handler;

        private String filename;

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }

    public static class Strategy {

        private String schemaChange;

        private String serverId;

        private String serverName;

        public String getSchemaChange() {
            return schemaChange;
        }

        public void setSchemaChange(String schemaChange) {
            this.schemaChange = schemaChange;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }
    }

    public static class Datasource {

        private String host;

        private String port;

        private String username;

        private String password;

        private String database;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
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

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }
    }
}
