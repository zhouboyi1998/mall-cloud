package com.cafe.captcha.service.impl;

import com.cafe.captcha.model.vo.Captcha;
import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.captcha.service.CaptchaService;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.enumeration.media.MediaFormatEnum;
import com.cafe.common.util.codec.Base64Util;
import com.cafe.id.feign.IDFeign;
import com.google.code.kaptcha.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service.impl
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:59
 * @Description: Kaptcha 图片验证码业务实现类
 */
@RequiredArgsConstructor
@Service(value = CaptchaProperties.GeneratorServiceName.KAPTCHA)
public class KaptchaServiceImpl extends BaseCaptchaServiceImpl implements CaptchaService {

    private final Producer producer;

    private final IDFeign idFeign;

    @Override
    public Captcha one() {
        // 生成图片验证码的文本
        String code = producer.createText();
        // 根据文本生成图片验证码
        BufferedImage bufferedImage = producer.createImage(code);

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId(null).getBody();

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(Base64Util.encodeImage(bufferedImage, MediaFormatEnum.PNG));
    }
}
