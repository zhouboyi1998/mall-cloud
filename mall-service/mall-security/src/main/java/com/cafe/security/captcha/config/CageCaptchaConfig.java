package com.cafe.security.captcha.config;

import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.pool.FloatConstant;
import com.cafe.common.enumeration.media.MediaFormatEnum;
import com.github.cage.Cage;
import com.github.cage.image.EffectConfig;
import com.github.cage.image.Painter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Color;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.captcha.config
 * @Author: zhouboyi
 * @Date: 2024/3/1 17:08
 * @Description: Cage Captcha 配置类
 */
@Configuration
public class CageCaptchaConfig {

    @Bean
    public Cage cage() {
        // 创建文本效果配置 (ripple 涟漪, blur 模糊, outline 立体, rotate 旋转)
        EffectConfig effectConfig = new EffectConfig(false, false, false, true, null);
        // 创建 Painter 图片验证码绘画器
        Painter painter = new Painter(CaptchaConstant.WIDTH, CaptchaConstant.HEIGHT, Color.WHITE, Painter.Quality.MAX, effectConfig, null);
        // 创建 Cage 验证码实例, 并注入到 Spring 容器中
        return new Cage(painter, null, null, MediaFormatEnum.PNG.getFormat(), FloatConstant.ONE_POINT_ZERO, null, null);
    }
}
