package com.cafe.infrastructure.xxljob.config;

import com.cafe.infrastructure.xxljob.property.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.xxljob.config
 * @Author: zhouboyi
 * @Date: 2023/5/5 11:22
 * @Description: XXL-JOB 配置类
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class XxlJobConfig {

    private final XxlJobProperties xxlJobProperties;

    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor() {
        log.info("XxlJobConfig.xxlJobExecutor(): xxl-job init, appname -> {}", xxlJobProperties.getExecutor().getAppname());
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppname());
        xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
