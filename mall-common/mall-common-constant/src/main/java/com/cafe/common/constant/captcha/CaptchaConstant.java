package com.cafe.common.constant.captcha;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.captcha
 * @Author: zhouboyi
 * @Date: 2023/2/27 10:30
 * @Description: 图片验证码相关常量
 */
public class CaptchaConstant {

    /**
     * 图片宽度
     */
    public static final Integer WIDTH = 130;

    /**
     * 图片高度
     */
    public static final Integer HEIGHT = 48;

    /**
     * 文本长度
     */
    public static final Integer LENGTH = 4;

    /**
     * 干扰线数量
     */
    public static final Integer LINE_COUNT = 50;

    /**
     * 文本字符集
     */
    public static final String CHARACTER_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 验证码超时时间 (单位: 秒)
     */
    public static final Integer TIMEOUT = 180;
}
