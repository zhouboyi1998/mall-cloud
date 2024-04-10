package com.cafe.security.captcha.mica;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.redis.RedisConstant;
import net.dreamlu.mica.captcha.cache.ICaptchaCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.captcha.mica
 * @Author: zhouboyi
 * @Date: 2024/4/10 16:08
 * @Description: Mica 图片验证码缓存
 */
@Component
public class MicaCaptchaCache implements ICaptchaCache {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public MicaCaptchaCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void put(String cache, String key, String code) {
        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, IntegerConstant.SIXTY, TimeUnit.SECONDS);
    }
}
