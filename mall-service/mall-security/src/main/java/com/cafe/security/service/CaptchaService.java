package com.cafe.security.service;

import com.cafe.security.model.vo.Captcha;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.service
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:59
 * @Description: 图片验证码业务接口
 */
public interface CaptchaService {

    /**
     * 获取图片验证码
     *
     * @return
     */
    Captcha one();
}
