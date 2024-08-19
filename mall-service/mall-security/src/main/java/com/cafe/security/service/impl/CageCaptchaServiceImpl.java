package com.cafe.security.service.impl;

import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.enumeration.media.MediaFormatEnum;
import com.cafe.common.util.codec.Base64Util;
import com.cafe.id.feign.IDFeign;
import com.cafe.security.model.Captcha;
import com.cafe.security.service.CaptchaService;
import com.github.cage.Cage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2024/3/1 11:15
 * @Description: Cage Captcha 图片验证码业务实现类
 */
@RequiredArgsConstructor
@Service(value = "cageCaptchaServiceImpl")
public class CageCaptchaServiceImpl implements CaptchaService {

    private final Cage cage;

    private final IDFeign idFeign;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Captcha one() {
        // 获取图片验证码文本
        String code = RandomStringUtils.random(CaptchaConstant.LENGTH, CaptchaConstant.CHARACTER_SET);
        // 生成图片验证码
        BufferedImage bufferedImage = cage.drawImage(code);

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(Base64Util.encodeImage(bufferedImage, MediaFormatEnum.PNG));
    }
}
