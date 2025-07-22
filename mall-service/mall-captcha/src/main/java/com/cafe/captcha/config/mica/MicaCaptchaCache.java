package com.cafe.captcha.config.mica;

import com.cafe.common.constant.captcha.CaptchaConstant;
import com.cafe.common.constant.redis.RedisConstant;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.captcha.cache.ICaptchaCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.config.mica
 * @Author: zhouboyi
 * @Date: 2024/4/10 16:08
 * @Description: Mica 图片验证码缓存
 */
@RequiredArgsConstructor
@Component
public class MicaCaptchaCache implements ICaptchaCache {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void put(String cache, String key, String code) {
        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, CaptchaConstant.TIMEOUT, TimeUnit.SECONDS);
    }
}
