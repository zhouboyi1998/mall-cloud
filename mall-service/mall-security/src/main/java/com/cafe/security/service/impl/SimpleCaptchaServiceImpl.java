package com.cafe.security.service.impl;

import cn.apiclub.captcha.backgrounds.BackgroundProducer;
import cn.apiclub.captcha.text.producer.TextProducer;
import cn.apiclub.captcha.text.renderer.WordRenderer;
import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.enumeration.media.MediaFormatEnum;
import com.cafe.common.util.codec.Base64Util;
import com.cafe.id.feign.IDFeign;
import com.cafe.security.model.Captcha;
import com.cafe.security.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/3 0:16
 * @Description: Simple Captcha 图片验证码业务实现类
 */
@Service(value = "simpleCaptchaServiceImpl")
public class SimpleCaptchaServiceImpl implements CaptchaService {

    private final BackgroundProducer backgroundProducer;

    private final TextProducer textProducer;

    private final WordRenderer wordRenderer;

    private final IDFeign idFeign;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public SimpleCaptchaServiceImpl(
        BackgroundProducer backgroundProducer,
        TextProducer textProducer,
        WordRenderer wordRenderer,
        IDFeign idFeign,
        RedisTemplate<String, Object> redisTemplate
    ) {
        this.backgroundProducer = backgroundProducer;
        this.textProducer = textProducer;
        this.wordRenderer = wordRenderer;
        this.idFeign = idFeign;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Captcha one() {
        // 生成图片验证码
        cn.apiclub.captcha.Captcha simpleCaptcha = new cn.apiclub.captcha.Captcha.Builder(CaptchaConstant.WIDTH, CaptchaConstant.HEIGHT).addBackground(backgroundProducer).addText(textProducer, wordRenderer).build();
        // 获取图片验证码文本
        String code = simpleCaptcha.getAnswer();

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(Base64Util.encodeImage(simpleCaptcha.getImage(), MediaFormatEnum.PNG));
    }
}
