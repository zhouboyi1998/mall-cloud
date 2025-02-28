package com.cafe.security.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.common.enumeration.media.MediaFormatEnum;
import com.cafe.id.feign.IDFeign;
import com.cafe.security.model.vo.Captcha;
import com.cafe.security.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2023/2/27 9:56
 * @Description: Hutool Captcha 图片验证码业务实现类
 */
@RequiredArgsConstructor
@Service(value = "hutoolCaptchaServiceImpl")
public class HutoolCaptchaServiceImpl implements CaptchaService {

    private final IDFeign idFeign;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Captcha one() {
        // 生成图片验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(CaptchaConstant.WIDTH, CaptchaConstant.HEIGHT, CaptchaConstant.LENGTH, IntegerConstant.FIFTY);
        // 获取图片验证码文本
        String code = lineCaptcha.getCode().toUpperCase();

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(MediaFormatEnum.PNG.getBase64Prefix() + lineCaptcha.getImageBase64());
    }
}
