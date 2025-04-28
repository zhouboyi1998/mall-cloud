package com.cafe.captcha.service;

import com.cafe.captcha.model.query.CaptchaVerifyQuery;
import com.cafe.captcha.model.vo.Captcha;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service
 * @Author: zhouboyi
 * @Date: 2023/2/24 9:59
 * @Description: 图片验证码业务接口
 */
public interface CaptchaService {

    /**
     * 获取图片验证码
     *
     * @return 图片验证码
     */
    Captcha one();

    /**
     * 校验图片验证码
     *
     * @param query 校验图片验证码查询条件
     * @return 校验结果
     */
    Boolean verify(CaptchaVerifyQuery query);
}
