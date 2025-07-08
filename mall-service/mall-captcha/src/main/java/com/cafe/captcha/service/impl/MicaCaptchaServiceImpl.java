package com.cafe.captcha.service.impl;

import com.cafe.captcha.model.vo.Captcha;
import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.captcha.service.CaptchaService;
import com.cafe.id.feign.IDFeign;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.captcha.service.ICaptchaService;
import org.springframework.stereotype.Service;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service.impl
 * @Author: zhouboyi
 * @Date: 2024/4/10 15:47
 * @Description: Mica Captcha 图片验证码业务实现类
 */
@RequiredArgsConstructor
@Service(value = CaptchaProperties.GeneratorServiceName.MICA_CAPTCHA)
public class MicaCaptchaServiceImpl extends BaseCaptchaServiceImpl implements CaptchaService {

    private final ICaptchaService micaCaptchaService;

    private final IDFeign idFeign;

    @Override
    public Captcha one() {
        // 生成图片验证码唯一标识
        Long key = idFeign.nextId(null).getBody();

        // 生成图片验证码
        String base64Image = micaCaptchaService.generateBase64(String.valueOf(key));

        // 返回图片验证码
        return new Captcha().setKey(key).setImage(base64Image);
    }
}
