package com.cafe.captcha.config;

import cn.apiclub.captcha.backgrounds.BackgroundProducer;
import cn.apiclub.captcha.backgrounds.FlatColorBackgroundProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.producer.TextProducer;
import cn.apiclub.captcha.text.renderer.WordRenderer;
import com.cafe.captcha.support.simple.ColorfulWordRenderer;
import com.cafe.common.constant.captcha.CaptchaConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.config
 * @Author: zhouboyi
 * @Date: 2024/3/3 1:58
 * @Description: Simple Captcha 配置类
 */
@Configuration
public class SimpleCaptchaConfig {

    /**
     * 文字颜色列表
     */
    public static final List<Color> WORD_COLORS = new ArrayList<>();

    static {
        WORD_COLORS.add(new Color(0x996600));
        WORD_COLORS.add(new Color(0x0099CC));
        WORD_COLORS.add(new Color(0x0066CC));
        WORD_COLORS.add(new Color(0x0088FF));
        WORD_COLORS.add(new Color(0xFF9900));
        WORD_COLORS.add(new Color(0xFF6677));
        WORD_COLORS.add(new Color(0x339988));
        WORD_COLORS.add(new Color(0x449933));
        WORD_COLORS.add(new Color(0x7766FF));
    }

    @Bean
    public BackgroundProducer backgroundProducer() {
        return new FlatColorBackgroundProducer(Color.WHITE);
    }

    @Bean
    public TextProducer textProducer() {
        return new DefaultTextProducer(CaptchaConstant.LENGTH, CaptchaConstant.CHARACTER_SET.toCharArray());
    }

    @Bean
    public WordRenderer wordRenderer() {
        return new ColorfulWordRenderer.Builder().color(WORD_COLORS).build();
    }
}
