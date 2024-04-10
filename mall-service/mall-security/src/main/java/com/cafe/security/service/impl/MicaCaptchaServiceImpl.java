package com.cafe.security.service.impl;

import com.cafe.id.feign.IDFeign;
import com.cafe.security.model.Captcha;
import com.cafe.security.service.CaptchaService;
import net.dreamlu.mica.captcha.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/10 15:47
 * @Description: Mica Captcha 图片验证码业务实现类
 */
@Service(value = "micaCaptchaServiceImpl")
public class MicaCaptchaServiceImpl implements CaptchaService {

    private final ICaptchaService captchaService;

    private final IDFeign idFeign;

    @Autowired
    public MicaCaptchaServiceImpl(ICaptchaService captchaService, IDFeign idFeign) {
        this.captchaService = captchaService;
        this.idFeign = idFeign;
    }

    @Override
    public Captcha one() {
        // 生成图片验证码唯一标识
        Long key = idFeign.nextId().getBody();

        // 生成图片验证码
        String base64Image = captchaService.generateBase64(String.valueOf(key));

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(base64Image);
    }
}
