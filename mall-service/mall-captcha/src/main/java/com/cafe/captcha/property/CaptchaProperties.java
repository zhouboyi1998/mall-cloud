package com.cafe.captcha.property;

import com.cafe.captcha.support.CaptchaGenerator;
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
     * 默认验证码生成器
     */
    private CaptchaGenerator defaultGenerator = CaptchaGenerator.EASY;
}
