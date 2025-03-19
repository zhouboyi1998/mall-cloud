package com.cafe.infrastructure.xxljob.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.xxljob.property
 * @Author: zhouboyi
 * @Date: 2023/5/5 11:23
 * @Description: XXL-JOB 配置
 */
@Getter
@Setter
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

    @Getter
    @Setter
    public static class Admin {

        /**
         * 调度中心地址
         */
        private String addresses;
    }

    @Getter
    @Setter
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
         * 执行器IP
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
    }
}
