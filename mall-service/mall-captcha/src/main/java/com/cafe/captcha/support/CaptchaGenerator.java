package com.cafe.captcha.support;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.support
 * @Author: zhouboyi
 * @Date: 2025/9/12 11:26
 * @Description: 验证码生成器名称枚举
 */
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
     * 根据枚举名称获取枚举
     *
     * @param name 枚举名称
     * @return 枚举
     */
    public static CaptchaGenerator get(String name) {
        if (Objects.isNull(name)) {
            return null;
        }
        name = name.toUpperCase();
        for (CaptchaGenerator value : values()) {
            if (Objects.equals(name, value.name())) {
                return value;
            }
        }
        return null;
    }
}
