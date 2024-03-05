package com.cafe.security.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.property
 * @Author: zhouboyi
 * @Date: 2024/3/5 11:22
 * @Description: 验证码配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     * 业务接口配置
     */
    private Service service;

    @Getter
    @Setter
    public static class Service {

        /**
         * 业务实现类
         */
        private String implementation;
    }
}
