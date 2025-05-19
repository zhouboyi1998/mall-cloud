package com.cafe.captcha.service.impl;

import com.cafe.captcha.config.CageCaptchaConfig;
import com.cafe.captcha.model.vo.Captcha;
import com.cafe.id.feign.IDFeign;
import com.github.cage.Cage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service.impl
 * @Author: zhouboyi
 * @Date: 2025/5/19 14:14
 * @Description:
 */
public class CageCaptchaServiceImplTest {

    @InjectMocks
    private CageCaptchaServiceImpl captchaService;

    @Mock
    private IDFeign idFeign;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    private final CageCaptchaConfig cageCaptchaConfig = new CageCaptchaConfig();

    private final Cage cage = cageCaptchaConfig.cage();

    @BeforeEach
    void setUp() {
        // 模拟注入
        MockitoAnnotations.initMocks(this);

        // 模拟方法行为
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        doNothing().when(valueOperations).set(anyString(), anyLong(), anyLong(), any(TimeUnit.class));

        // 初始化被测试的服务
        captchaService = new CageCaptchaServiceImpl(cage, idFeign);
        captchaService.redisTemplate = redisTemplate;
    }

    @Test
    void testOne() {
        long key = 123456789L;
        // 模拟生成图片验证码唯一标识
        when(idFeign.nextId()).thenReturn(ResponseEntity.ok(key));

        // 生成图片验证码
        Captcha captcha = captchaService.one();

        // 验证结果
        assertNotNull(captcha);
        assertEquals(key, captcha.getKey());
        assertNotNull(captcha.getImage());

        // 打印图片验证码
        System.out.println(captcha.getImage());
    }
}
