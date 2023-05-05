package com.cafe.common.xxljob.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.xxljob.property
 * @Author: zhouboyi
 * @Date: 2023/5/5 11:23
 * @Description: XXL-JOB 配置
 */
@Component
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

    /**
     * 通讯令牌
     */
    private String accessToken;

    /**
     * 调度中心配置
     */
    private Admin admin;

    /**
     * 执行器配置
     */
    private Executor executor;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public static class Admin {

        /**
         * 调度中心地址
         */
        private String addresses;

        public String getAddresses() {
            return addresses;
        }

        public void setAddresses(String addresses) {
            this.addresses = addresses;
        }
    }

    public static class Executor {

        /**
         * 执行器名称
         */
        private String appname;

        /**
         * 执行器地址
         */
        private String address;

        /**
         * 执行器 IP
         */
        private String ip;

        /**
         * 执行器端口
         */
        private Integer port;

        /**
         * 执行器日志存储路径
         */
        private String logPath;

        /**
         * 执行器日志保存天数
         */
        private Integer logRetentionDays;

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getLogPath() {
            return logPath;
        }

        public void setLogPath(String logPath) {
            this.logPath = logPath;
        }

        public Integer getLogRetentionDays() {
            return logRetentionDays;
        }

        public void setLogRetentionDays(Integer logRetentionDays) {
            this.logRetentionDays = logRetentionDays;
        }
    }
}
