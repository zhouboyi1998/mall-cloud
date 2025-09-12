package com.cafe.captcha.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.support
 * @Author: zhouboyi
 * @Date: 2025/9/12 11:26
 * @Description: 验证码生成器名称枚举
 */
@AllArgsConstructor
@Getter
public enum CaptchaGenerator {

    /**
     * Kaptcha 验证码生成器
     */
    KAPTCHA,

    /**
     * Easy Captcha 验证码生成器
     */
    EASY,

    /**
     * Hutool Captcha 验证码生成器
     */
    HUTOOL,

    /**
     * Cage Captcha 验证码生成器
     */
    CAGE,

    /**
     * Simple Captcha 验证码生成器
     */
    SIMPLE,

    /**
     * Mica Captcha 验证码生成器
     */
    MICA,

    ;

    /**
     * 根据枚举名称查找枚举
     *
     * @param name 枚举名称
     * @return 枚举
     */
    public static CaptchaGenerator findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        name = name.toUpperCase();
        for (CaptchaGenerator captchaGenerator : values()) {
            if (Objects.equals(captchaGenerator.name(), name)) {
                return captchaGenerator;
            }
        }
        return null;
    }
}
