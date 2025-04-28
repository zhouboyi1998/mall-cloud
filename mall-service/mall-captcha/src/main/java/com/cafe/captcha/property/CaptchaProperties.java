package com.cafe.captcha.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

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
    private Generator defaultGenerator = Generator.EASY;

    /**
     * 验证码生成器枚举
     */
    @Getter
    @AllArgsConstructor
    public enum Generator {

        /**
         * Kaptcha 验证码生成器
         */
        KAPTCHA(GeneratorServiceName.KAPTCHA),

        /**
         * EasyCaptcha 验证码生成器
         */
        EASY(GeneratorServiceName.EASY_CAPTCHA),

        /**
         * HutoolCaptcha 验证码生成器
         */
        HUTOOL(GeneratorServiceName.HUTOOL_CAPTCHA),

        /**
         * CageCaptcha 验证码生成器
         */
        CAGE(GeneratorServiceName.CAGE_CAPTCHA),

        /**
         * SimpleCaptcha 验证码生成器
         */
        SIMPLE(GeneratorServiceName.SIMPLE_CAPTCHA),

        /**
         * MicaCaptcha 验证码生成器
         */
        MICA(GeneratorServiceName.MICA_CAPTCHA),

        ;

        /**
         * 验证码生成器服务名称
         */
        private final String serviceName;

        /**
         * 根据枚举名称获取验证码生成器
         *
         * @param name 枚举名称
         * @return 验证码生成器
         */
        public static Generator getGeneratorByName(String name) {
            if (!StringUtils.hasText(name)) {
                return null;
            }
            name = name.toUpperCase();
            for (Generator generator : values()) {
                if (Objects.equals(generator.name(), name)) {
                    return generator;
                }
            }
            return null;
        }
    }

    /**
     * 验证码生成器服务名称
     */
    public static class GeneratorServiceName {

        public static final String KAPTCHA = "kaptchaServiceImpl";

        public static final String EASY_CAPTCHA = "easyCaptchaServiceImpl";

        public static final String HUTOOL_CAPTCHA = "hutoolCaptchaServiceImpl";

        public static final String CAGE_CAPTCHA = "cageCaptchaServiceImpl";

        public static final String SIMPLE_CAPTCHA = "simpleCaptchaServiceImpl";

        public static final String MICA_CAPTCHA = "micaCaptchaServiceImpl";
    }
}
