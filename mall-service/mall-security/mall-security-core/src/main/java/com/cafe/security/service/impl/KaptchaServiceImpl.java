package com.cafe.security.service.impl;

import com.cafe.common.constant.MediaConstant;
import com.cafe.common.constant.NumberConstant;
import com.cafe.common.constant.RedisConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.security.model.Captcha;
import com.cafe.security.service.CaptchaService;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:59
 * @Description: Kaptcha 图片验证码业务实现类
 */
@Service
public class KaptchaServiceImpl implements CaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaptchaServiceImpl.class);

    private final Producer producer;

    private final IDFeign idFeign;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public KaptchaServiceImpl(Producer producer, IDFeign idFeign, RedisTemplate<String, Object> redisTemplate) {
        this.producer = producer;
        this.idFeign = idFeign;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Captcha one() {
        // 生成图片验证码的文本
        String code = producer.createText();
        // 根据文本生成图片验证码
        BufferedImage bufferedImage = producer.createImage(code);

        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 将图片转换成 Base64 编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, MediaConstant.PNG, outputStream);
        } catch (IOException e) {
            LOGGER.error("KaptchaServiceImpl.one(): could not create base64 image -> {}", e.getMessage());
        }
        String image = MediaConstant.BASE64_PNG_PREFIX + Base64.getEncoder().encodeToString(outputStream.toByteArray());

        // 保存图片验证码的唯一标识和文本到 Redis 中
        redisTemplate.opsForValue().set(RedisConstant.CAPTCHA_PREFIX + key, code, NumberConstant.SIXTY, TimeUnit.SECONDS);

        // 组装图片验证码返回值
        return new Captcha().setKey(key).setImage(image);
    }
}
