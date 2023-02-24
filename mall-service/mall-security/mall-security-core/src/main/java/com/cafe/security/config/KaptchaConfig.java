package com.cafe.security.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.config
 * @Author: zhouboyi
 * @Date: 2023/2/22 17:59
 * @Description: Kaptcha 配置类
 */
@Configuration
public class KaptchaConfig {

    @Bean(name = "defaultKaptcha")
    public DefaultKaptcha defaultKaptcha() {
        Properties properties = new Properties();

        // 图片是否有边框
        properties.setProperty("kaptcha.border", "no");
        // 图片边框颜色
        properties.setProperty("kaptcha.border.color", "white");
        // 图片边框厚度
        properties.setProperty("kaptcha.border.thickness", "0");
        // 图片宽度
        properties.setProperty("kaptcha.image.width", "130");
        // 图片高度
        properties.setProperty("kaptcha.image.height", "48");
        // 图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");

        // 文本集合
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 文本字符长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 文本字符间距
        properties.setProperty("kaptcha.textproducer.char.space", "8");
        // 文本字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
        // 文本字符颜色
        properties.setProperty("kaptcha.textproducer.font.color", "0,175,155");
        // 文本字符大小
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        // 文本生成器
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");

        // 干扰颜色
        properties.setProperty("kaptcha.noise.color", "230,162,60");
        // 干扰图片样式
        // 水纹: com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼: com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影: com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        // 干扰生成器
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        // 背景颜色渐变
        properties.setProperty("kaptcha.background.clear.to", "white");
        // 背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");

        // 文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");

        Config config = new Config(properties);

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
