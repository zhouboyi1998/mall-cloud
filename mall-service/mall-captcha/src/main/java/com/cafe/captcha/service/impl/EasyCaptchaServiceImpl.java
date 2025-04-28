package com.cafe.captcha.service.impl;

import com.cafe.captcha.model.vo.Captcha;
import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.captcha.service.CaptchaService;
import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import com.cafe.id.feign.IDFeign;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service.impl
 * @Author: zhouboyi
 * @Date: 2023/2/24 23:42
 * @Description: Easy Captcha 图片验证码业务实现类
 */
@RequiredArgsConstructor
@Service(value = CaptchaProperties.GeneratorServiceName.EASY_CAPTCHA)
public class EasyCaptchaServiceImpl extends BaseCaptchaServiceImpl implements CaptchaService {

    private final IDFeign idFeign;

    @Override
    public Captcha one() {
        // 生成图片验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(CaptchaConstant.WIDTH, CaptchaConstant.HEIGHT, CaptchaConstant.LENGTH);
        // 获取图片验证码文本
        String code = specCaptcha.text().toUpperCase();

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(specCaptcha.toBase64());
    }
}
