package com.cafe.captcha.service.impl;

import com.cafe.captcha.model.query.CaptchaVerifyQuery;
import com.cafe.captcha.model.vo.Captcha;
import com.cafe.captcha.service.CaptchaService;
import com.cafe.common.constant.redis.RedisConstant;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service
 * @Author: zhouboyi
 * @Date: 2025/4/28 18:08
 * @Description: 图片验证码服务公共基类
 */
@NoArgsConstructor
public abstract class BaseCaptchaServiceImpl implements CaptchaService {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Override
    public abstract Captcha one();

    @Override
    public Boolean verify(CaptchaVerifyQuery query) {
        // 从 Redis 中获取正确的图片验证码文本
        String correctCode = (String) redisTemplate.opsForValue().get(RedisConstant.CAPTCHA_PREFIX + query.getKey());
        // 校验用户输入的验证码文本是否正确
        return Objects.equals(query.getCode(), correctCode);
    }
}
